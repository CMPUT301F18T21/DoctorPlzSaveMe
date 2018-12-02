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
import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class AddRecordTwoActivity extends AppCompatActivity {
    private Button nextBtn;
    private Button backBtn;
    private Button changeBtn;
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
    private int problem_index;
    private ArrayList<String> bodylocation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_two);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",-1);
        chosen = intent.getIntExtra("chosen",-1);

        Backend backend = Backend.getInstance();
        record = backend.GetCPPatientRecord(patientID,ProblemPosition,RecordPosition);

        nextBtn = findViewById(R.id.cpRecordNext2);
        backBtn = findViewById(R.id.cpRecordBack2);
        changeBtn = findViewById(R.id.RecordChange);
        imageView2 = findViewById(R.id.imageView2);
        // imageView.getLocationOnScreen(loc);
        imageView = findViewById(R.id.imageView);
        imageView.setVisibility(View.GONE);
        bodylocation = RecordBuffer.getInstance().getRecord().getBodyLocation();
        if(bodylocation!=null){
            imageView.setX(Float.valueOf(bodylocation.get(1)));
            imageView.setY(Float.valueOf(bodylocation.get(2)));
            Log.i("load",bodylocation.get(1));
            imageView.setVisibility(View.VISIBLE);
        }


        if (chosen == 0){
            imageView.setImageResource(R.drawable.front);
        }
        if (chosen == 1){
            imageView.setImageResource(R.drawable.back);
        }

        imageView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
//                    textView.setText("Touch coordinates : " +
//                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                    imageView.setY(event.getY() + imY - (imageView.getHeight()/2));
                    imageView.setX(event.getX() + imX - (imageView.getWidth()/2));
                    imageView.setVisibility(View.VISIBLE);
                    BLX = event.getX();
                    BLY = event.getY();
                    ArrayList<String> body_location = new ArrayList<String>();
                    body_location.add("default1");
                    body_location.add(Float.toString(BLX));
                    body_location.add(Float.toString(BLY));
                    Log.i("save",Float.toString(BLX));
                    RecordBuffer.getInstance().getRecord().setBodyLocation(body_location);
                }
                return true;
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddRecordThreeActivity();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openAddRecordActivity();
            }
        });

        changeBtn.setOnClickListener(new View.OnClickListener() {
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

    private void openAddRecordActivity() {
        Intent intent = new Intent(this, AddRecordActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }

    private void openAddRecordThreeActivity() {
        Intent intent = new Intent(this, AddRecordThreeActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        imX = imageView2.getX();
        imY = imageView2.getY();
    }

    @Override
    public void onBackPressed() {
        openAddRecordActivity();
    }

}
