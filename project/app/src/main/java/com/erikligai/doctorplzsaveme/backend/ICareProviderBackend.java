package com.erikligai.doctorplzsaveme.backend;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

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

    public ArrayList<Problem> GetCPPatientProblems(String PatientID);

    public ArrayList<Record> GetCPPatientRecords(String PatientID, int ProblemIndex);

    public Record GetCPPatientRecord(String PatientID, int ProblemIndex, int RecordIndex);

    public Problem GetCPPatientProblem(String PatientID, int ProblemIndex);

    public Patient GetCPPatient(String PatientID);

    // don't worry about this
    public void PopulatePatients();
}
