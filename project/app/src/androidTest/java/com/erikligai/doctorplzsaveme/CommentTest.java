package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Models.Comment;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommentTest extends TestCase {
    public void testEditComment() {
        Comment testComment = new Comment("test comment");
        testComment.editComment("changed text");
        assertEquals("changed text", testComment.getComment());
    }

    public void testCorrectDate() {
        Comment testComment = new Comment("test comment");
        String testDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        assertEquals(testComment.getDate(), testDate);
    }
}
