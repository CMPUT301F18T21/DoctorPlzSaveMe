package com.erikligai.doctorplzsaveme;

import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemDescException;
import com.erikligai.doctorplzsaveme.Exceptions.TooLongProblemTitleException;
import com.erikligai.doctorplzsaveme.Models.Comment;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProblemTest extends TestCase {
    public void testTitle() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = null;
        try {
            problem = new Problem(title, description, date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        assertTrue("Problem title is incorrect", title.equals(problem.getTitle()));
    }

    public void testDescription() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = null;
        try {
            problem = new Problem(title, description, date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        assertTrue("Problem description is incorrect", description.equals(problem.getDescription()));
    }

    public void testDate() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = null;
        try {
            problem = new Problem(title, description, date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        assertTrue("Problem date is incorrect", date.equals(problem.getDate()));
    }

    public void testSetTitle() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = null;
        try {
            problem = new Problem(title, description, date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        try {
            problem.setTitle("newTitle");
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        }
        assertEquals("newTitle",problem.getTitle());
    }

    public void testSetDescription() {
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = null;
        try {
            problem = new Problem(title, description, date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        try {
            problem.setDesc("newDescription");
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        assertEquals("newDescription",problem.getDescription());
    }

    public void testAddComment() {
        Comment comment = new Comment("comment");
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = null;
        try {
            problem = new Problem(title, description, date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        problem.addComment(comment);
        ArrayList<Comment> commentList = problem.getComments();
        assertTrue(commentList.contains(comment));
    }

    public void testAddRecord() {
        Record record = new Record("title", "comment");
        String title = "problem title";
        String description = "problem description";
        Date date = Calendar.getInstance().getTime();
        Problem problem = null;
        try {
            problem = new Problem(title, description, date);
        } catch (TooLongProblemTitleException e) {
            e.printStackTrace();
        } catch (TooLongProblemDescException e) {
            e.printStackTrace();
        }
        problem.addRecord(record);
        assertTrue(problem.getRecords().contains(record));
    }

}
