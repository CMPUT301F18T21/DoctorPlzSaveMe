package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.R;

public class EditRecordThreeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backBtn2,nextBtn2,addPhotoBtn,addBodylocationBtn;
    private int problem_index, record_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_three);
        Intent intent = getIntent();
        problem_index = intent.getIntExtra("P_Pos",-1);
        record_index = intent.getIntExtra("R_Pos",-1);
        backBtn2 = findViewById(R.id.backButton3);
        nextBtn2 = findViewById(R.id.nextButton3);
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
            case R.id.backButton3:
                openEditRecordTwoActivity();
                break;

            case R.id.nextButton3:
                openEditRecordFourActivity();
                break;
        }
    }

    private void openEditRecordTwoActivity() {
        Intent intent = new Intent(this, EditRecordTwoActivity.class);
        intent.putExtra("P_Pos", problem_index);
        intent.putExtra("R_Pos", record_index);
        finish();
        startActivity(intent);
    }

    private void openEditRecordFourActivity() {
        Intent intent = new Intent(this, EditRecordFourActivity.class);
        intent.putExtra("P_Pos", problem_index);
        intent.putExtra("R_Pos", record_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openEditRecordTwoActivity();
    }
}