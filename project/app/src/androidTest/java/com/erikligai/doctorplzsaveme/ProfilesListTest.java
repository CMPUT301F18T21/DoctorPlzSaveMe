package com.erikligai.doctorplzsaveme;

//import junit.framework.TestCase;
//import com.google.common.truth.Truth.assertThat;

import junit.framework.TestCase;

import org.junit.Test;


//import junit.framework.TestCase;
//import android.test.ActivityInstrumentationTestCase2;

public class ProfilesListTest extends TestCase {

//    public ProfilesListTest(){
//        super(com.erikligai.doctorplzsaveme.MainActivity.class);
//    }

    @Test
    public void testAddPatientProfile(){
        ProfilesList profiles = new ProfilesList();
        Profile patient = new Patient("name");
        profiles.add(patient);
        assertTrue(profiles.hasProfile(patient));
    }

    @Test
    public void testAddProviderProfile(){
        ProfilesList profiles = new ProfilesList();
        Profile careProvider = new CareProvider("name");
        profiles.add(careProvider);
        assertTrue(profiles.hasProfile(careProvider));
    }

}
