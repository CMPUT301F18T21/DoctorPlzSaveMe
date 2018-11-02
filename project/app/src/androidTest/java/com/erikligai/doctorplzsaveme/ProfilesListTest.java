package com.erikligai.doctorplzsaveme;

//import junit.framework.TestCase;
import com.google.common.truth.Truth.assertThat;

import junit.framework.TestCase;


import org.junit.Test;

//import junit.framework.TestCase;
//import android.test.ActivityInstrumentationTestCase2;

public class ProfilesListTest extends TestCase {

    public ProfilesListTest(){
        super(com.erikligai.doctorplzsaveme.MainActivity.class);
    }

    @Test
    public void testAddPatient(){
        ProfilesList profiles = new ProfilesList();
        Profile patient = new Patient("me@test.ca","7805555555","TestMe");
        profiles.add(patient);
        assertTrue(profiles.hasProfile(patient));
    }

    @Test
    public void testAddProvider(){
        ProfilesList profiles = new ProfilesList();
        Profile careProvider = new CareProvider("me@test.ca","7805555555","TestMe");
        profiles.add(careProvider);
        assertTrue(profiles.hasProfile(careProvider));
    }

}
