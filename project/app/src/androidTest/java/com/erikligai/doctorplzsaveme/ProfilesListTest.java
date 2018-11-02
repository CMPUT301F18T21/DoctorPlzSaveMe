package com.erikligai.doctorplzsaveme;

import android.test.ActivityInstrumentationTestCase2;

public class ProfilesListTest extends ActivityInstrumentationTestCase2 {

    public ProfilesListTest(){
        super(com.erikligai.doctorplzsaveme.NewPatientActivity.class);
    }

    public void testAddPatient(){
        ProfilesList profiles = new ProfilesList();
        Profile patient = new Patient("me@test.ca","7805555555","TestMe");
        profiles.add(patient);
        assertTrue(profile.hasProfile(patient));
    }

    public void testAddProvider(){
        ProfilesList profiles = new ProfilesList();
        Profile provider = new Provider("me@test.ca","7805555555","TestMe");
        profiles.add(provider);
        assertTrue(profile.hasProfile(provider));
    }

}
