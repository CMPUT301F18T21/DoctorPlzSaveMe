package com.erikligai.doctorplzsaveme;

import java.util.Date;

public class Record {
    private String title;
    private Date date;
    private String comment;
    private Photo photo;
    private BodyLocation bodyLocation;
    private Geolocation geoLocation;

    public Record(String title,Date date, String comment){
        this.title = title;
        this.date = new Date();
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    public void addPhoto(Photo photo) {

    }

    public boolean hasPhoto(Photo photo) {
        return true;
    }

    public void addBodyLocation(BodyLocation bodyLocation) {

    }

    public boolean hasBodyLocation(BodyLocation bodyLocation) {
        return true;
    }

    public void addGeolocation(Geolocation geolocation) {

    }

    public boolean hasGeolocation(Geolocation geolocation) {
        return true;
    }
}
