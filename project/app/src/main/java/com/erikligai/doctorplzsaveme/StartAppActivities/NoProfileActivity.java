package com.erikligai.doctorplzsaveme.StartAppActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class NoProfileActivity extends AppCompatActivity {

    private Button scanButton, newProfileButton, logInButton;
    private TextView usernameText;

    // TODO: ADD BACK (STACK) NAVIGATION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_profile);

        scanButton = (Button) findViewById(R.id.scan_button);
        newProfileButton = (Button) findViewById(R.id.new_profile_button);
        logInButton = (Button) findViewById(R.id.logInButton);
        usernameText = (TextView) findViewById(R.id.usernameText);

        // button listener for scanButton
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Backend.isConnected()) {
                    // TODO: Scan in profile
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button listener for logInButton
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Backend.isConnected()) {
                    Backend.getInstance().setPatientFromES(usernameText.getText().toString());
                    if (Backend.getInstance().getPatientProfile() != null)
                    {
                        finish();
                        startActivity(new Intent(NoProfileActivity.this, PatientActivity.class));
                    } else
                    {
                        Toast.makeText(getApplicationContext(), (String) "Profile does not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button listener for newProfileButton
        newProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Backend.isConnected()) {
                    finish();
                    startActivity(new Intent(NoProfileActivity.this, NewProfileActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
