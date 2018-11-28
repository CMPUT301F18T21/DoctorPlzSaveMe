package com.erikligai.doctorplzsaveme.backend;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

public class Backend implements IPatientBackend {
    private static Backend instance = new Backend();

    private Patient patientProfile = null;

    public static Backend getInstance() {
        return instance;
    }

    public Backend() {}

    public void UpdatePatient() {
        // sync with DB, if possible, else set profile synced flag to false
    }

    public void setPatientProfile(Patient patientProfile) {
        this.patientProfile = patientProfile;
    }

    // ex. usage
    // if (Backend.getInstance().getPatientProfile() == null) {...}
    public Patient getPatientProfile() {
        if (patientProfile == null) {
            // get local profile, then if not synced, get from db, fail return null
            return null;
        }
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

}
