package com.erikligai.doctorplzsaveme;

import android.test.ActivityInstrumentationTestCase2;

public class RecordListTest extends ActivityInstrumentationTestCase2 {

    public RecordListTest(){
        super(com.erikligai.doctorplzsaveme.NewPatientActivity.class);
    }

    public void testAddEmptyRecord(){
        RecordList records = new RecordList();
        Record record = Record();
        records.add(record);
        assertTrue(records.hasRecord(record));
    }

    public void testAddGeolocationToRecord(){
        Record record = Record();
        Geolocation geolocation = new Geolocation();
        record.addGeolocation(geolocation);
        assertTrue(record.has(geolocation));
    }

    public void testAddBodyLocationToRecord(){
        Record record = Record();
        BodyLocation bodyLocation = new BodyLocation();
        record.addBodyLocation(BodyLocation);
        assertTrue(record.has(BodyLocation));
    }

    public void testAddPhotoToRecord(){
        Record record = Record();
        Photo photo = new Photo();
        record.addPhoto(photo);
        assertTrue(record.has(photo));
    }

    public void testAddTitleAndCommentToRecord(){
        Record record = Record();
        String titleAndComment = "(title,comment)";
        record.addTitleComment(titleAndComment);
        assertTrue(record.has(titleAndComment));
    }
}
