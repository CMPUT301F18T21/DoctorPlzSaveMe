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

    private static Backend instance = new Backend();

    private static final String FILENAME = "patient_profile.sav";

    private Context mContext;

    private Patient patientProfile = null;

    public static Backend getInstance() {
        return instance;
    }

    public Backend() {}

    public void setContext(Context context)
    {
        this.mContext = context;
    }

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

    public ArrayList<Problem> getPatientProblemList() {
        return patientProfile.getProblemList();
    }

    public void addPatientProblem(Problem problem) {
        patientProfile.addProblem(problem);
        UpdatePatient();
    }

    public void deletePatientProblem(int problemIndex) {
        patientProfile.deleteProblem(problemIndex);
        UpdatePatient();
    }

    public void addPatientRecord(int problemIndex, Record record) {
        patientProfile.getProblemList().get(problemIndex).addRecord(record);
        UpdatePatient();
    }

    public void deletePatientRecord(int problemIndex, int recordIndex) {
        patientProfile.getProblemList().get(problemIndex).getRecords().remove(recordIndex);
        UpdatePatient();
    }

    private void serializePatientProfile()
    {
        try {
            FileOutputStream fos = mContext.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(patientProfile, writer);
            writer.flush();
            writer.close();
            fos.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean deserializePatientProfile()
    {
        try {
            FileInputStream fis = mContext.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            Gson gson = new Gson();
            patientProfile = gson.fromJson(reader, Patient.class);
            return true;
        } catch (IOException e) {
            patientProfile = null;
            return false;
        }
    }

    private void syncPatientES()
    {
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
    }

    public Patient fetchPatientProfile() {
        // if local storage doesn't have a profile, return null
        if (!deserializePatientProfile()) { return null; }
        else
        {
            // fetch Patient from DB, will overwrite local if it can
            new Thread(new Runnable() {
                @Override
                public void run() {
                    syncPatientES();
                }
            }).start();
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
