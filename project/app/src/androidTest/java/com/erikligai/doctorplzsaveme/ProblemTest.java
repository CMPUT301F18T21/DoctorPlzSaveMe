package com.erikligai.doctorplzsaveme;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProblemTest extends TestCase {
    public void testTitle() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = new Problem(title, description, date);
        assertTrue("Problem title is incorrect", title.equals(problem.getTitle()));
    }

    public void testDescription() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = new Problem(title, description, date);
        assertTrue("Problem description is incorrect", description.equals(problem.getDescription()));
    }

    public void testDate() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = new Problem(title, description, date);
        assertTrue("Problem date is incorrect", date.equals(problem.getDate()));
    }

    public void testAddComment() {
        Comment comment = new Comment("comment");
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = new Problem(title, description, date);
        problem.addComment(comment);
        ArrayList<Comment> commentList = problem.getComments();
        assertTrue(commentList.contains(comment));
    }

    public void testAddRecord() {
        Record record = new Record("title");
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = new Problem(title, description, date);
        problem.addRecord(record);
        assertTrue(problem.getRecords().contains(record));
    }

}
