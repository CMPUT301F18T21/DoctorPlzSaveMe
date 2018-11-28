package com.erikligai.doctorplzsaveme.StartAppActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Activities.CareProviderActivity;
import com.erikligai.doctorplzsaveme.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class MainActivity extends AppCompatActivity {

    private Button patientButton;
    private Button careProviderButton;


    // TODO: ADD BACK (STACK) NAVIGATION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientButton = (Button) findViewById(R.id.patient_button);
        careProviderButton = (Button) findViewById(R.id.care_provider_button);

        // button listener for patientButton
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if we don't detect a local profile, go to NoProfileActivity
                if (Backend.getInstance().getPatientProfile() == null)
                {
                    startActivity(new Intent(MainActivity.this, NoProfileActivity.class));
                } else // otherwise go to PatientActivity
                {
                    // TODO: setPatientProfile() in Backend
                    startActivity(new Intent(MainActivity.this, PatientActivity.class));
                }

            }
        });

        // button listener for careProviderButton
        careProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here it should open a new activity where the care provider's patients are listed in a recycler view

                // open new activity
                startActivity(new Intent(MainActivity.this, CareProviderActivity.class));
            }
        });

    }

}
