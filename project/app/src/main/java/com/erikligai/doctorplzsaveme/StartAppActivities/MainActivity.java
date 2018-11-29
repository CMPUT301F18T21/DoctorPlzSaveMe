package com.erikligai.doctorplzsaveme.StartAppActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Activities.CareProviderActivity;
import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class MainActivity extends AppCompatActivity {

    private Button patientButton, careProviderButton;

    // TODO: ADD BACK (STACK) NAVIGATION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // IMPORTANT! DON'T DELETE!
        Backend.getInstance().setContext(getApplicationContext());

        patientButton = (Button) findViewById(R.id.patient_button);
        careProviderButton = (Button) findViewById(R.id.care_provider_button);

        // button listener for patientButton
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if we don't detect a profile, go to NoProfileActivity
                if (Backend.getInstance().fetchPatientProfile() == null)
                {
                    finish();
                    startActivity(new Intent(MainActivity.this, NoProfileActivity.class));
                } else // otherwise go to PatientActivity
                {
                    finish();
                    startActivity(new Intent(MainActivity.this, PatientActivity.class));
                }

            }
        });

        // button listener for careProviderButton
        careProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CPLoginActivity.class));
            }
        });

    }

}
