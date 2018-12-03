package com.erikligai.doctorplzsaveme.Models;

import java.util.ArrayList;

/**
 * Patient class, stores all info relating to that patient ('root of the data tree')
 * We pull/push this whole class from the DB at once so we have access to all the data
 */
public class Patient {
    private String id;
    private String email;
    private String phone;
    private ArrayList<String> photoLabels;
    private ArrayList<String> photoIds;
    private ArrayList<String> photos;
    private ArrayList<Problem> mProblemList;

     /**
     * Creates a Patient (extends Profile)
     * @param id: user id of patient
     * @param email: User's email
     * @param phone: User's phone number
     */
    public Patient(String id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.photoIds = new ArrayList<String>();
        this.photos = new ArrayList<String>();
        this.mProblemList = new ArrayList<Problem>();
        this.photoLabels = new ArrayList<String>();
        photos.add("");
        photos.add("");
        photoIds.add("front");
        photoIds.add("back");
        photoLabels.add("Default Front");
        photoLabels.add("Default Back");
    }

    /**
     * Add a photo (Body Location Photo) to this patient
     * @param id : String
     * @param photo :String
     * @param PhotoLabel : String
     */
    public void addPhoto(String id, String photo, String PhotoLabel){
        this.photoIds.add(id);
        this.photos.add(photo);
        this.photoLabels.add(PhotoLabel);
    }

    /**
     * Adds a label to a body location photo
     * @param i : Integer
     * @param photoLabel : String
     */
    public void addPhotoLabel(Integer i, String photoLabel){
        this.photoLabels.add(i,photoLabel);
    }

    /**
     * Returns the photo ids (so we can index the photos)
     * @return ArrayList<String> (photoIds)
     */
    public ArrayList<String> getPhotoIds(){
        return this.photoIds;
    }

    /**
     * Returns the photos (in String form)
     * @return ArrayList<String>
     */
    public ArrayList<String> getPhotos(){
        return this.photos;
    }

    /**
     * Return the photo labels
     * @return ArrayList<String>
     */
    public ArrayList<String> getPhotoLabels(){
        return this.photoLabels;
    }

    /**
     * set the email of the patient
     * @param newEmail : String
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    /**
    * Set the user's phone to newPhone
    * @param newPhone: String
    * @return Nothing 
    */
    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

     /**
    *Returns the name String of the user
    *@return name String
     */
    public String getID() {
        return this.id;
    }

     /**
    *Returns the email String of the user
    *@return email String
     */
    public String getEmail() {
        return this.email;
    }

     /**
    *Returns the phone String of the user
    *@return phone String
     */
    public String getPhone() {
        return this.phone;
    }

     /**
     * Add problem to patient's problem list
     * @param problem: Problem that is added
     * @return Nothing
     */
    public void addProblem(Problem problem) {
        mProblemList.add(problem);
    }

     /**
     * Returns if patient's problem list has given problem or not
     * @param problem: we want to check if problem exists in patient's problem list
     * @return boolean that returns true if problem is in patient's problem list, false if not
     */
    public boolean hasProblem(Problem problem) {
        return mProblemList.contains(problem);
    }

    /**
     * Removes problem from patient's problem list
     * @param problem: we want to remove from patient's problem list
     * @return Nothing
     */
    public void deleteProblem(Problem problem) {
        mProblemList.remove(problem);
    }

     /**
     * Gets the patient's problem list 
     * @return ArrayList<ProblemList> patient's problem list
     */
     public void deleteProblem(int index) {
         mProblemList.remove(index);
     }

    /**
     * Return the list of problems of this patient
     * @return ArrayList<Problem>
     */
    public ArrayList<Problem> getProblemList() {
        return this.mProblemList;
    }



}
