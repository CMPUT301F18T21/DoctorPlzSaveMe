package com.erikligai.doctorplzsaveme;

import android.test.ActivityInstrumentationTestCase2;

public class CommentListTest extends ActivityInstrumentationTestCase2 {

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
