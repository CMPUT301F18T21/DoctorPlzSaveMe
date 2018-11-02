package com.erikligai.doctorplzsaveme;
import junit.framework.TestCase;

public class CommentListTest extends TestCase {

    public CommentListTest(){
        super(com.erikligai.doctorplzsaveme.NewPatientActivity.class);
    }

    public void testAddComment(){
        CommentList comments = new CommentList();
        Comment comment = Comment("test")
        comments.add(comment);
        assertTrue(comments.hasComment(comment));
    }

}
