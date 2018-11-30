package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class CPViewBodyLocationActivity  extends AppCompatActivity {
    private Button nextBtn;
    private Button backBtn;
    private Record record;
    private TextView textView;
    private ImageView imageView;
    private int ProblemPosition;
    private int RecordPosition;
    private String patientID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_bodylocation);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        RecordPosition = intent.getIntExtra("RecordPos",-1);
        patientID = intent.getStringExtra("patientId");

        Backend backend = Backend.getInstance();
        record = backend.GetCPPatientRecord(patientID,ProblemPosition,RecordPosition);

        nextBtn = findViewById(R.id.cpRecordNext2);
        backBtn = findViewById(R.id.cpRecordBack2);
        imageView = findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView2);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    textView.setText("Touch coordinates : " +
                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                }
                return true;
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPViewGeolocationActivity();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPViewRecordActivity();
            }
        });

    }

    public void openCPViewRecordActivity(){
        Intent intent = new Intent(this,CPViewRecordActivity.class);
        startActivity(intent);
    }

    public void openCPViewGeolocationActivity(){
        Intent intent = new Intent(this,CPViewGeolocationActivity.class);
        intent.putExtra("ProblemPos", ProblemPosition);
        intent.putExtra("RecordPos", RecordPosition);
        intent.putExtra("patientId",patientID);
        startActivity(intent);
    }
}
