package com.erikligai.doctorplzsaveme.StartAppActivities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class NewProfileActivity extends AppCompatActivity {

    private Button createProfile;
    private TextView emailInputText;
    private TextView phoneInputText;
    private TextView userIDText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        createProfile = (Button) findViewById(R.id.createProfileButton);
        emailInputText = (TextView) findViewById(R.id.emailInputText);
        phoneInputText = (TextView) findViewById(R.id.phoneInputText);
        userIDText = (TextView) findViewById(R.id.user_id_textview);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Backend.isConnected();
                    Patient new_patient = new Patient(
                            userIDText.getText().toString(),
                            emailInputText.getText().toString(),
                            phoneInputText.getText().toString());
                    Backend.getInstance().setPatientProfile(new_patient);
                    Intent intent = new Intent(NewProfileActivity.this, PatientActivity.class);
                    startActivity(intent);
                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

