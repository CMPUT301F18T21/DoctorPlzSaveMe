package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

public class CareProviderTest extends TestCase {
    public void testAddPatient(){
        PatientList patients = new PatientList();
        Profile patient = new Patient("me@test.ca","7805555555","TestMe");
        patients.add(patient);
        assertTrue(patient.hasPatient(patient));
    }
}
