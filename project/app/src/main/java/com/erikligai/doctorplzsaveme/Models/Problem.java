package com.erikligai.doctorplzsaveme.Models;

import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemDescException;
import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemTitleException;
import java.util.ArrayList;
import java.util.Date;
import io.searchbox.annotations.JestId;

/**
 * Problem class that stores info about a patient's specific problem
 * Contains CP comments, Records belonging to the problem, etc
 */
public class Problem {
    private String Problem_title;
    private String Problem_description;
    private Date Problem_date;
    private ArrayList<Record> Problem_records = new ArrayList<>();
    private ArrayList<Comment> Problem_comments = new ArrayList<>();

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
        this.Problem_title = title;
        if (desc.length()>300){
            throw new TooLongProblemDescException("This description is too long! Please enter a comment with less than 300 character!");
        }
        this.Problem_description = desc;
        this.Problem_date = date;
    }

    /**
    *Returns the title of the problem
    *@return title String
     */
    public String getTitle() {
        return this.Problem_title;
    }

    /**
    *Returns the description of the problem
    *@return description String
     */
    public String getDescription() {
        return this.Problem_description;
    }

    /**
    *Returns the date of the problem
    *@return date Date
     */
    public Date getDate() {

        return this.Problem_date;
    }

    /**
     * Gets the patient's record list
     * @return ArrayList<Record> patient's record list
     */
    public ArrayList<Record> getRecords() {
        return this.Problem_records;
    }

    /**
     * Gets the patient's comment list
     * @return ArrayList<Comment> patient's comment list
     */
    public ArrayList<Comment> getComments() {
        return this.Problem_comments;
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
        this.Problem_title = title;
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
        this.Problem_description = desc;
    }

    /**
     *Sets the date of the problem to date
     *@return Nothing
     */
    public void setDate(Date date){
        this.Problem_date = date;
    }

    /**
    *Adds comment to problem's comment list
    *@param comment: String
    *@return Nothing
     */
    public void addComment(Comment comment) {
        this.Problem_comments.add(comment);
    }

    /**
    *Adds record to problem's record list
    *@param record: Record
    *@return Nothing
     */
    public void addRecord(Record record) {
        this.Problem_records.add(record);
    }

    /**
     * Set the comments of the record (used usually when we override the comments with the pulled
     * CP comments)
     * @param new_comments : ArrayList<Comment>
     */
    public void setComments(ArrayList<Comment> new_comments)
    {
        this.Problem_comments = new_comments;
    }

    /**
     * Return the number of records belonging to this problem
     * @return int
     */
    public int countRecords() {
        return Problem_records.size();
    }
}
