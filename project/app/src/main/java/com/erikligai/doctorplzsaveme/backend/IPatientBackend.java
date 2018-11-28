package com.erikligai.doctorplzsaveme.backend;

import android.content.Context;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

public interface IPatientBackend {

    // sync patientProfile with local storage/DB
    // SHOULD BE CALLED WHENEVER ANYTHING CHANGES WITH THE DATA!!!
    public void UpdatePatient();

    public void setContext(Context context);

    public void setPatientProfile(Patient patientProfile);

    public Patient getPatientProfile();

    public void addPatientProblem(Problem problem);

    public void deletePatientProblem(int problemIndex);

    public void addPatientRecord(int problemIndex, Record record);

    public void deletePatientRecord(int problemIndex, int recordIndex);


}
