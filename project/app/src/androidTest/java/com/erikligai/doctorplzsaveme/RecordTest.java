package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

public class RecordTest extends TestCase {
    public void testAddGeolocationToRecord(){
        Record record = new Record("title");
        Geolocation geolocation = new Geolocation();
        record.addGeolocation(geolocation);
        assertTrue(record.hasGeolocation(geolocation));
    }

    public void testAddBodyLocationToRecord(){
        Record record = new Record("tittle");
        BodyLocation bodyLocation = new BodyLocation();
        record.addBodyLocation(bodyLocation);
        assertTrue(record.hasBodyLocation(bodyLocation));
    }

    // test if photo gets added
    public void testAddPhotoToRecord(){
        Record record = new Record("title");
        Photo photo = new Photo();
        record.addPhoto(photo);
        assertTrue(record.hasPhoto());
    }
}
