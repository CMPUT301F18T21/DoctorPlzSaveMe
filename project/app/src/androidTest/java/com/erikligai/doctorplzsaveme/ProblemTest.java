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




//    public void testDeleteProblem(){
//        ProblemsList list = new ProblemsList();
//        Problem problem = new Problem("test","To be deleted","07-07-07");
//        list.add(problem);
//        list.delete(problem);
//        assertFalse(list.hasProblem(problem));
//    }
//
//    public void testGetProblem(){
//        ProblemsList problems = new ProblemsList();
//        Problem problem = new Problem("test","New Problem","07-07-07");
//        problems.add(problem);
//        Problem returnedProblem = problems.getProblem(0);
//        assertEquals(returnedProblem.getTitle(), problem.getTitle());
//    }
//
//    public void testHasProblem(){
//        ProblemsList list = new ProblemsList();
//        Problem problem = new Problem("test","New Problem","07-07-07");
//        list.add(problem);
//        assertTrue(list.hasProblem(problem));
//    }
//
//    public void testEditProblem(){
//        Problem problem = new Problem("test","New Problem","07-07-07");
//        problem.edit("changed","changes","07-08-07");
//        Problem problem2 = new Problem("changed","changes","07-08-07");
//        assertEquals(problem.details(),problem2.details());
//    }
}
