package com.erikligai.doctorplzsaveme.Models;

import java.util.ArrayList;

public class CareProvider implements Profile {
    ArrayList<Patient> mPatientList = new ArrayList<>();
    String name;
    String userid;
    String email;
    String phone;

    /**
     * Creates a CareProvider (extends Profile)
     * @param name: Name of care provider
     * @param userid: user id of care provider
     * @param email: User's email
     * @param phone: User's phone number
     */
    public CareProvider(String name, String userid, String email, String phone) {
        this.name = name;
        this.userid = userid;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getUserid() {
        return this.userid;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    /**
     * Gets the patient list of the care provider
     * @return ArrayList<Patient> patient list of care provider
     */
    public ArrayList getPatientList() {
        return mPatientList;
    }

    /**
     * Add patient to caregiver's list
     * @param patient: Patient that is added
     * @return Nothing
     */
    public void addPatient(Patient patient) {
        mPatientList.add(patient);
    }


    /**
     * Returns if careprovider has given patient or not
     * @param patient: we want to check if patient exists in careprovider's list
     * @return boolean that returns true if patient is under careprovider, false if not
     */
    public boolean hasPatient(Patient patient) {
        return mPatientList.contains(patient);
    }
}

