package com.erikligai.doctorplzsaveme.backend;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

import java.util.ArrayList;

/**
 * This interface outlines the main CP backend features
 */
public interface ICareProviderBackend {

    // return cp's assigned patients
    public ArrayList<Patient> GetPatients();

    // clears the patients (when CP logs out)
    public void ClearPatients();

    // adds comment to the patient's problem and updates that patient profile to DB
    public boolean addComment(String PatientID, int problemIndex, String comment);

    // add patient to CP, PatientID would be aquired from QR code or search by username
    public void AddPatient(String PatientID);

    // patient list adapts to this (returns assigned patients)
    public ArrayList<Patient> getCPPatients();

    // returns the problems of the patient with the ID patientID
    public ArrayList<Problem> GetCPPatientProblems(String PatientID);

    // returns the records of the patient with the ID patientID and a given problemIndex
    public ArrayList<Record> GetCPPatientRecords(String PatientID, int ProblemIndex);

    // returns the record of the patient with the ID patientID and a given problemIndex and recordIndex
    public Record GetCPPatientRecord(String PatientID, int ProblemIndex, int RecordIndex);

    // returns the problem of the patient with the ID patientID and a given problemIndex
    public Problem GetCPPatientProblem(String PatientID, int ProblemIndex);

    // populate the assigned patients from db
    public void PopulatePatients();
}
