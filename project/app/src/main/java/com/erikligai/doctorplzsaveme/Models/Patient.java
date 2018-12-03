package com.erikligai.doctorplzsaveme.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Patient class, stores all info relating to that patient ('root of the data tree')
 * We pull/push this whole class from the DB at once so we have access to all the data
 */
public class Patient {
    private String Patient_id;
    private String Patient_email;
    private String Patient_phone;
    private ArrayList<String> Patient_photoLabels;
    private ArrayList<String> Patient_photoIds;
    private ArrayList<String> Patient_photos;
    private ArrayList<Problem> Patient_mProblemList;

     /**
     * Creates a Patient (extends Profile)
     * @param id: user id of patient
     * @param email: User's email
     * @param phone: User's phone number
     */
    public Patient(String id, String email, String phone) {
        this.Patient_id = id;
        this.Patient_email = email;
        this.Patient_phone = phone;
        this.Patient_photoIds = new ArrayList<String>();
        this.Patient_photos = new ArrayList<String>();
        this.Patient_mProblemList = new ArrayList<Problem>();
        this.Patient_photoLabels = new ArrayList<String>();
        Patient_photos.add("");
        Patient_photos.add("");
        Patient_photoIds.add("front");
        Patient_photoIds.add("back");
        Patient_photoLabels.add("Default Front");
        Patient_photoLabels.add("Default Back");
    }

    /**
     * Add a photo (Body Location Photo) to this patient
     * @param id : String
     * @param photo :String
     * @param PhotoLabel : String
     */
    public void addPhoto(String id, String photo, String PhotoLabel){
        this.Patient_photoIds.add(id);
        this.Patient_photos.add(photo);
        this.Patient_photoLabels.add(PhotoLabel);
    }

    /**
     * Adds a label to a body location photo
     * @param i : Integer
     * @param photoLabel : String
     */
    public void addPhotoLabel(Integer i, String photoLabel){
        this.Patient_photoLabels.add(i,photoLabel);
    }

    /**
     * Returns the photo ids (so we can index the photos)
     * @return ArrayList<String> (photoIds)
     */
    public ArrayList<String> getPhotoIds(){
        return this.Patient_photoIds;
    }

    /**
     * Returns the photos (in String form)
     * @return ArrayList<String>
     */
    public ArrayList<String> getPhotos(){
        return this.Patient_photos;
    }

    /**
     * Return the photo labels
     * @return ArrayList<String>
     */
    public ArrayList<String> getPhotoLabels(){
        return this.Patient_photoLabels;
    }

    /**
     * set the email of the patient
     * @param newEmail : String
     */
    public void setEmail(String newEmail) {
        this.Patient_email = newEmail;
    }

    /**
    * Set the user's phone to newPhone
    * @param newPhone: String
    * @return Nothing 
    */
    public void setPhone(String newPhone) {
        this.Patient_phone = newPhone;
    }

     /**
    *Returns the name String of the user
    *@return name String
     */
    public String getID() {
        return this.Patient_id;
    }

     /**
    *Returns the email String of the user
    *@return email String
     */
    public String getEmail() {
        return this.Patient_email;
    }

     /**
    *Returns the phone String of the user
    *@return phone String
     */
    public String getPhone() {
        return this.Patient_phone;
    }

     /**
     * Add problem to patient's problem list
     * @param problem: Problem that is added
     * @return Nothing
     */
    public void addProblem(Problem problem) {
        Patient_mProblemList.add(problem);
    }

     /**
     * Returns if patient's problem list has given problem or not
     * @param problem: we want to check if problem exists in patient's problem list
     * @return boolean that returns true if problem is in patient's problem list, false if not
     */
    public boolean hasProblem(Problem problem) {
        return Patient_mProblemList.contains(problem);
    }

    /**
     * Removes problem from patient's problem list
     * @param problem: we want to remove from patient's problem list
     * @return Nothing
     */
    public void deleteProblem(Problem problem) {
        Patient_mProblemList.remove(problem);
    }

     /**
     * Gets the patient's problem list 
     * @return ArrayList<ProblemList> patient's problem list
     */
     public void deleteProblem(int index) {
         Patient_mProblemList.remove(index);
     }

    /**
     * Return the list of problems of this patient
     * @return ArrayList<Problem>
     */
    public ArrayList<Problem> getProblemList() {
        return this.Patient_mProblemList;
    }



}
