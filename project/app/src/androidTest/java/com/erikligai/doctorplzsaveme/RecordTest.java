package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Models.BodyLocation;
import com.erikligai.doctorplzsaveme.Models.Geolocation;
import com.erikligai.doctorplzsaveme.Models.Photo;
import com.erikligai.doctorplzsaveme.Models.Record;

import junit.framework.TestCase;

public class RecordTest extends TestCase {
    public void testAddGeolocationToRecord(){
        Record record = new Record("Testing Title", "Testing comment.");
        Geolocation geolocation = new Geolocation();
        record.addGeolocation(geolocation);
        assertTrue(record.hasGeolocation(geolocation));
    }

    public void testAddBodyLocationToRecord(){
        Record record = new Record("Testing Title", "Testing comment.");
        BodyLocation bodyLocation = new BodyLocation();
        record.addBodyLocation(bodyLocation);
        assertTrue(record.hasBodyLocation(bodyLocation));
    }

    // test if photo gets added
    public void testAddPhotoToRecord(){
        Record record = new Record("Testing Title", "Testing comment.");
        Photo photo = new Photo();
        record.addPhoto(photo);
        assertTrue(record.hasPhoto());
    }
}