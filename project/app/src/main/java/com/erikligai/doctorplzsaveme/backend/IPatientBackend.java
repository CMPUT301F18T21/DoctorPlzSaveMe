package com.erikligai.doctorplzsaveme.backend;

import android.content.Context;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

import java.util.ArrayList;

public interface IPatientBackend {

    // sync patientProfile with local storage/DB
    // SHOULD BE CALLED WHENEVER ANYTHING CHANGES WITH THE DATA!!! (dont forget)
    public void UpdatePatient();

    public Patient getPatientProfile();

    public ArrayList<Problem> getPatientProblems();

    public ArrayList<Record> getPatientRecords(int problemIndex);

    public void addPatientProblem(Problem problem);

    public void deletePatientProblem(int problemIndex);

    public void addPatientRecord(int problemIndex, Record record);

    public void deletePatientRecord(int problemIndex, int recordIndex);



    // don't worry about these (Daniil's responsibility)

    // MUST BE CALLED UPON APP STARTUP TO BE ABLE TO READ/WRITE TO FILE
    public void setContext(Context context);

    public void setPatientProfile(Patient patientProfile);

}
