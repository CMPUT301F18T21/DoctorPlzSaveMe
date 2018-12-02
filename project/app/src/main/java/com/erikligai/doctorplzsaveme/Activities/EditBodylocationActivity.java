package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.R;

public class EditBodylocationActivity extends AppCompatActivity {
    // Get Buttons
    private Button saveChangesBtn2,EditNextBtn2,editPhotoBtn,editBodylocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bodylocation);

        // Get buttons
        saveChangesBtn2 = findViewById(R.id.saveChangesButton2);
        EditNextBtn2 = findViewById(R.id.editNextButton2);
        editPhotoBtn  =findViewById(R.id.editPhotoButton);
        editBodylocationBtn = findViewById(R.id.editBodylocationButton);

        EditNextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditGeoLocationActivity();
            }
        });
        saveChangesBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void openEditGeoLocationActivity(){
        Intent intent = new Intent(this,AddRecordThreeActivity.class);
        startActivity(intent);
    }
}
