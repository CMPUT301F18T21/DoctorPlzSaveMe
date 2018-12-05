package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.erikligai.doctorplzsaveme.Adapters.commentAdapter;
import com.erikligai.doctorplzsaveme.Models.Comment;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class PatientViewCommentActivity extends AppCompatActivity {

    private commentAdapter adapter;
    private RecyclerView commentList;
    private Problem problem;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private ArrayList<Problem> problems;
    private int problemPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_comment);

        Intent intent = getIntent();
        problemPos = intent.getIntExtra("Pos",-1);

        Backend backend = Backend.getInstance();
        problems = backend.getPatientProblems();
        problem = problems.get(problemPos);
        comments = problem.getComments();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Comments");

        initRecyclerView();
    }

    private void initRecyclerView() {
//        Log.d(TAG, "initRecyclerView: init");

        commentList = findViewById(R.id.patientCommentRecyclerView);
        // display recyclerview
        commentList.setVisibility(View.VISIBLE);
        adapter = new commentAdapter(comments, this);
        commentList.setAdapter(adapter);
        commentList.setLayoutManager(new LinearLayoutManager(this));
    }
}
