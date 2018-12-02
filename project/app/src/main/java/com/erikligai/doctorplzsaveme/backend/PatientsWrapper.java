package com.erikligai.doctorplzsaveme.backend;

import java.util.ArrayList;

/** wrapper for storing list of assigned patients for a CP (makes ES easier) */
public class PatientsWrapper {
    public ArrayList<String> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<String> patients) {
        this.patients = patients;
    }

    private ArrayList<String> patients;

    PatientsWrapper(ArrayList<String> p) { patients = p; }
}
