package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;

public class EditRecordTwoActivity extends AppCompatActivity {
    private Button nextBtn;
    private Button backBtn;
    private Button changeBtn;
    private Record record;
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

        nextBtn = findViewById(R.id.cpRecordNext2);
        backBtn = findViewById(R.id.cpRecordBack2);
        changeBtn = findViewById(R.id.RecordChange);
        imageView2 = findViewById(R.id.imageView2);
        // imageView.getLocationOnScreen(loc);
        imageView = findViewById(R.id.imageView);
        imageView.setVisibility(View.GONE);

        if(RecordBuffer.getInstance().getRecord().getPhotoid()!=null){
            imageView.setX(RecordBuffer.getInstance().getRecord().getXpos());
            imageView.setY(RecordBuffer.getInstance().getRecord().getYpos());
            imageView.setVisibility(View.VISIBLE);
        }

        imageView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
//                    textView.setText("Touch coordinates : " +
//                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                    imageView.setX(event.getX());
                    imageView.setY(event.getY());
                    imageView.setVisibility(View.VISIBLE);
                    RecordBuffer.getInstance().getRecord().setPhotoid("front");
                    RecordBuffer.getInstance().getRecord().setXpos(event.getX());
                    RecordBuffer.getInstance().getRecord().setYpos(event.getY());
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
                Intent intent = new Intent(view.getContext(), PatientSelectBodylocationPhotoActivity.class);
                intent.putExtra("P_Pos", problem_index);
                intent.putExtra("R_Pos", record_index);
                startActivity(intent);
            }
        });

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
