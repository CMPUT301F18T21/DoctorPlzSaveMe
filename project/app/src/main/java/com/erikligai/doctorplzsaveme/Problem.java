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

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
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

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addRecord(Record record) {
        this.records.add(record);
    }

    public ArrayList<Comment> getComments() {
        return this.comments;
    }

    public ArrayList<Record> getRecords() {
        return this.records;
    }
}
