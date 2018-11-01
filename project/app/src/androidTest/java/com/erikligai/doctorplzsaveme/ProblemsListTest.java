package com.erikligai.doctorplzsaveme;

import android.test.ActivityInstrumentationTestCase2;

public class ProblemsListTest extends ActivityInstrumentationTestCase2 {

    public ProblemsListTest(){
        super(com.erikligai.doctorplzsaveme.MainActivity.class);
    }

    public void testAddProblem(){
        ProblemsList problems = new ProblemsList();
        Problem problem = new Problem("New Problem");
        problems.add(problem);
        assertTrue(problems.hasProblem(problem));
    }

    public void testDeleteProblem(){
        ProblemsList list = new ProblemsList();
        Problem problem = new Problem("To be deleted");
        list.add(problem);
        list.delete(problem);
        assertFalse(list.hasProblem(problem));
    }

    public void testGetProblem(){
        ProblemsList problems = new ProblemsList();
        Problem problem = new Problem("New Problem");
        problems.add(problem);
        Problem returnedProblem = problems.getProblem(0);
        assertEquals(returnedProblem.getTitle(), problem.getTitle());
    }

    public void testHasProblem(){
        ProblemsList list = new ProblemsList();
        Problem problem = new Problem("New Problem");
        list.add(problem);
        assertTrue(list.hasProblem(problem));
    }
}
