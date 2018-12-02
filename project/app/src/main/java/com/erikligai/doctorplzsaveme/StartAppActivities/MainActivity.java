package com.erikligai.doctorplzsaveme.StartAppActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.CareProviderActivity;
import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

/**
 * This is the main activity the user starts at where they can select if they want to do
 * patient or cp related tasks
 */
public class MainActivity extends AppCompatActivity {

    private Button patientButton, careProviderButton;

    @Override
    /**
     * Set the listeners for butons and set the view
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // IMPORTANT! sets backend context for writing/reading from file
        Backend.getInstance().setContext(getApplicationContext());

        patientButton = (Button) findViewById(R.id.patient_button);
        careProviderButton = (Button) findViewById(R.id.care_provider_button);

        // button listener for patientButton
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * Set the listener for patientButton
             */
            public void onClick(View v) {

                // if we don't detect a local profile, go to NoProfileActivity
                if (Backend.getInstance().fetchPatientProfile() == null)
                {
                    startActivity(new Intent(MainActivity.this, NoProfileActivity.class));
                } else // otherwise go to PatientActivity
                {
                    startActivity(new Intent(MainActivity.this, PatientActivity.class));
                }

            }
        });

        // button listener for careProviderButton
        careProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * Set the listener for careProviderButton
             */
            public void onClick(View v) {
                boolean fetched_from_local = Backend.getInstance().deserializeCPProfile();
                // if we didn't fetch a cp id from file, go to login page
                if (!fetched_from_local) {
                    startActivity(new Intent(MainActivity.this, CPLoginActivity.class));
                    return;
                }
                // otherwise check if local cp id exists on db
                int r = Backend.cpIDExists(Backend.getInstance().getCP_ID());
                // if it exists, populate the patients (fetch them from DB with the cp id)
                if (r == 0) {
                    Backend.getInstance().ClearPatients();
                    Backend.getInstance().PopulatePatients();
                    startActivity(new Intent(MainActivity.this, CareProviderActivity.class));
                } else if (r == 1)
                // if it doesn't exist but we connected to the db, launch login. should not happen, really
                {
                    Log.e("CP: ","did not find cp id on file in DB!");
                    startActivity(new Intent(MainActivity.this, CPLoginActivity.class));
                } else if (r == -1) // could not connect to db so toast message it
                {
                    Toast.makeText(getApplicationContext(), (String) "Could not connect to DB!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
