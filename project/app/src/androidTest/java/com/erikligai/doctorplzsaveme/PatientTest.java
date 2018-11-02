package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

import java.util.ArrayList;

public class PatientTest extends TestCase {
    public void testAddProblem(){
        Patient patient = new Patient();
        Problem problem = new Problem("test","New Problem","07-07-07");
        patient.addProblem(problem);
        assertTrue(patient.hasProblem(problem));
    }
}
