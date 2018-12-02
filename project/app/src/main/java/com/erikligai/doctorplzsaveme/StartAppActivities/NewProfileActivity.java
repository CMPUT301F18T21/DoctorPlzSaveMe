package com.erikligai.doctorplzsaveme.StartAppActivities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.erikligai.doctorplzsaveme.backend.Toaster;

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
                // TODO: CHECK FOR VALID PATIENT PARAMS
                int result = Backend.userIDExists(userIDText.getText().toString());
                if (result == 1) // no patient with that ID
                {
                    Patient new_patient = new Patient(
                            userIDText.getText().toString(),
                            emailInputText.getText().toString(),
                            phoneInputText.getText().toString());
                    Backend.getInstance().setPatientProfile(new_patient);
                    Intent intent = new Intent(NewProfileActivity.this, PatientActivity.class);
                    finish();
                    startActivity(intent);
                } else if (result == 0) // patient with that ID already exists
                {
                    Toast.makeText(getApplicationContext(), (String) "Username exists!", Toast.LENGTH_SHORT).show();
                } else if (result == -1)
                {
                    Toast.makeText(getApplicationContext(), (String) "No connection to DB!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("createProfile.onClick: ", "something went wrong!");
                }
            }
        });

    }
}

