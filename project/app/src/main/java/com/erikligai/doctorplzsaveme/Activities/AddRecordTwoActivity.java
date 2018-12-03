package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class AddRecordTwoActivity extends AppCompatActivity {
    private Button nextBtn;
    private Button backBtn;
    private Button changeBtn;
    private Record record;
    private Patient patient;
    private ImageView imageView;
    private int chosen;
    private ImageView imageView2;
    private int problem_index;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_two);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",-1);

        record = RecordBuffer.getInstance().getRecord();
        patient = Backend.getInstance().getPatientProfile();

        nextBtn = findViewById(R.id.cpRecordNext2);
        backBtn = findViewById(R.id.cpRecordBack2);
        changeBtn = findViewById(R.id.RecordChange);
        imageView2 = findViewById(R.id.imageView2);
        // imageView.getLocationOnScreen(loc);
        imageView = findViewById(R.id.imageView);
        imageView.setVisibility(View.GONE);


        imageView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
//                    textView.setText("Touch coordinates : " +
//                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                    imageView.setX(event.getX());
                    imageView.setY(event.getY());
                    imageView.setVisibility(View.VISIBLE);
                    record.setXpos(event.getX());
                    record.setYpos(event.getY());
                    if(record.getPhotoid().equals("")){
                        record.setPhotoid("front");
                    }
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
                Intent intent = new Intent(view.getContext(), SelectByLocationActivity.class);
                intent.putExtra("ProblemPos", problem_index);
                startActivity(intent);
            }
        });

    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        updateImage();
    }

    private void updateImage(){
        if(record.getXpos()!=0.0 || record.getYpos()!=0.0) {
            imageView.setX(record.getXpos());
            imageView.setY(record.getYpos());
            imageView.setVisibility(View.VISIBLE);
        }
        int index;
        if (record.getPhotoid().equals("")){
            index = 0;
        } else {
            index = patient.getPhotoIds().indexOf(record.getPhotoid());
        }
        if(index == 0){
            imageView2.setImageResource(R.drawable.front);
        } else if (index == 1){
            imageView2.setImageResource(R.drawable.back);
        } else {
            Bitmap bitmap = StringToBitMap(patient.getPhotos().get(index));
            imageView2.setImageBitmap(bitmap);
        }
    }


    public Bitmap StringToBitMap(String encodedString) {
        byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        return bitmap;
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
    public void onBackPressed() {
        openAddRecordActivity();
    }

}
