package com.erikligai.doctorplzsaveme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Comment {
    private String comment;
    private String date;

    Comment(String comment){
        this.comment = comment;
        this.date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }

    public void editComment(String newComment) {
        this.comment = newComment;
    }

    public String getComment(){
        return this.comment;
    }

    public String getDate() {
        return this.date;
    }
}
