package com.erikligai.doctorplzsaveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class AddBodylocationActivity extends AppCompatActivity {

    private Button backBtn2,nextBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bodylocation);

        // Get buttons
        backBtn2 = findViewById(R.id.backButton2);
        nextBtn2 = findViewById(R.id.nextButton2);

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
        startActivity(intent);
    }

    private void openAddGeolocationActivity(){
        Intent intent = new Intent(this,AddGeolocationActivity.class);
        startActivity(intent);
    }
}