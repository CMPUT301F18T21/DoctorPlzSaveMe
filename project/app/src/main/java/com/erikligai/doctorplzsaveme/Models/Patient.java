package com.erikligai.doctorplzsaveme.Models;

import java.util.ArrayList;

public class Patient implements Profile {
    private String id;
    private String email;
    private String phone;

    private ArrayList<Problem> mProblemList = new ArrayList<>();
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
    }

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
     public ArrayList<Problem> getProblemList() {
        return this.mProblemList;
    }


}
