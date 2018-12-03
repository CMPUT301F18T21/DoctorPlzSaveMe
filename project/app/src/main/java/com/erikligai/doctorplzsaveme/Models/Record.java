package com.erikligai.doctorplzsaveme.Models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

/**
 * Record class containing all data belonging to a record of a problem of a patient
 */
public class Record {
    private LatLng geolocation;
    private String title;
    private Date date;
    private String comment;
    private String photoid;
    private float xpos;
    private float ypos;
    private ArrayList<String> photos;

    /**
     * Empty constructor for the Record, sets default values (utility constructor)
     */
    public Record() {
        this.date = new Date();
        this.title = "";
        this.comment = "";
        this.photos = new ArrayList<String>();
        this.photoid = "";
    }

    /**
     * Creates a time-stamped Record 
     * @param title: title of record
     * @param comment: record's comment

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

    /**
     * Return the photoid (which body location its using)
     * @return String
     */
    public String getPhotoid() {
        return photoid;
    }

    /**
     * Return the x position of the body location photo
     * @return float
     */
    public float getXpos() {
        return xpos;
    }

    /**
     * Return the y position of the body location photo
     * @return float
     */
    public float getYpos() {
        return ypos;
    }

    /**
     * Get the photos of the Record in String form
     * @return ArrayList<String>
     */
    public ArrayList<String> getPhotos() {
        return photos;
    }

    /**
     * Set the title of the record
     * @param title : String
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Set the comment of the record
     * @param comment : String
     */
    public void setComment(String comment){
        this.comment = comment;
    }

    /**
     * Set the date of the record
     * @param date : Date
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Set the geolocation of the record
     * @param geolocation : LatLng
     */
    public void setGeolocation(LatLng geolocation) {
        this.geolocation = geolocation;
    }

    /**
     * Set the photo id (which BLP, used to try to index patient's BLPs)
     * @param photoid : String
     */
    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    /**
     * set the photo xpos (which BLP, used to try to index patient's BLPs)
     * @param xpos : float
     */
    public void setXpos(float xpos) {
        this.xpos = xpos;
    }

    /**
     * set the photo ypos (which BLP, used to try to index patient's BLPs)
     * @param ypos : float
     */
    public void setYpos(float ypos) {
        this.ypos = ypos;
    }

    /**
     * Set the photos of the record
     * @param photos : ArrayList<String>
     */
    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
}

