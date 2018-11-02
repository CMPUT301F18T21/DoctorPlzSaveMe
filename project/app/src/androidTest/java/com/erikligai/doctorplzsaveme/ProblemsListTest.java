package com.erikligai.doctorplzsaveme;
import junit.framework.TestCase;

//import android.test.ActivityInstrumentationTestCase2;


public class ProblemsListTest extends TestCase {

    public ProblemsListTest(){
        super(String.valueOf(MainActivity.class));
    }

    public void testAddProblem(){
        ProblemsList problems = new ProblemsList();
        Problem problem = new Problem("test","New Problem","07-07-07");
        problems.add(problem);
        assertTrue(problems.hasProblem(problem));
    }

    public void testDeleteProblem(){
        ProblemsList list = new ProblemsList();
        Problem problem = new Problem("test","To be deleted","07-07-07");
        list.add(problem);
        list.delete(problem);
        assertFalse(list.hasProblem(problem));
    }

    public void testGetProblem(){
        ProblemsList problems = new ProblemsList();
        Problem problem = new Problem("test","New Problem","07-07-07");
        problems.add(problem);
        Problem returnedProblem = problems.getProblem(0);
        assertEquals(returnedProblem.getTitle(), problem.getTitle());
    }

    public void testHasProblem(){
        ProblemsList list = new ProblemsList();
        Problem problem = new Problem("test","New Problem","07-07-07");
        list.add(problem);
        assertTrue(list.hasProblem(problem));
    }

    public void testEditProblem(){
        Problem problem = new Problem("test","New Problem","07-07-07");
        problem.edit("changed","changes","07-08-07");
        Problem problem2 = new Problem("changed","changes","07-08-07");
        assertEquals(problem.details(),problem2.details());
    }
}
