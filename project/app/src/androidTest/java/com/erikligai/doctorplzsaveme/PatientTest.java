package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;

import junit.framework.TestCase;

import java.util.Date;

public class PatientTest extends TestCase {
    public void testAddProblem(){
        Patient patient = new Patient("name", "id", "email", "phone");
        Date date = new Date();
        Problem problem = new Problem("test","New Problem", date);
        patient.addProblem(problem);
        assertTrue(patient.hasProblem(problem));
    }

    // tests delete problem
    public void testDeleteProblem() {
        Patient patient = new Patient("name", "id", "email", "phone");
        Date date = new Date();
        Problem problem = new Problem("test","New Problem", date);
        patient.addProblem(problem);
        patient.deleteProblem(problem);
        assertFalse(patient.getProblemList().contains(problem));
    }

}
