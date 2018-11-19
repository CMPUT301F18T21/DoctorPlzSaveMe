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

    public Problem(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return this.date;
    }

    public void edit(String changed, String changes, String s) {
    }

    public long details() {
        return blank;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);

    }

    public ArrayList getComments() {
        return this.comments;
    }
}
