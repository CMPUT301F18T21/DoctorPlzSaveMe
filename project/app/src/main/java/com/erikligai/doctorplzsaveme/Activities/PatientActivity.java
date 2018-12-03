package com.erikligai.doctorplzsaveme.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.EditProfileActivity;
import com.erikligai.doctorplzsaveme.Activities.ViewRecordLocationsActivity;
import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import android.widget.TextView;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // invoke MainProblemActivity
        Button viewProblemsBtn = findViewById(R.id.viewProblemsButton);
        viewProblemsBtn.setOnClickListener(this); // calling onClick() method

        // invoke EditProfileActivity
        Button editProfileBtn = findViewById(R.id.editProfileButton);
        editProfileBtn.setOnClickListener(this);

        // invoke ViewRecordLocationsActivity
        Button viewLocationBtn = findViewById(R.id.viewLocationButton);
        viewLocationBtn.setOnClickListener(this);

        // invoke uploadBodyLocationActivity
        Button uploadBodyLocationBtn = findViewById(R.id.uploadBodyLocationButton);
        uploadBodyLocationBtn.setOnClickListener(this);

        // print welcome message on UL
        TextView welcome_text = findViewById(R.id.welcomeText);
        //String w_text = "Welcome, " + Backend.getInstance().getPatientProfile().getID();
        //welcome_text.setText(w_text);
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

