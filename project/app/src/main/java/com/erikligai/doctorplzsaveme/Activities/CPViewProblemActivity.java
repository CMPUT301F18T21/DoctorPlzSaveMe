package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Comment;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class CPViewProblemActivity extends AppCompatActivity {

    private ArrayAdapter<Comment> adapter;
    // Define Views
    private ListView commentList;
    private TextView problemTitle;
    private TextView problemDescription;
    private Button viewRecordsBtn;
    private FloatingActionButton fab;

    private Problem problem;
    private ArrayList<Comment> comments = new ArrayList<Comment>();
    private int problemPos;
    private String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_problem);

        commentList = findViewById(R.id.cpComments);
        problemTitle = findViewById(R.id.cpProblemTitle);
        problemDescription = findViewById(R.id.cpProblemDescription);
        viewRecordsBtn = findViewById(R.id.cpProblemRecords);
        fab = findViewById(R.id.comment_fab);

        Intent intent = getIntent();
        problemPos = intent.getIntExtra("Pos",-1);
        patientID = intent.getStringExtra("patientID");

        Backend backend = Backend.getInstance();
        problem = backend.GetCPPatientProblem(patientID,problemPos);

        comments = problem.getComments();

        problemTitle.setText(problem.getTitle());
        problemDescription.setText(problem.getDescription());

        adapter = new ArrayAdapter<Comment>(CPViewProblemActivity.this, android.R.layout.simple_list_item_1, comments);
        commentList.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fab", "add comment");
                //calls AddRecordActivity
                Intent intent = new Intent(view.getContext(), CPAddCommentActivity.class);
                intent.putExtra("ProblemPos", problemPos);
                intent.putExtra("patientId",patientID);
                startActivity(intent);
            }
        });

        viewRecordsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPViewRecordsActivity();
            }
        });
    }

    public void openCPViewRecordsActivity(){
        Intent intent = new Intent(this,CPViewRecordsActivity.class);
        startActivity(intent);
    }
}
