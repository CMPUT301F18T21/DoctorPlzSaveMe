package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.R;

public class AddGeolocationActivity extends AppCompatActivity {
    private Button backBtn3,nextBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_geolocation);

        // Get buttons
        backBtn3 = findViewById(R.id.backButton3);
        nextBtn3 = findViewById(R.id.nextButton3);

        backBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBodylocationActivity();
            }
        });

        nextBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    
    private void openAddBodylocationActivity(){
        Intent intent = new Intent(this,AddRecordTwoActivity.class);
        startActivity(intent);
    }
}
