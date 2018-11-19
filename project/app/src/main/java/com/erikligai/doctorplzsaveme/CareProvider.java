package com.erikligai.doctorplzsaveme;

import java.util.ArrayList;

public class CareProvider implements Profile {
    ArrayList<Patient> mPatientList = new ArrayList<>();
    String name;
    String id;
    String email;
    String phone;

    public CareProvider(String name, String id, String email, String phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public void addPatient(Patient patient) {
        mPatientList.add(patient);
    }

    public ArrayList getPatientList() {
        return mPatientList;
    }

    public boolean hasPatient(Patient patient) {
        return mPatientList.contains(patient);
    }
}

