package com.erikligai.doctorplzsaveme.backend;

import android.content.Context;
import android.util.Log;

import com.erikligai.doctorplzsaveme.Models.Comment;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * This class is a Singleton class which holds, writes, fetches data
 * for the rest of the app. This includes the patient profile(s), data for CP, etc.
 */
public class Backend implements IPatientBackend, ICareProviderBackend {

    // SINGLETON CODE -----------------------

    // instance of the singleton
    private static Backend instance = new Backend();

    /** fetch the singleton instance
     * @return instance: Backend
     */
    public static Backend getInstance() {
        return instance;
    }

    /**
     * // empty constructor for the Backend
     */
    private Backend() {}

    // IPatientBackend CODE -----------------

    // filenames for local storage (cached data/userids as keys)
    private static final String P_FILENAME = "patient_profile.sav";
    private static final String CP_FILENAME = "cp_profile.sav";

    // context for reading to/from file
    private Context mContext = null;

    // current patient profile
    private Patient patientProfile = null;

    /**
     * THIS MUST BE CALLED ON APP STARTUP!
     * sets context for reading to/from file
     * @param context : Context
     */
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

    /**
     * THIS MUST BE CALLED WHENEVER A CHANGE TO PROFILE AND ITS MEMBERS IS MADE
     * syncs the current patient with DB (if it can) by fetching DB comments (if CP has made
     * a change to comments, overriding the local comments, and pushing the local profile to DB)
     */
    public boolean UpdatePatient() {
        serializePatientProfile();
        try {
            syncPatientES();
            //setPatientProfile(patientProfile);
        } catch (Exception e) {
            Log.d("UpdatePatient: ", "Could not sync patient!");
            return false;
        }
        return true;
    }

