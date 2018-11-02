package com.erikligai.doctorplzsaveme;

import java.util.Collection;

public class CommentList {
    private Collection<Comment> comments;
    public void add(Comment comment){
        comments.add(comment);
    }
    public boolean hasComment(Comment comment){
        return comments.contains(comment);
    }
}
