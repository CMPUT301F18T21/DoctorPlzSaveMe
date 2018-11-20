package com.erikligai.doctorplzsaveme;

import java.util.ArrayList;

public class Patient implements Profile {
    private String name;
    private String id;
    private String email;
    private String phone;

    private ArrayList<Problem> mProblemList = new ArrayList<>();

    public Patient(String name, String id, String email, String phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void addProblem(Problem problem) {
        mProblemList.add(problem);
    }

    public boolean hasProblem(Problem problem) {
        return mProblemList.contains(problem);
    }

    public void deleteProblem(Problem problem) {
        mProblemList.remove(problem);
    }

    public ArrayList getProblemList() {
        return this.mProblemList;
    }


}
