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

/**
 * Activity for creating a new patient profile, can only do when connected to DB
 * saves profile locally then syncs with DB
 */
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
                // check if a field is blank
                if (userIDText.getText().toString().equals("") ||
                        emailInputText.getText().toString().equals("") ||
                        phoneInputText.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), (String) "A field is blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                // check if that user id exists in db
                int result = Backend.userIDExists(userIDText.getText().toString());
                // handle results
                if (result == 1) // no patient with that ID
                {
                    // create a new patient profile with that id and add to db, then go to patient activity
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
                    Toast.makeText(getApplicationContext(), (String) "Could not connect to DB!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("createProfile.onClick: ", "something went wrong!");
                }
            }
        });
    }
}

