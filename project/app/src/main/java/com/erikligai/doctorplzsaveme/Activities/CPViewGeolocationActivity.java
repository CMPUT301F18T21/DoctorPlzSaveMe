package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Models.Geolocation;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class CPViewGeolocationActivity extends AppCompatActivity  {
    private Button doneBtn;
    private Button backBtn;
    private Record record;
    private int ProblemPosition;
    private int RecordPosition;
    private String patientID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_geolocation);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        RecordPosition = intent.getIntExtra("RecordPos",-1);
        patientID = intent.getStringExtra("patientId");

        Backend backend = Backend.getInstance();
        record = backend.GetCPPatientRecord(patientID,ProblemPosition,RecordPosition);

        doneBtn = findViewById(R.id.cpRecordDone);
        backBtn = findViewById(R.id.cpRecordBack3);

        doneBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPRecordActivity();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPViewBodylocationActivity();
            }
        });

    }

    public void openCPViewBodylocationActivity(){
        Intent intent = new Intent(this,CPViewBodyLocationActivity.class);
        intent.putExtra("ProblemPos", ProblemPosition);
        intent.putExtra("RecordPos", RecordPosition);
        intent.putExtra("patientId",patientID);
        startActivity(intent);
    }

    public void openCPRecordActivity(){
        Intent intent = new Intent(this,CPRecordActivity.class);
        intent.putExtra("problemID", Integer.toString(ProblemPosition));
        intent.putExtra("patientID",patientID);
        startActivity(intent);
    }
}
