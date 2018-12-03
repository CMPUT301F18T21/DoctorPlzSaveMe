package com.erikligai.doctorplzsaveme.Models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

/**
 * Record class containing all data belonging to a record of a problem of a patient
 */
public class Record {
    private LatLng Record_geolocation;
    private String Record_title;
    private Date Record_date;
    private String Record_comment;
    private String Record_photoid;
    private float Record_xpos;
    private float Record_ypos;
    private ArrayList<String> Record_photos;

    /**
     * Empty constructor for the Record, sets default values (utility constructor)
     */
    public Record() {
        this.Record_date = new Date();
        this.Record_title = "";
        this.Record_comment = "";
        this.Record_photos = new ArrayList<String>();
        this.Record_photoid = "";
    }

    /**
     * Creates a time-stamped Record 
     * @param title: title of record
     * @param comment: record's comment

     */
    public Record(String title, String comment){
        this.Record_title = title;
        this.Record_date = new Date();
        this.Record_comment = comment;
    }

     /**
    *Returns the comment of the record
    *@return comment String
     */
    public String getComment() {
        return this.Record_comment;
    }

      /**
    *Returns the date of the record
    *@return date Date
     */
    public Date getDate() {
        return this.Record_date;
    }

    /**
    *Returns the title of the record
    *@return title String
     */
    public String getTitle() {
        return this.Record_title;
    }

    /**
    *Returns the geolocation of the record
    *@return geolocation Geolocation
     */
    public LatLng getGeolocation() {
        return this.Record_geolocation;
    }

    /**
     * Return the photoid (which body location its using)
     * @return String
     */
    public String getPhotoid() {
        return Record_photoid;
    }

    /**
     * Return the x position of the body location photo
     * @return float
     */
    public float getXpos() {
        return Record_xpos;
    }

    /**
     * Return the y position of the body location photo
     * @return float
     */
    public float getYpos() {
        return Record_ypos;
    }

    /**
     * Get the photos of the Record in String form
     * @return ArrayList<String>
     */
    public ArrayList<String> getPhotos() {
        return Record_photos;
    }

    /**
     * Set the title of the record
     * @param title : String
     */
    public void setTitle(String title){
        this.Record_title = title;
    }

    /**
     * Set the comment of the record
     * @param comment : String
     */
    public void setComment(String comment){
        this.Record_comment = comment;
    }

    /**
     * Set the date of the record
     * @param date : Date
     */
    public void setDate(Date date){
        this.Record_date = date;
    }

    /**
     * Set the geolocation of the record
     * @param geolocation : LatLng
     */
    public void setGeolocation(LatLng geolocation) {
        this.Record_geolocation = geolocation;
    }

    /**
     * Set the photo id (which BLP, used to try to index patient's BLPs)
     * @param photoid : String
     */
    public void setPhotoid(String photoid) {
        this.Record_photoid = photoid;
    }

    /**
     * set the photo xpos (which BLP, used to try to index patient's BLPs)
     * @param xpos : float
     */
    public void setXpos(float xpos) {
        this.Record_xpos = xpos;
    }

    /**
     * set the photo ypos (which BLP, used to try to index patient's BLPs)
     * @param ypos : float
     */
    public void setYpos(float ypos) {
        this.Record_ypos = ypos;
    }

    /**
     * Set the photos of the record
     * @param photos : ArrayList<String>
     */
    public void setPhotos(ArrayList<String> photos) {
        this.Record_photos = photos;
    }
}

