package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private FloatingActionButton fab;

    private Record record;
    private TextView textView;
    private ImageView imageView;
    private int ProblemPosition;
    private int RecordPosition;
    private int chosen;
    private String patientID;
    private ImageView imageView2;
    private float imX;
    private float imY;
    private float BLX;
    private float BLY;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_bodylocation);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        RecordPosition = intent.getIntExtra("RecordPos",-1);
        patientID = intent.getStringExtra("patientId");
        chosen = intent.getIntExtra("chosen",-1);

        Backend backend = Backend.getInstance();
        record = backend.GetCPPatientRecord(patientID,ProblemPosition,RecordPosition);

        nextBtn = findViewById(R.id.cpRecordNext2);
        backBtn = findViewById(R.id.cpRecordBack2);
        imageView = findViewById(R.id.imageView2);
        // imageView.getLocationOnScreen(loc);
        imageView2 = findViewById(R.id.imageView);
        imageView2.setVisibility(View.GONE);
        textView = findViewById(R.id.textView2);

        fab = findViewById(R.id.comment_fab2);

        if (chosen == 0){
            imageView.setImageResource(R.drawable.front);
        }
        if (chosen == 1){
            imageView.setImageResource(R.drawable.back);
        }

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    textView.setText("Touch coordinates : " +
                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                    imageView2.setY(event.getY() + imY - (imageView2.getHeight()/2));
                    imageView2.setX(event.getX() + imX - (imageView2.getWidth()/2));
                    imageView2.setVisibility(View.VISIBLE);
                    BLX = event.getX();
                    BLY = event.getY();
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fab", "add bodylocation photo");
                //calls AddRecordActivity
                Intent intent = new Intent(view.getContext(), PatientSelectBodylocationPhotoActivity.class);
                intent.putExtra("ProblemPos", ProblemPosition);
                intent.putExtra("RecordPos", RecordPosition);
                intent.putExtra("patientId",patientID);
                startActivity(intent);
            }
        });

    }

    public void openCPViewRecordActivity(){
        Intent intent = new Intent(this,CPViewRecordActivity.class);
        intent.putExtra("problemIndex", Integer.toString(ProblemPosition));
        intent.putExtra("recordIndex", Integer.toString(RecordPosition));
        intent.putExtra("patientID",patientID);
        startActivity(intent);
    }

    public void openCPViewGeolocationActivity(){
        Intent intent = new Intent(this,CPViewGeolocationActivity.class);
        intent.putExtra("ProblemPos", ProblemPosition);
        intent.putExtra("RecordPos", RecordPosition);
        intent.putExtra("patientId",patientID);
        startActivity(intent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        imX = imageView.getX();
        imY = imageView.getY();
    }
}
