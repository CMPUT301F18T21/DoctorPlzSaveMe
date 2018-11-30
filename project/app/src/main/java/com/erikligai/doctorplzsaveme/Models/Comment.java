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
    private String comment;
    private String date;

    public Comment(String comment){
        this.comment = comment;
        this.date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
    /**
    *Re-assigns the comment String to newComment
    *@param newComment:
    *@return Nothing
     */
    public void editComment(String newComment) {
        this.comment = newComment;
    }
    /**
    *Returns the comment String of the Comment
    *@return comment String
     */
    public String getComment(){
        return this.comment;
    }
    /**
    *Returns the date of the Comment
    *@return date Date
     */
    public String getDate() {
        return this.date;
    }
}
