package com.erikligai.doctorplzsaveme;

import android.test.ActivityInstrumentationTestCase2;

public class CreateProfileTest extends ActivityInstrumentationTestCase2 {

    public CreateProfileTest(){
        super(com.erikligai.doctorplzsaveme.NewPatientActivity.class);
    }

    public void testCreateProfile(){
        ProfilesList profiles = new ProfilesList();
        Profile profile = new Profile("me@test.ca","7805555555","TestMe");
        profiles.add(profile);
        assertTrue(profile.hasProfile(profile));
    }

}
