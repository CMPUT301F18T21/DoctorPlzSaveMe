package com.erikligai.doctorplzsaveme.backend;

import android.content.Context;

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
        return patientProfile;
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

    private boolean serializePatientProfile()
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
            return true;
        } catch (IOException e) {
            return false;
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
        Patient es_patient = null; // fetch from DB with UserID
        if (es_patient != null)
        {
            // for each Problem from DB, take Problem.comments and overwrite local Problem comments
            // (in case Care Provider has made new comments), then take local patientProfile and push
            // it to the DB
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
}
