package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;

public class CPViewGeolocationActivity extends AppCompatActivity {
    private Button doneBtn;
    private Button backBtn;
    private Record record;
    private int ProblemPosition;
    private int RecordPosition;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_geolocation);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        RecordPosition = intent.getIntExtra("RecordPos",-1);

        doneBtn = findViewById(R.id.cpRecordDone);
        backBtn = findViewById(R.id.cpRecordBack3);

        doneBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //return to cp recordlist view
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
        startActivity(intent);
    }
}
