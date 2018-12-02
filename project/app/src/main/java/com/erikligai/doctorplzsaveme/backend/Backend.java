package com.erikligai.doctorplzsaveme.backend;

import android.content.Context;
import android.net.sip.SipSession;
import android.util.Log;

import com.erikligai.doctorplzsaveme.Models.Comment;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.google.common.util.concurrent.Service;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Backend implements IPatientBackend, ICareProviderBackend {

    // SINGLETON CODE -----------------------
    private static Backend instance = new Backend();
    public static Backend getInstance() {
        return instance;
    }
    private Backend() {}

    // IPatientBackend CODE -----------------

    // TODO: move this filename to an xml
    private static final String P_FILENAME = "patient_profile.sav";
    private static final String CP_FILENAME = "cp_profile.sav";

    private Context mContext = null; // context for reading to/from file
    // current patient profile
    private Patient patientProfile = null;

    // THIS MUST BE CALLED ON APP STARTUP!
    public void setContext(Context context)
    {
        if (context != null)
        {
            this.mContext = context;
        } else
        {
            Log.e("setContext: ","context is null!");
            System.exit(1); // shouldn't happen!
        }
    }

    // THIS MUST BE CALLED WHENEVER A CHANGE TO PROFILE AND ITS MEMBERS IS MADE
    public void UpdatePatient() {
        // sync with DB
        new Thread(new Runnable() {
            @Override
            public void run() {
                serializePatientProfile();
                try {
                    syncPatientES();
                } catch (Exception e)
                {
                    Log.d("UpdatePatient: ", "Could not sync patient!");
                }
            }
        }).start();
    }

    public void setPatientProfile(Patient patientProfile) {
        if (patientProfile == null)
        {
            Log.e("setPatientProfile: ", "patientProfile is null or empty!");
        } else
        {
            this.patientProfile = patientProfile;
            UpdatePatient();
        }
    }

    public Patient getPatientProfile() {
        return patientProfile;
    }

    public ArrayList<Problem> getPatientProblems() {
        try
        {
            return patientProfile.getProblemList();
        } catch (Exception e)
        {
            Log.e("getPatientProblem: ", "could not get problems list!");
            e.printStackTrace();
            return null;
        }
    }
 
    public ArrayList<Record> getPatientRecords(int problemIndex) {
        try
        {
            return patientProfile.getProblemList().get(problemIndex).getRecords();
        } catch (Exception e)
        {
            Log.e("getPatientRecord: ", "could not get record!");
            e.printStackTrace();
        }
        return null;
    }

    public void addPatientProblem(Problem problem) {
        try
        {
            patientProfile.addProblem(problem);
            UpdatePatient();
        } catch (Exception e)
        {
            Log.e("addPatientProblem: ", "could not add problem!");
            e.printStackTrace();
        }
    }

    public void deletePatientProblem(int problemIndex) {
        try {
            patientProfile.deleteProblem(problemIndex);
            UpdatePatient();
        } catch (Exception e) {
            Log.e("deletePatientProblem: ", "could not delete problem!");
            e.printStackTrace();
        }
    }

    public void addPatientRecord(int problemIndex, Record record) {
        try
        {
            patientProfile.getProblemList().get(problemIndex).addRecord(record);
            UpdatePatient();
        } catch (Exception e)
        {
            Log.e("addPatientRecord: ", "could not add record!");
            e.printStackTrace();
        }
    }

    public void deletePatientRecord(int problemIndex, int recordIndex) {
        try {
            patientProfile.getProblemList().get(problemIndex).getRecords().remove(recordIndex);
            UpdatePatient();
        } catch (Exception e)
        {
            Log.e("deletePatientRecord: ", "could not delete record!");
            e.printStackTrace();
        }
    }

    private void serializePatientProfile()
    {
        try {
            assert(mContext != null);
            assert(patientProfile != null);
            FileOutputStream fos = mContext.getApplicationContext().openFileOutput(P_FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(patientProfile, writer);
            writer.flush();
            writer.close();
            fos.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace(); // shouldn't happen ever
        }
    }

    private boolean deserializePatientProfile()
    {
        try {
            assert(mContext != null);
            FileInputStream fis = mContext.getApplicationContext().openFileInput(P_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            patientProfile = gson.fromJson(reader, Patient.class);
            return true;
        } catch (IOException e) {
            // we couldn't find it in file, so just make sure its null and return false to
            // notify that we did not find anything on local storage
            patientProfile = null;
            return false;
        }
    }

    private void syncPatientES() throws InterruptedException
    {
        try {

            String UserID = patientProfile.getID();
            ElasticsearchProblemController.GetPatientTask getPatientTask = new ElasticsearchProblemController.GetPatientTask();
            Patient es_patient = null;
            es_patient = getPatientTask.execute(UserID).get();
            if (es_patient != null)
            {
                for (int i = 0; i < patientProfile.getProblemList().size(); ++i)
                {
                    patientProfile.getProblemList().get(i).setComments(es_patient.getProblemList().get(i).getComments());
                }
            } else { throw new Exception(); } // es_patient should not be null if connected to DB
            ElasticsearchProblemController.SetPatientTask setPatientTask = new ElasticsearchProblemController.SetPatientTask();
            // throw exception if we couldn't upload to DB
            if (!setPatientTask.execute(patientProfile).get()) { throw new Exception(); }
        } catch (Exception e) {
            throw new InterruptedException("syncPatientES error");
        }

    }

    public Patient fetchPatientProfile()
    {
        // if local storage doesn't have a profile, return null
        if (!deserializePatientProfile()) { return null; }
        // fetch Patient from DB, will overwrite local comments if it can, and override patient profile
        // if it finds a profile
        new Thread(new Runnable()  {
            @Override
            public void run()  {
                try {
                    syncPatientES();
                } catch (Exception e)
                {
                    Log.d("fetchPatientProfile: ", "could not sync!");
                }
            }
        }).start();
        return patientProfile;
    }

    public void setPatientFromES(String UserID)
    {
        patientProfile = null;
        try {
            ElasticsearchProblemController.GetPatientTask getPatientTask = new ElasticsearchProblemController.GetPatientTask();
            patientProfile = getPatientTask.execute(UserID).get();
            serializePatientProfile();
        } catch (Exception e)
        {
            // do nothing, couldn't login
        }
        // returns null if couldn't get from ES
    }

    public void clearPatientData()
    {
        try
        {
            mContext.getApplicationContext().deleteFile(P_FILENAME);
        } catch (Exception e)
        {
            Log.e("clearPatientData: ", "could not delete patient data!");
            e.printStackTrace();
        }
    }

    // https://stackoverflow.com/questions/9570237/android-check-internet-connection
    // razzak
    public boolean isConnected(){
        if (userIDExists(patientProfile.getID()) == 0)
        {
            return true;
        }
        return false;
    }

    public static int userIDExists(String UserID)
    {
        ElasticsearchProblemController.CheckIfPatientIDExistsTask checkTask =
                new ElasticsearchProblemController.CheckIfPatientIDExistsTask();
        try {
            return (checkTask.execute(UserID).get());
        } catch (Exception e)
        {
            return -1;
        }
    }

    // ICareProviderBackend CODE ------------

    // CP ONLY PULLS FROM DB WHEN LOGGING IN, AND ADDING/DELETING PATIENTS!

    private ArrayList<Patient> m_patients = new ArrayList<>();

    public String getCP_ID() {
        return CP_ID;
    }

    public void setCP_ID(String CP_ID) {
        this.CP_ID = CP_ID;
    }

    private String CP_ID = null;

    // patient list adapts to this
    public ArrayList<Patient> getM_patients() {
        return m_patients;
    }

    // adds comment to the patient's problem and updates that patient profile to DB
    public void addComment(String PatientID, int problemIndex, String comment)
    {
        assert(m_patients != null);
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) {
                patient.getProblemList().get(problemIndex).addComment(new Comment(comment));
                UpdatePatient(patient);
                return;
            }
        }
    }

    // add patient to CP, PatientID would be aquired from QR code
    public void AddPatient(String PatientID)
    {
        // TODO: update DB patientIDs, and add that patientID's Patient to m_patients
        // requires error checking (like ID already exists in m_patients, or doesn't exist on DB)
        for (Patient p : m_patients) {
            if (p.getID().equals(PatientID)) {
//                Log.e("Error", "already exists in care provider's list");
                return;
            }
        }
        ElasticsearchProblemController.AssignPatientToCPTask assignTask = new ElasticsearchProblemController.AssignPatientToCPTask();
        String[] params = new String[]{CP_ID, PatientID};
        assignTask.execute(params);

        ElasticsearchProblemController.GetPatientTask getPatientTask = new ElasticsearchProblemController.GetPatientTask();
        try {
            Patient new_patient = getPatientTask.execute(PatientID).get();
            if (new_patient != null) { m_patients.add(new_patient); }
        } catch (Exception e) {}
    }

    // remove patient from CP (not required!) PatientID would be aquired from the Patient class
    public void RemovePatient(String PatientID)
    {
        // TODO: update DB patientIDs, and remove that patientID's Patient from m_patients
    }

    private boolean UpdatePatient(Patient patient)
    {
        ElasticsearchProblemController.SetPatientTask setPatientTask = new ElasticsearchProblemController.SetPatientTask();
        try {
            return setPatientTask.execute(patient).get();
        } catch (Exception e) { return false; }
    }

    public void PopulatePatients()
    {
        ArrayList<String> PatientIDs = null;
        ElasticsearchProblemController.GetCPPatientsTask getPatientsTask = new ElasticsearchProblemController.GetCPPatientsTask();
        try {
            PatientIDs = getPatientsTask.execute(CP_ID).get().getPatients();
            Log.d("size: ", Integer.toString(PatientIDs.size()));
            for (String patient : PatientIDs)
            {
                Log.d("test: ", patient);
                ElasticsearchProblemController.GetPatientTask getPatientTask = new ElasticsearchProblemController.GetPatientTask();
                m_patients.add(getPatientTask.execute(patient).get());
            }
        }
        catch (Exception e) {}
    }

    public ArrayList<Patient> GetPatients()
    {
        return m_patients;
    }

    public void ClearPatients()
    {
        m_patients = new ArrayList<>();
    }

    public ArrayList<Problem> GetCPPatientProblems(String PatientID)
    {
        assert(m_patients != null);
        for (Patient patient : m_patients )
        {
            Log.e("patient ID", patient.getID());
            Log.e("patient ID", PatientID);
            if (patient.getID().equals(PatientID)) { return patient.getProblemList(); }
        }
        assert(false); // i.e. shouldn't happen!
        return null;
    }

    public ArrayList<Record> GetCPPatientRecords(String PatientID, int ProblemIndex)
    {
        assert(m_patients != null);
        for (Patient patient : m_patients )
        {
            Log.e("patient ID", patient.getID());
            Log.e("patient ID", PatientID);


            if (patient.getID().equals(PatientID)) { return patient.getProblemList().get(ProblemIndex).getRecords(); }
        }
        assert(false); // i.e. shouldn't happen!
        return null;

    }

    public Record GetCPPatientRecord(String PatientID, int ProblemIndex, int RecordIndex)
    {
        assert(m_patients != null);
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient.getProblemList().get(ProblemIndex).getRecords().get(RecordIndex); }
        }
        assert(false); // i.e. shouldn't happen!
        return null;
    }

    public Problem GetCPPatientProblem(String PatientID, int ProblemIndex)
    {
        assert(m_patients != null);
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient.getProblemList().get(ProblemIndex); }
        }
        assert(false); // i.e. shouldn't happen!
        return null;
    }

    public Patient GetCPPatient(String PatientID)
    {
        assert(m_patients != null);
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient; }
        }
        assert(false); // i.e. shouldn't happen!
        return null;
    }


    // SAVE CP STUFF -----------

    public void SaveCPProfile()
    {
        if (CP_ID == null) { return; }
        serializeCPProfile();
    }

    public void clearCPData()
    {
        mContext.getApplicationContext().deleteFile(CP_FILENAME);
    }

    private void serializeCPProfile()
    {
        try {
            assert(mContext != null);
            FileOutputStream fos = mContext.getApplicationContext().openFileOutput(CP_FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(CP_ID, writer);
            writer.flush();
            writer.close();
            fos.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace(); // shouldn't happen ever
        }
    }

    public boolean deserializeCPProfile()
    {
        try {
            assert(mContext != null);
            FileInputStream fis = mContext.getApplicationContext().openFileInput(CP_FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            CP_ID = gson.fromJson(reader, String.class);
            return true;
        } catch (IOException e) {
            // we couldn't find it in file, so just make sure its null and return false to
            // notify that we did not find anything on local storage
            Log.e("failure:","!!!!!!!!!!!!!!!!!!!!!!!");
            CP_ID = null;
            return false;
        }
    }



}
