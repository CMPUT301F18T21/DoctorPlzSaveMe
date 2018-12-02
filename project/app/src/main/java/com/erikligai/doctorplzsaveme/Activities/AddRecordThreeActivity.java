package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.R;

import java.util.Date;

public class AddRecordThreeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button backBtn3,nextBtn3,addPhotoBtn,addBodylocationBtn;
    private int problem_index;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_three);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",-1);
        backBtn3 = findViewById(R.id.backButton3);
        nextBtn3 = findViewById(R.id.nextButton3);
        backBtn3.setOnClickListener(this);
        nextBtn3.setOnClickListener(this);
        //date = intent.getLongExtra("date",-1);
        // Get buttons
//        backBtn2 = findViewById(R.id.backButton2);
//        nextBtn2 = findViewById(R.id.nextButton2);
//        addPhotoBtn  =findViewById(R.id.addPhotoButton);
//        addBodylocationBtn = findViewById(R.id.addBodylocationButton);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton3:
                openAddRecordTwoActivity();
                break;

            case R.id.nextButton3:
                openAddRecordFourActivity();
                break;
        }
    }

    private void openAddRecordTwoActivity() {
        Intent intent = new Intent(this, AddRecordTwoActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }

    private void openAddRecordFourActivity() {
        Intent intent = new Intent(this, AddRecordFourActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openAddRecordTwoActivity();
    }
}