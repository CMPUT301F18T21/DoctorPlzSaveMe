package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemDescException;
import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemTitleException;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;

import junit.framework.TestCase;

import java.util.Date;

public class PatientTest extends TestCase {

    public void testGetEmail(){
        Patient patient = new Patient("id", "email", "phone");
        assertEquals("email",patient.getEmail());
    }

    public void testSetEmail(){
        Patient patient = new Patient("id", "email", "phone");
        patient.setEmail("newEmail");
        assertEquals("newEmail",patient.getEmail());
    }

    public void testGetPhone(){
        Patient patient = new Patient("id", "email", "phone");
        assertEquals("phone",patient.getPhone());
    }

    public void testSetPhone(){
        Patient patient = new Patient("id", "email", "phone");
        patient.setEmail("newPhone");
        assertEquals("newPhone",patient.getEmail());
    }

    public void testAddPhoto(){
        Patient patient = new Patient("id", "email", "phone");
        patient.addPhoto("photoID","photo","photoLabel");
        assertTrue(patient.getPhotos().contains("photo"));
        assertTrue(patient.getPhotoIds().contains("photoID"));
        assertTrue(patient.getPhotoLabels().contains("photoLabel"));
    }

    public void testAddProblem(){
        Patient patient = new Patient("id", "email", "phone");
        Date date = new Date();
        Problem problem = null;
        try {
            problem = new Problem("test","New Problem", date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        patient.addProblem(problem);
        assertTrue(patient.hasProblem(problem));
    }

    // tests delete problem
    public void testDeleteProblem() {
        Patient patient = new Patient("id", "email", "phone");
        Date date = new Date();
        Problem problem = null;
        try {
            problem = new Problem("test","New Problem", date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        patient.addProblem(problem);
        patient.deleteProblem(problem);
        assertFalse(patient.getProblemList().contains(problem));
    }

}
