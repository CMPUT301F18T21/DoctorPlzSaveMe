package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import android.widget.TextView;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button viewProblemsBtn = findViewById(R.id.viewProblemsButton);
        viewProblemsBtn.setOnClickListener(this); // calling onClick() method

        Button editProfileBtn = findViewById(R.id.editProfileButton);
        editProfileBtn.setOnClickListener(this);

        Button viewLocationBtn = findViewById(R.id.viewLocationButton);
        viewLocationBtn.setOnClickListener(this);

        Button uploadBodyLocationBtn = findViewById(R.id.uploadBodyLocationButton);
        uploadBodyLocationBtn.setOnClickListener(this);

        TextView welcome_text = findViewById(R.id.welcomeText);

        String w_text = "Welcome, " + Backend.getInstance().getPatientProfile().getID();
        welcome_text.setText(w_text);
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.viewProblemsButton:
                viewProblems(findViewById(R.id.content));
                break;
            case R.id.uploadBodyLocationButton:
                uploadBodyLocation(findViewById(R.id.content));
                break;
            case R.id.editProfileButton:
                editProfile(findViewById(R.id.content));
                break;
            case R.id.viewLocationButton:
                viewLocation(findViewById(R.id.content));
                break;
            default:
                break;
        }
    }

    /** Called when the user taps the View Problems button */
    public void viewProblems(View view) {
        Intent intent = new Intent(this, MainProblemActivity.class);
        startActivity(intent);
    }

    public void uploadBodyLocation(View view) {
        Intent intent = new Intent(this, UploadBodyLocationActivity.class);
        startActivity(intent);
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void viewLocation(View view) {
        Intent intent = new Intent(this, ViewRecordLocationsActivity.class);
        startActivity(intent);
    }
}

