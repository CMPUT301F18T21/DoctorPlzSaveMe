package com.erikligai.doctorplzsaveme.backend;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.StartAppActivities.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.util.ArrayList;

public class Backend implements IPatientBackend {

    // singleton code
    private static Backend instance = new Backend();
    public static Backend getInstance() {
        return instance;
    }
    private Backend() {}

    // TODO: move this filename to an xml
    private static final String FILENAME = "patient_profile.sav";

    private Context mContext = null; // context for reading to/from file
    // current patient profile
    private Patient patientProfile = null;

    // THIS MUST BE CALLED ON APP STARTUP!
    public void setContext(Context context)
    {
        this.mContext = context;
    }

    // THIS MUST BE CALLED WHENEVER A CHANGE TO PROFILE AND ITS MEMBERS IS MADE
    public void UpdatePatient() {
        // sync with DB
        new Thread(new Runnable() {
            @Override
            public void run() {
                serializePatientProfile();
                syncPatientES();
            }
        }).start();
    }

    public void setPatientProfile(Patient patientProfile) {
        this.patientProfile = patientProfile;
        UpdatePatient();
    }

    // ex. usage
    // if (Backend.getInstance().getPatientProfile() == null) {...}
    public Patient getPatientProfile() {
        assert(patientProfile != null);
        return patientProfile;
    }

    public ArrayList<Problem> getPatientProblems()
    {
        assert(patientProfile != null);
        return patientProfile.getProblemList();
    }

    public ArrayList<Record> getPatientRecords(int problemIndex)
    {
        assert(patientProfile != null);
        return patientProfile.getProblemList().get(problemIndex).getRecords();
    }

    public void addPatientProblem(Problem problem) {
        assert(patientProfile != null);
        patientProfile.addProblem(problem);
        UpdatePatient();
    }

    public void deletePatientProblem(int problemIndex) {
        assert(patientProfile != null);
        patientProfile.deleteProblem(problemIndex);
        UpdatePatient();
    }

    public void addPatientRecord(int problemIndex, Record record) {
        assert(patientProfile != null);
        patientProfile.getProblemList().get(problemIndex).addRecord(record);
        UpdatePatient();
    }

    public void deletePatientRecord(int problemIndex, int recordIndex) {
        assert(patientProfile != null);
        patientProfile.getProblemList().get(problemIndex).getRecords().remove(recordIndex);
        UpdatePatient();
    }

    private void serializePatientProfile()
    {
        try {
            assert(mContext != null);
            assert(patientProfile != null);
            FileOutputStream fos = mContext.getApplicationContext().openFileOutput(FILENAME, 0);
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
            FileInputStream fis = mContext.getApplicationContext().openFileInput(FILENAME);
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

    private void syncPatientES()
    {
        try {
            isConnected(); // will throw exception if phone is offline
            assert(patientProfile != null);
            String UserID = patientProfile.getID();
            Patient es_patient = null; // TODO: fetch from DB with UserID
            if (es_patient != null)
            {
            /* TODO:
            for each Problem from DB, take Problem.comments and overwrite local Problem comments
            (in case Care Provider has made new comments), then take local patientProfile and push
            it to the DB */
            }
            // else try push local patientProfile to DB (since might be new profile)
        } catch (Exception e)
        {
            // do nothing since we are offline
        }

    }

    public Patient fetchPatientProfile() {
        // if local storage doesn't have a profile, return null
        if (!deserializePatientProfile()) { return null; }
        else
        {
            // fetch Patient from DB, will overwrite local comments if it can
            new Thread(new Runnable() {
                @Override
                public void run() {
                    syncPatientES();
                }
            }).start();
            assert(patientProfile != null);
            return patientProfile;
        }
    }

    // https://stackoverflow.com/questions/9570237/android-check-internet-connection
    // razzak
    public static boolean isConnected() throws InterruptedException, IOException {
        final String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

    public static boolean userIDExists(String UserID)
    {
        // TODO: CHECK IF USERID EXISTS IN DB
        return false;
    }
}
