package com.erikligai.doctorplzsaveme.StartAppActivities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class NewProfileActivity extends AppCompatActivity {

    private Button createProfile;
    private TextView emailInputText;
    private TextView phoneInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        createProfile = (Button) findViewById(R.id.createProfileButton);
        emailInputText = (TextView) findViewById(R.id.emailInputText);
        phoneInputText = (TextView) findViewById(R.id.phoneInputText);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tell backend to add profile, and launch PatientActivity
                // TODO: add profile to backend
                Intent intent = new Intent(NewProfileActivity.this, PatientActivity.class);
                startActivity(intent);
            }
        });

    }
}

