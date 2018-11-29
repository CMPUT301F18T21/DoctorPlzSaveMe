package com.erikligai.doctorplzsaveme.backend;

import com.erikligai.doctorplzsaveme.Models.Patient;

import java.util.ArrayList;

public interface ICareProviderBackend {

    // get patients of current logged in care provider
    public ArrayList<Patient> GetPatients();

    // clears patients (mainactivity)
    public void ClearPatients();

    // adds comment to the patient's problem and updates that patient profile to DB
    public void addComment(int patientIndex, int problemIndex, String comment);

    // add patient to CP, PatientID would be aquired from QR code
    public void AddPatient(String PatientID);

    // remove patient from CP (not required!) PatientID would be aquired from the Patient class
    public void RemovePatient(String PatientID);

    // don't worry about this
    public void PopulatePatients();
}
