package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Adapters.RecyclerViewPhotoAdapter;
import com.erikligai.doctorplzsaveme.R;

import java.util.ArrayList;

public class PatientSelectBodylocationPhotoActivity extends AppCompatActivity {

    private RecyclerViewPhotoAdapter adapter;
    private Button doneBtn;
    private int ProblemPosition;
    private int RecordPosition;
    private String patientID;
    private int chosen = -1;
    private ArrayList<Integer> imgs;

    private ImageView front;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_select_bodylocation_photo);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        RecordPosition = intent.getIntExtra("RecordPos",-1);
        patientID = intent.getStringExtra("patientId");
        doneBtn = findViewById(R.id.doneButton2);
        front = findViewById(R.id.frontDefault);
        back = findViewById(R.id.backDefault);

        imgs.add(R.drawable.front);
        imgs.add(R.drawable.back);
        imgs.add(R.drawable.stoned);

        front.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    chosen = 0;
                }
                return true;
            }
        });

        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    chosen = 1;
                }
                return true;
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPViewBodyLocationActivity();
            }
        });
    }

    public void openCPViewBodyLocationActivity(){
        Intent intent = new Intent(this,CPViewBodyLocationActivity.class);
        intent.putExtra("ProblemPos", ProblemPosition);
        intent.putExtra("RecordPos", RecordPosition);
        intent.putExtra("patientId",patientID);
        intent.putExtra("chosen",chosen);
        finish();
        startActivity(intent);
    }
    private void initRecyclerView(String patientID) {
//        Log.d(TAG, "initRecyclerView: init");

        // display recyclerview
        adapter = new RecyclerViewPhotoAdapter(this);
    }
}
