package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

import java.util.Date;

public class PatientTest extends TestCase {
    public void testAddProblem(){
        Patient patient = new Patient("name");
        Date date = new Date();
        Problem problem = new Problem("test","New Problem", date);
        patient.addProblem(problem);
        assertTrue(patient.hasProblem(problem));
    }
}
