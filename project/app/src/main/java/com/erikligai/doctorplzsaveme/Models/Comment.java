package com.erikligai.doctorplzsaveme.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
*Comment
*
*A text made by a CareProvider on a Patient's Problem.
*Has a comment (String) and date (Date).
 */
public class Comment {
    private String Comment_comment;
    private String Comment_date;

    /**
     * Constructor for Comment that takes a comment (String) ands uses the current time to set
     * the comment's date
     * @param comment : String
     */
    public Comment(String comment){
        this.Comment_comment = comment;
        this.Comment_date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    /**
    *Re-assigns the comment String to newComment
    *@param newComment: String
    *@return Nothing
     */
    public void editComment(String newComment) {
        this.Comment_comment = newComment;
    }
    /**
    *Returns the comment String of the Comment
    *@return comment : String
     */
    public String getComment(){
        return this.Comment_comment;
    }
    /**
    *Returns the date of the Comment
    *@return date : Date
     */
    public String getDate() {
        return this.Comment_date;
    }
}
