package com.erikligai.doctorplzsaveme.backend;

import com.erikligai.doctorplzsaveme.Models.Patient;

import java.util.ArrayList;

public interface ICareProviderBackend {

    // patient list adapts to this
    public ArrayList<Patient> getPatients();

    // automatically stamps time onto comment and adds it to that problems comment arraylist
    public void addComment(int patientIndex, int problemIndex, String comment);
    
}
