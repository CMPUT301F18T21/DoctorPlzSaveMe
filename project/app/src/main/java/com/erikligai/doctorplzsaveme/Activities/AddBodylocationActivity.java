package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.R;

public class AddBodylocationActivity extends AppCompatActivity {

    private Button backBtn2,nextBtn2,addPhotoBtn,addBodylocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bodylocation);

        // Get buttons
        backBtn2 = findViewById(R.id.backButton2);
        nextBtn2 = findViewById(R.id.nextButton2);
        addPhotoBtn  =findViewById(R.id.addPhotoButton);
        addBodylocationBtn = findViewById(R.id.addBodylocationButton);

        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddRecordActivity();
            }
        });

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddGeolocationActivity();
            }
        });
    }
   
    private void openAddRecordActivity(){
        Intent intent = new Intent(this,AddRecordActivity.class);
        //startActivity(intent);
    }
    
    private void openAddGeolocationActivity(){
        Intent intent = new Intent(this,AddGeolocationActivity2.class);
        //startActivity(intent);
    }
}