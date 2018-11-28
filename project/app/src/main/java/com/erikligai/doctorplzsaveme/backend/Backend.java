package com.erikligai.doctorplzsaveme.backend;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

public class Backend implements IPatientBackend {
    private static Backend instance = new Backend();

    public static Backend getInstance() {
        return instance;
    }

    public Backend() {}

    public void UpdatePatient() {}

    public void setPatientProfile(Patient patientProfile) {}

    // ex. usage
    // if (Backend.getInstance().getPatientProfile() == null) {...}
    public Patient getPatientProfile() { return null; }

    public void addPatientProblem(Problem problem) {}

    public void deletePatientProblem(int problemIndex) {}

    public void addPatientRecord(Record record) {}

    public void deletePatientRecord(int problemIndex, int recordIndex) {}

}
