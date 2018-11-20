package com.erikligai.doctorplzsaveme;

import java.util.Date;

public class Record {
    private Photo photo;
    private BodyLocation bodyLocation;
    private Geolocation geolocation;
    private String title;
    private Date date;
    private String comment;

    /**
     * Creates a time-stamped Record 
     * @param title: title of record
     * @param description: record's comment
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


//     public Record(String title, Photo photo, BodyLocation bodyLocation, Geolocation geolocation) {
//         this.title = title;
//         this.photo = photo;
//         this.bodyLocation = bodyLocation;
//         this.geolocation = geolocation;
//     }

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
    public Geolocation getGeolocation() {
        return this.geolocation;
    }
     /**
    *Returns the bodylocation of the record
    *@return bodylocation Bodylocation
     */
    public BodyLocation getBodyLocation() {
        return this.bodyLocation;
    }
     /**
    *Returns the photo of the record
    *@return photo Photo
     */
    public Photo getPhoto() {
        return this.photo;
    }
    /**
    *Sets the photo of the record
    *@param photo: Photo
    *@return Nothing
     */
    public void addPhoto(Photo photo) {
        this.photo = photo;
    }
    /**
    *Checks if the record has a photo
    *@return boolean
     */
    public boolean hasPhoto() {
        return this.photo != null;
    }
    /**
    *Sets the bodylocation of the record
    *@param bodylocation: Bodylocation
    *@return Nothing
     */
    public void addBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocation = bodyLocation;
    }
     /**
    *Checks if the record has a bodylocation
    *@return boolean
     */
    public boolean hasBodyLocation(BodyLocation bodyLocation) {
        return bodyLocation != null;
    }
    /**
    *Sets the geolocation of the record
    *@param geolocation: Geolocation
    *@return Nothing
     */
    public void addGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }
     /**
    *Checks if the record has a geolocation
    *@return boolean
     */
    public boolean hasGeolocation(Geolocation geolocation) {
        return this.geolocation != null;
    }
}
