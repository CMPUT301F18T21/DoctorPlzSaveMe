package com.erikligai.doctorplzsaveme;

import android.test.ActivityInstrumentationTestCase2;

public class PatientListTest extends ActivityInstrumentationTestCase2 {

    public PatientListTestsListTest(){
        super(com.erikligai.doctorplzsaveme.NewPatientActivity.class);
    }

    public void testAddPatient(){
        PatientList patients = new PatientList();
        Profile patient = new Patient("me@test.ca","7805555555","TestMe");
        patients.add(patient);
        assertTrue(patient.hasPatient(patient));
    }


}
