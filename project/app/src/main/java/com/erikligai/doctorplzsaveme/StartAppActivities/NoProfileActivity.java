package com.erikligai.doctorplzsaveme.StartAppActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.R;

public class NoProfileActivity extends AppCompatActivity {

    private Button scanButton, newProfileButton;

    // TODO: ADD BACK (STACK) NAVIGATION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_profile);

        scanButton = (Button) findViewById(R.id.scan_button);
        newProfileButton = (Button) findViewById(R.id.new_profile_button);

        // button listener for scanButton
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Scan in profile
            }
        });

        // button listener for newProfileButton
        newProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoProfileActivity.this, NewProfileActivity.class));
            }
        });
    }
}
