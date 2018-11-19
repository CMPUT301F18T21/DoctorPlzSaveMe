package com.erikligai.doctorplzsaveme;

public class Record {
    private Photo photo;
    private BodyLocation bodyLocation;
    private Geolocation geolocation;
    private String title;

    public Record(String title) {
        this.title = title;
    }

    public Record(String title, Photo photo, BodyLocation bodyLocation, Geolocation geolocation) {
        this.title = title;
        this.photo = photo;
        this.bodyLocation = bodyLocation;
        this.geolocation = geolocation;
    }

    public String getTitle() {
        return this.title;
    }

    public Geolocation getGeolocation() {
        return this.geolocation;
    }

    public BodyLocation getBodyLocation() {
        return this.bodyLocation;
    }

    public Photo getPhoto() {
        return this.photo;
    }

    public void addPhoto(Photo photo) {
        this.photo = photo;
    }

    public boolean hasPhoto() {
        return this.photo != null;
    }

    public void addBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocation = bodyLocation;
    }

    public boolean hasBodyLocation(BodyLocation bodyLocation) {
        return bodyLocation != null;
    }

    public void addGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public boolean hasGeolocation(Geolocation geolocation) {
        return this.geolocation != null;
    }
}
