package com.erikligai.doctorplzsaveme.Models;

import com.erikligai.doctorplzsaveme.TooLongProblemDescException;
import com.erikligai.doctorplzsaveme.TooLongProblemTitleException;

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

    public Problem(String title, String description) {
        try{
            setTitle(title);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        }
        this.description = description;
        this.date = new Date();
    }
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
    

    public Problem(){
    }

//    public void setTitle(String newTitle) {
//        this.title = newTitle;
//    }

    /**
    * Set the problem's description to newDescription
    * @param newDescription: String
    * @return Nothing 
    */
    public void setDescription(String newDescription) {
        this.description = newDescription;
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
     * Gets the patient's comment list 
     * @return ArrayList<Comment> patient's comment list 
     */
    public ArrayList<Comment> getComments() {
        return this.comments;
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

    /**
    *edit 
    *@param changed: String 
    *@param changes: String
    *@param s: String 
    *@return Nothing
    */
    public void edit(String changed, String changes, String s) {
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

//     public void setRecords(Record record) {
//         records.add(record);
//     }

     /**
     * Gets the patient's record list 
     * @return ArrayList<Record> patient's record list 
     */
    public ArrayList<Record> getRecords() {
        return this.records;
    }
}
