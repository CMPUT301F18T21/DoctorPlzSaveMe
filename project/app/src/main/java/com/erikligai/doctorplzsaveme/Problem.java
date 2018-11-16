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
        this.date = date;
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

    public void setTitle(String title) throws TooLongProblemTitleException{
        if (title.length()>30){
            throw new TooLongProblemTitleException("This title is too long! Please enter a comment with less than 30 character!");
        }
        this.title = title;
    }

    public void setDesc(String desc) throws TooLongProblemDescException{
        if (title.length()>300){
            throw new TooLongProblemDescException("This description is too long! Please enter a comment with less than 30 character!");
        }
        this.description = desc;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
