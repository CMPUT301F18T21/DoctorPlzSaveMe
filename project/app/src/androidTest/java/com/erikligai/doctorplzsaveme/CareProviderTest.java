package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

import java.util.ArrayList;

public class CareProviderTest extends TestCase {
    public void testAddPatient(){
        CareProvider careProvider = new CareProvider("name", "id", "email", "phone");
        Patient patient = new Patient("name", "id", "email", "phone");
        careProvider.addPatient(patient);
        assertTrue(careProvider.hasPatient(patient));
    }

    // tests if getPatientList works
    public void testGetPatientList() {
        ArrayList<Patient> testList = new ArrayList<>();
        CareProvider careProvider = new CareProvider("name", "id", "email", "phone");
        Patient patient = new Patient("name", "id", "email", "phone");
        Patient patient2 = new Patient("name2", "id2", "email2", "phone2");
        testList.add(patient);
        testList.add(patient2);

        careProvider.addPatient(patient);
        careProvider.addPatient(patient2);

        assertEquals(testList, careProvider.getPatientList());
    }

    // tests if hasPatient works
    public void testHasPatient() {
        CareProvider careProvider = new CareProvider("name", "id", "email", "phone");
        Patient patient = new Patient("name", "id", "email", "phone");
        careProvider.addPatient(patient);
        assertEquals(careProvider.getPatientList().contains(patient), careProvider.hasPatient(patient));
    }
}
