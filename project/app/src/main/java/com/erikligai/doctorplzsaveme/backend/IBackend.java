package com.erikligai.doctorplzsaveme.backend;

import com.erikligai.doctorplzsaveme.Patient;
import com.erikligai.doctorplzsaveme.Problem;
import com.erikligai.doctorplzsaveme.Record;

public interface IBackend {

    // sync patientProfile with local storage/DB
    // SHOULD BE CALLED WHENEVER ANYTHING CHANGES WITH THE DATA!!!
    public void UpdatePatient();

    public void setPatientProfile(Patient patientProfile);

    public Patient getPatientProfile();

    public void addPatientProblem(Problem problem);

    public void deletePatientProblem(int problemIndex);

    public void addPatientRecord(Record record);

    public void deletePatientRecord(int problemIndex, int recordIndex);


}
