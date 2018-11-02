package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

public class CareProviderTest extends TestCase {
    public void testAddPatient(){
        CareProvider careProvider = new CareProvider("name");
        Patient patient = new Patient("name");
        careProvider.addPatient(patient);
        assertTrue(careProvider.hasPatient(patient));
    }
}
