package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

public class CareProviderTest extends TestCase {
    public void testAddPatient(){
        CareProvider careProvider = new CareProvider("name", "id", "email", "phone");
        Patient patient = new Patient("name", "id", "email", "phone");
        careProvider.addPatient(patient);
        assertTrue(careProvider.hasPatient(patient));
    }
}
