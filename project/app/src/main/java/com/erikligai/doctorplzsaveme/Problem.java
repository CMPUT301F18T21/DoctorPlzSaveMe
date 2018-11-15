package com.erikligai.doctorplzsaveme;

import java.util.ArrayList;
import java.util.Date;

public class Problem {
    String title;
    String description;
    Date date;
    long blank;
    ArrayList<Comment> comments = new ArrayList<Comment>();
    ArrayList<Record> records = new ArrayList<Record>();

    public Problem(String test, String new_problem, Date date) {
        this.title = test;
        this.description = new_problem;
        this.date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void edit(String changed, String changes, String s) {
    }

    public long details() {
        return blank;
    }

    public void addComment(Comment comment) {

    }
}
