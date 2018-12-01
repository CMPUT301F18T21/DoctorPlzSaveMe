package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;

import java.util.Date;

public class AddRecordTwoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button backBtn2,nextBtn2,addPhotoBtn,addBodylocationBtn;
    private int problem_index;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_two);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",-1);
        backBtn2 = findViewById(R.id.backButton2);
        nextBtn2 = findViewById(R.id.nextButton2);
        backBtn2.setOnClickListener(this);
        nextBtn2.setOnClickListener(this);
        //date = intent.getLongExtra("date",-1);
        // Get buttons
//        backBtn2 = findViewById(R.id.backButton2);
//        nextBtn2 = findViewById(R.id.nextButton2);
//        addPhotoBtn  =findViewById(R.id.addPhotoButton);
//        addBodylocationBtn = findViewById(R.id.addBodylocationButton);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton2:
                openAddRecordActivity();
                break;

            case R.id.nextButton2:
                RecordBuffer.getInstance().addRecordBuffer(problem_index);
                finish();
                break;
        }
    }

    private void openAddRecordActivity() {
        Intent intent = new Intent(this, AddRecordActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }
}