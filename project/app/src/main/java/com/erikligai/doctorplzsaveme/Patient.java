package com.erikligai.doctorplzsaveme;

public class Patient implements Profile {
    private String name;
    private String id;
    private String email;
    private String phone;

    public Patient(String name, String id, String email, String phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
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
    }

    public boolean hasProblem(Problem problem) {
        return true;
    }


}
