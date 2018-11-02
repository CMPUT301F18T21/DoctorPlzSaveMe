package com.erikligai.doctorplzsaveme;
import junit.framework.Test;
import junit.framework.TestCase;

public class PatientListTest extends TestCase {

    public PatientListTest(){
        super(com.erikligai.doctorplzsaveme.NewPatientActivity.class);
    }

    public void testAddPatient(){
        PatientList patients = new PatientList();
        Profile patient = new Patient("me@test.ca","7805555555","TestMe");
        patients.add(patient);
        assertTrue(patient.hasPatient(patient));
    }

}
