package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Models.Record;

import junit.framework.TestCase;

public class RecordTest extends TestCase {

    public void testGetTitle(){
        Record record = new Record("title","comment");
        assertEquals("title",record.getTitle());
    }

    public void testGetComment(){
        Record record = new Record("title","comment");
        assertEquals("comment",record.getComment());
    }

    public void testSetTitle(){
        Record record = new Record();
        record.setTitle("title");
        Record record1 = new Record("title","comment");
        record1.setTitle("newTitle");
        assertEquals("title",record.getTitle());
        assertEquals("newTitle",record1.getTitle());
    }

    public void testSetComment(){
        Record record = new Record();
        record.setComment("comment");
        Record record1 = new Record("title","comment");
        record1.setComment("newComment");
        assertEquals("comment",record.getComment());
        assertEquals("newComment",record1.getComment());
    }
/*
    public void testGeolocation(){
        Record record = new Record();
        LatLng geolocation = new LatLng(53.5444, -113.4909);
        record.setGeolocation(geolocation);
        assertEquals(geolocation,record.getGeolocation());
    }*/

    public void testPhotoID(){
        Record record = new Record();
        record.setPhotoid("photoID");
        assertEquals("photoID",record.getPhotoid());
    }

    public void testPos(){
        Record record = new Record();
        record.setXpos((float)2);
        record.setYpos((float)4);
        assertEquals((float)2,record.getXpos());
        assertEquals((float)4,record.getYpos());
    }
}