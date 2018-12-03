package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class EditRecordTwoActivity extends AppCompatActivity {
    private Button nextBtn;
    private Button backBtn;
    private Button changeBtn;
    private Record record;
    private Patient patient;
    private ImageView imageView;
    private ImageView imageView2;
    private int problem_index;
    private int record_index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_two);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("P_Pos", 0);
        record_index = intent.getIntExtra("R_Pos", 0);

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
                    float x = event.getX()/imageView2.getWidth();
                    float y = event.getY()/imageView2.getHeight();
                    imageView.setX(x*imageView2.getWidth());
                    imageView.setY(y*imageView2.getHeight());
                    imageView.setVisibility(View.VISIBLE);
                    record.setXpos(x);
                    record.setYpos(y);
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
                openEditRecordThreeActivity();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openEditRecordActivity();
            }
        });

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fab", "add bodylocation photo");
                //calls AddRecordActivity
                Intent intent = new Intent(view.getContext(), SelectByLocationActivity.class);
                intent.putExtra("P_Pos", problem_index);
                intent.putExtra("R_Pos", record_index);
                startActivity(intent);
            }
        });
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        updateImage();
        imageView2.post(new Runnable() {
            @Override
            public void run() {
                if(record.getXpos()!=0.0 || record.getYpos()!=0.0) {
                    imageView.setX(record.getXpos()*imageView2.getWidth());
                    imageView.setY(record.getYpos()*imageView2.getHeight());
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void updateImage(){
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

    private void openEditRecordActivity() {
        Intent intent = new Intent(this, EditRecordActivity.class);
        intent.putExtra("P_Pos", problem_index);
        intent.putExtra("R_Pos", record_index);
        finish();
        startActivity(intent);
    }

    private void openEditRecordThreeActivity() {
        Intent intent = new Intent(this, EditRecordThreeActivity.class);
        intent.putExtra("P_Pos", problem_index);
        intent.putExtra("R_Pos", record_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openEditRecordActivity();
    }

}
