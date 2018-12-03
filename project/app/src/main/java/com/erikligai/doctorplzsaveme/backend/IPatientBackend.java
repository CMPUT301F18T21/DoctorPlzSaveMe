package com.erikligai.doctorplzsaveme.backend;

import android.content.Context;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

import java.util.ArrayList;

/**
 * This interface outlines the main patient backend features
 * Javadoc is done in Backend
 */
public interface IPatientBackend {

    // THIS MUST BE CALLED WHENEVER A CHANGE TO PROFILE AND ITS MEMBERS IS MADE
    // syncs the current patient with DB (if it can) by fetching DB comments (if CP has made
    // a change to comments, overriding the local comments, and pushing the local profile to DB)
    public boolean UpdatePatient();

    // return the current patient profile
    public Patient getPatientProfile();

    // get the current patient profile's problems
    public ArrayList<Problem> getPatientProblems();

    // get the current patient profiles' records given a problem index
    public ArrayList<Record> getPatientRecords(int problemIndex);

    // add a problem to the current patient
    public void addPatientProblem(Problem problem);

    // delete a patient's problem given an index (from a recyclerview most likely)
    public void deletePatientProblem(int problemIndex);

    // add a patient record given the problem index and Record object
    public void addPatientRecord(int problemIndex, Record record);

    // delete a patient record given a problem index and record index (obtained from recyclerviews)
    public void deletePatientRecord(int problemIndex, int recordIndex);


    // MUST BE CALLED UPON APP STARTUP TO BE ABLE TO READ/WRITE TO FILE
    public void setContext(Context context);
    public boolean setPatientProfile(Patient patientProfile);

}
