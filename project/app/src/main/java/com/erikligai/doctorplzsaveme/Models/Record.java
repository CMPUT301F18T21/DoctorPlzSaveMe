package com.erikligai.doctorplzsaveme.Models;

import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Record {
    private LatLng geolocation;
    private String title;
    private Date date;
    private String comment;
    private String photoid;
    private float xpos;
    private float ypos;
    private ArrayList<String> photos;

    public Record() {
        this.date = new Date();
        this.title = "";
        this.comment = "";
        this.photos = new ArrayList<String>();
    }

    /**
     * Creates a time-stamped Record 
     * @param title: title of record
     * @param comment: record's comment
     *
     *
     */
    public Record(String title, String comment){
        this.title = title;
        this.date = new Date();
        this.comment = comment;
    }
     /**
    *Returns the comment of the record
    *@return comment String
     */
    public String getComment() {
        return this.comment;
    }
      /**
    *Returns the date of the record
    *@return date Date
     */
    public Date getDate() {
        return this.date;
    }

    /**
    *Returns the title of the record
    *@return title String
     */
    public String getTitle() {
        return this.title;
    }

    /**
    *Returns the geolocation of the record
    *@return geolocation Geolocation
     */
    public LatLng getGeolocation() {
        return this.geolocation;
    }


    public float getXpos() {
        return xpos;
    }

    public float getYpos() {
        return ypos;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setGeolocation(LatLng geolocation) {
        this.geolocation = geolocation;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    public void setYpos(float ypos) {
        this.ypos = ypos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }


    public String getPhotoid() {
        return photoid;
    }
}

