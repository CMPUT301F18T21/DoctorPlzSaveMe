package com.erikligai.doctorplzsaveme;

import java.util.ArrayList;
import java.util.Date;

import io.searchbox.annotations.JestId;

public class Problem {
    String title;
    String description;
    Date date;
    ArrayList<Record> records = new ArrayList<>();
    ArrayList<Comment> comments = new ArrayList<>();
    @JestId
    String id;

    public Problem(String test, String new_problem) {
        this.title = test;
        this.description = new_problem;
        this.date = new Date();
    }

    public Problem(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

//    public void setTitle(String newTitle) {
//        this.title = newTitle;
//    }

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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void edit(String changed, String changes, String s) {
    }

    public void setTitle(String title) throws TooLongProblemTitleException{
        if (title.length()>30){
            throw new TooLongProblemTitleException("This title is too long! Please enter a comment with less than 30 character!");
        }
        this.title = title;
    }

    public void setDesc(String desc) throws TooLongProblemDescException{
        if (title.length()>300){
            throw new TooLongProblemDescException("This description is too long! Please enter a comment with less than 300 character!");
        }
        this.description = desc;
    }

    public void setDate(Date date){
        this.date = date;
    }

//     public void setRecords(Record record) {
//         records.add(record);
//     }

    public ArrayList<Record> getRecords() {
        return this.records;
    }
}
