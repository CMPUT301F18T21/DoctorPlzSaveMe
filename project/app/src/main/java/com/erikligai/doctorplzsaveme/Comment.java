package com.erikligai.doctorplzsaveme;

public class Comment {
    private String theComment;
    Comment(String givenStr){
        theComment = givenStr;
    }
    public void setComment(String changed)
    {
        theComment = changed;
    }
    public String getComment(){
        return theComment;
    }
}