    /**
     * THIS MUST BE CALLED WHENEVER A CHANGE TO PROFILE AND ITS MEMBERS IS MADE
     * syncs the current patient with DB (if it can) by fetching DB comments (if CP has made
     * a change to comments, overriding the local comments, and pushing the local profile to DB)
     * THIS RUNS ON A SEPARATE THREAD SO IT DOES NOT CLOG THE UI
     */
    public void UpdatePatientRunnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                serializePatientProfile();
                try {
                    syncPatientES();
                    //setPatientProfile(patientProfile);
                } catch (Exception e) {
                    Log.d("UpdatePatientRunnable: ", "Could not sync patient!");
                }
            }
        });
    }

    /**
     * given a patientProfile class, set the patientProfile (done if user creates new profile)
     * then updates to DB (if it can)
     * @param patientProfile : Patient
     */
    public boolean setPatientProfile(Patient patientProfile) {
        if (patientProfile == null)
        {
            Log.e("setPatientProfile: ", "patientProfile is null or empty!");
        } else
        {
            try
            {
                ElasticsearchProblemController.SetPatientTask setPatientTask = new ElasticsearchProblemController.SetPatientTask();
                if (setPatientTask.execute(patientProfile).get())
                {
                    this.patientProfile = patientProfile;
                    return true;
                }
            } catch (Exception e) { return false; }
        }
        return false;
    }

    /**
     * return the current patient profile
     * @return this.patientProfile : Patient
     */
    public Patient getPatientProfile() {
        return patientProfile;
    }

    /**
     * get the current patient profile's problems
     * @return ArrayList<Problem> of patient's problems
     */
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

    /**
     * get the current patient profiles' records given a problem index
     * @param problemIndex : int
     * @return ArrayList<Record> of patient's records of a particular problem
     */
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

    /**
     * add a body location photo to the patient
     * @param id : String
     * @param photo : String
     * @param photoLabel : String
     */
    public void addPatientPhoto(String id, String photo, String photoLabel) {
        assert(patientProfile != null);
        patientProfile.addPhoto(id,photo, photoLabel);
        UpdatePatient();
    }

    /**
     * add a problem to the current patient
     * @param problem : Problem
     */
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

    /**
     * delete a patient's problem given an index (from a recyclerview most likely)
     * @param problemIndex : int
     */
    public void deletePatientProblem(int problemIndex) {
        try {
            patientProfile.deleteProblem(problemIndex);
            UpdatePatient();
        } catch (Exception e) {
            Log.e("deletePatientProblem: ", "could not delete problem!");
            e.printStackTrace();
        }
    }

    /**
     * add a patient record given the problem index and Record object
     * @param problemIndex : int
     * @param record : Record
     */
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

    /**
     * delete a patient record given a problem index and record index (obtained from recyclerviews)
     * @param problemIndex : int
     * @param recordIndex : int
     */
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

    /**
     * save the current patient profile to file (to locally cache)
     */
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

    /**
     * fetch the local patient profile (if it exists)
     * @return boolean (whether or not it succeeded)
     */
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

    /**
     * sync the patient with the DB
     * @throws InterruptedException if failed to sync
     */
    private void syncPatientES() throws InterruptedException
    {
        try {

            String UserID = patientProfile.getID();
            ElasticsearchProblemController.GetPatientTask getPatientTask = new ElasticsearchProblemController.GetPatientTask();
            Patient es_patient = null;
            // get the DB patient
            es_patient = getPatientTask.execute(UserID).get();
            // sync comments
            if (es_patient != null)
            {
                try {
                    for (int i = 0; i < patientProfile.getProblemList().size(); ++i)
                    {
                        patientProfile.getProblemList().get(i).setComments(es_patient.getProblemList().get(i).getComments());
                    }
                } catch (Exception e) {}
            } else { throw new Exception(); } // es_patient should not be null if connected to DB
            ElasticsearchProblemController.SetPatientTask setPatientTask = new ElasticsearchProblemController.SetPatientTask();
            // throw exception if we couldn't upload to DB
            if (!setPatientTask.execute(patientProfile).get()) { throw new Exception(); }
        } catch (Exception e) {
            throw new InterruptedException("syncPatientES error");
        }
    }

    /**
     * fetch patient profile from local storage and sync with DB
     * @return Patient (from DB)
     */
    public Patient fetchPatientProfile()
    {
        // if local storage doesn't have a profile, return null
        if (!deserializePatientProfile()) { return null; }
        // fetch Patient from DB, will overwrite local comments if it can, and override patient profile
        // if it finds a profile
        new Thread(new Runnable() {
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

    /**
     * will set the patient profile from DB and save it (when no local exists)
     * @param UserID : String (id to fetch with)
     */
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
    }

    /**
     * clear the local storage of the patient (when they log out)
     */
    public void clearPatientData()
    {
        try
        {
            patientProfile = null;
            mContext.getApplicationContext().deleteFile(P_FILENAME);
        } catch (Exception e)
        {
            Log.e("clearPatientData: ", "could not delete patient data!");
            e.printStackTrace();
        }
    }

    /**
     * sees if it can connect to the DB by seeing if the patient profile's id query works
     * SHOULD NOT BE CALLED BY CP code!
     * @return boolean (if we have connection with DB)
     */
    public boolean isConnected(){
        try {
            return userIDExists(patientProfile.getID()) == 0;
        } catch (Exception e)
        {
            Log.e("isConnected: ", "failure: most likely caused by null patientProfile being called by CP code.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * check if a patient with the given UserID exists
     * @param UserID : String
     * @return int (0 = Id exists, 1 Id does not exist, -1 fail to connect to DB)
     */
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

    // CP ONLY PULLS FROM DB WHEN LOGGING IN, AND EDITING PATIENTS!

    // list of patients assigned to the CP
    private ArrayList<Patient> m_patients = new ArrayList<>();

    // the id (login username) of the logged in CP
    private String CP_ID = null;

    /**
     * return the CP username
     * @return String
     */
    public String getCP_ID() {
        return CP_ID;
    }

    /**
     * set the CP username
     * @param CP_ID : String
     */
    public void setCP_ID(String CP_ID) {
        this.CP_ID = CP_ID;
    }

    /**
     * patient list adapts to this (returns assigned patients)
     * @return ArrayList<Patient> : CP's assigned patients
     */
    public ArrayList<Patient> getCPPatients() {
        return m_patients;
    }

    /**
     * add comment to assigned cp
     * @param PatientID : String
     * @param problemIndex : int
     * @param comment : String
     * @return boolean (if successful in adding comment)
     */
    public boolean addComment(String PatientID, int problemIndex, String comment)
    {
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) {
                patient.getProblemList().get(problemIndex).addComment(new Comment(comment));
                return UpdatePatient(patient);
            }
        }
        return false;
    }

    /**
     * add patient to CP, PatientID would be aquired from QR code or search by username
     * @param PatientID : String
     */
    public void AddPatient(String PatientID)
    {
        for (Patient p : m_patients) {
            if (p.getID().equals(PatientID)) {
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

    /**
     * syncs that patient with DB (to update comments), returns false if couldn't, true otherwise
     * @param patient : Patient
     * @return boolean (if successful in updating patient)
     */
    private boolean UpdatePatient(Patient patient)
    {
        ElasticsearchProblemController.SetPatientTask setPatientTask = new ElasticsearchProblemController.SetPatientTask();
        try {
            return setPatientTask.execute(patient).get();
        } catch (Exception e) { return false; }
    }

    /**
     * populate the assigned patients from db
     */
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

    /**
     * return cp's assigned patients
     * @return ArrayList<Patient> : Patients assigned to the cp
     */
    public ArrayList<Patient> GetPatients()
    {
        return m_patients;
    }

    /**
     * clears the patients (when CP logs out)
     */
    public void ClearPatients()
    {
        m_patients = new ArrayList<>();
    }

    /**
     * returns the problems of the patient with the ID patientID
     * @param PatientID : String
     * @return ArrayList<Problem> : problems of patient with PatientID
     */
    public ArrayList<Problem> GetCPPatientProblems(String PatientID)
    {
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient.getProblemList(); }
        }
        return null;
    }

    /**
     * returns the records of the patient with the ID patientID and a given problemIndex
     * @param PatientID : String
     * @param ProblemIndex : int
     * @return ArrayList<Record> : records of patient with PatientID and a given problemIndex
     */
    public ArrayList<Record> GetCPPatientRecords(String PatientID, int ProblemIndex)
    {
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient.getProblemList().get(ProblemIndex).getRecords(); }
        }
        return null;

    }

    /**
     * returns the record of the patient with the ID patientID and a given problemIndex and recordIndex
     * @param PatientID : String
     * @param ProblemIndex : int
     * @param RecordIndex : int
     * @return Record
     */
    public Record GetCPPatientRecord(String PatientID, int ProblemIndex, int RecordIndex)
    {
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient.getProblemList().get(ProblemIndex).getRecords().get(RecordIndex); }
        }
        return null;
    }

    /**
     * returns the problem of the patient with the ID patientID and a given problemIndex
     * @param PatientID : String
     * @param ProblemIndex : int
     * @return Problem
     */
    public Problem GetCPPatientProblem(String PatientID, int ProblemIndex)
    {
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient.getProblemList().get(ProblemIndex); }
        }
        return null;
    }

    /**
     * returns the patient class of a patient with a given patientID
     * @param PatientID : String
     * @return Patient
     */
    public Patient GetCPPatient(String PatientID)
    {
        for (Patient patient : m_patients )
        {
            if (patient.getID().equals(PatientID)) { return patient; }
        }
        return null;
    }

    /**
     * check if a cpIDExists by checking it against the DB
     * @param UserID
     * @return int : 0 if found, 1 if connected to DB but not found, -1 if exception or not connected to DB
     */
    public static int cpIDExists(String UserID)
    {
        ElasticsearchProblemController.CheckIfCPExistsTask checkTask =
                new ElasticsearchProblemController.CheckIfCPExistsTask();
        try {
            return (checkTask.execute(UserID).get());
        } catch (Exception e)
        {
            return -1;
        }
    }

    // SAVE CP STUFF -----------

    /**
     * (calls) save CP profile id to serialize
     */
    public void SaveCPProfile()
    {
        if (CP_ID == null) { return; }
        serializeCPProfile();
    }

    /**
     * clear local CP id
     */
    public void clearCPData()
    {
        mContext.getApplicationContext().deleteFile(CP_FILENAME);
    }

    /**
     * save cp id to file
     */
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

    /**
     * read the CP id from file (if exists)
     * @return boolean (if successful in reading file)
     */
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
            CP_ID = null;
            return false;
        }
    }
}
