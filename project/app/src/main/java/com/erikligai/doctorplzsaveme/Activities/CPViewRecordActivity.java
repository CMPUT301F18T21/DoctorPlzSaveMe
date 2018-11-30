package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class CPViewRecordActivity extends AppCompatActivity {

    private Button nextBtn;
    private Button backBtn;
    private TextView recordTitle;
    private TextView recordComment;
    private Record record;
    private int ProblemPosition;
    private int RecordPosition;
    private String patientID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_record);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        RecordPosition = intent.getIntExtra("RecordPos",-1);
        patientID = intent.getStringExtra("patientId");

        Backend backend = Backend.getInstance();
        record = backend.GetCPPatientRecord(patientID,ProblemPosition,RecordPosition);

        nextBtn = findViewById(R.id.cpRecordNext1);
        backBtn = findViewById(R.id.cpRecordBack1);
        recordTitle = findViewById(R.id.cpRecordTitle);
        recordComment = findViewById(R.id.cpRecordComment);

        recordTitle.setText(record.getTitle());
        recordComment.setText(record.getComment());

        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPViewBodyLocationActivity();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               // return to cp view recordlist
            }
        });
    }

    public void openCPViewBodyLocationActivity(){
        Intent intent = new Intent(this,CPViewBodyLocationActivity.class);
        intent.putExtra("ProblemPos", ProblemPosition);
        intent.putExtra("RecordPos", RecordPosition);
        intent.putExtra("patientId",patientID);
        startActivity(intent);
    }
}