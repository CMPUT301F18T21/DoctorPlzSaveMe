package com.erikligai.doctorplzsaveme;

import java.util.ArrayList;

public class ProblemsList {
    ArrayList<Problem> problems = new ArrayList<Problem>();
    public Problem getProblem(int i) {
        return problems.get(i);
    }

    public void add(Problem problem) {
    }

    public boolean hasProblem(Problem problem) {
        return true;
    }

    public void delete(Problem problem) {
    }
}
