package com.erikligai.doctorplzsaveme.backend;

import java.util.ArrayList;

// for ES

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
