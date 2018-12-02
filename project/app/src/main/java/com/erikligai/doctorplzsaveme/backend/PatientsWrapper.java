package com.erikligai.doctorplzsaveme.backend;

import java.util.ArrayList;

/** wrapper for storing list of assigned patients for a CP (makes ES easier) */
public class PatientsWrapper {
    /**
     * returns patients that are wrapped (assigned patients)
     * @return ArrayList<String> : assigned patient ids
     */
    public ArrayList<String> getPatients() {
        return patients;
    }

    /**
     * set assigned patients (overwrite)
     * @param patients : ArrayList<String> assigned patient ids
     */
    public void setPatients(ArrayList<String> patients) {
        this.patients = patients;
    }

    private ArrayList<String> patients;

    /**
     * constructor that takes an arraylist of strings (patient ids)
     * @param p : ArrayList<String>
     */
    PatientsWrapper(ArrayList<String> p) { patients = p; }
}
