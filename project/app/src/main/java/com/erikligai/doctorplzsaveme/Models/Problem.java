package com.erikligai.doctorplzsaveme.Models;

import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemDescException;
import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemTitleException;

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

    /**
     * Creates a Problem
     * @param title: title of problem
     * @param desc: problem's description
     * @param date: problem's start date
     */
    public Problem(String title, String desc, Date date) throws TooLongProblemTitleException, TooLongProblemDescException{
        if (title.length()>30){
            throw new TooLongProblemTitleException("This title is too long! Please enter a comment with less than 30 character!");
        }
        this.title = title;
        if (desc.length()>300){
            throw new TooLongProblemDescException("This description is too long! Please enter a comment with less than 300 character!");
        }
        this.description = desc;
        this.date = date;
    }



    /**
    *Returns the title of the problem
    *@return title String
     */
    public String getTitle() {
        return this.title;
    }

    /**
    *Returns the description of the problem
    *@return description String
     */
    public String getDescription() {
        return description;
    }

    /**
    *Returns the date of the problem
    *@return date Date
     */
    public Date getDate() {

        return this.date;
    }


    /**
     * Gets the patient's record list
     * @return ArrayList<Record> patient's record list
     */
    public ArrayList<Record> getRecords() {
        return this.records;
    }

    /**
     * Gets the patient's comment list
     * @return ArrayList<Comment> patient's comment list
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
    }



    /**
     *Sets the title of the problem to title
     *@param title: String
     *@return Nothing
     *@throws TooLongProblemTitleException
     */
    public void setTitle(String title) throws TooLongProblemTitleException{
        if (title.length()>30){
            throw new TooLongProblemTitleException("This title is too long! Please enter a comment with less than 30 character!");
        }
        this.title = title;
    }

    /**
     *Sets the description of the problem to desc
     *@param desc: String
     *@return Nothing
     *@throws TooLongProblemTitleException
     */
    public void setDesc(String desc) throws TooLongProblemDescException {
        if (desc.length()>300){
            throw new TooLongProblemDescException("This description is too long! Please enter a comment with less than 300 character!");
        }
        this.description = desc;
    }

    /**
     *Sets the date of the problem to date
     *@return Nothing
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
    *Adds comment to problem's comment list
    *@param comment: String
    *@return Nothing
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    /**
    *Adds record to problem's record list
    *@param record: Record
    *@return Nothing
     */
    public void addRecord(Record record) {
        this.records.add(record);
    }

    public void setComments(ArrayList<Comment> new_comments)
    {
        this.comments = new_comments;
    }


       /**
    *Returns the id of the problem
    *@return id String
     */
    public String getId() {
        return this.id;
    }

       /**
    *Sets the id of the problem to id
    *@param id: String
    *@return Nothing
     */
    public void setId(String id) {
        this.id = id;
    }

    public int countRecords() {
        return records.size();
    }

}
