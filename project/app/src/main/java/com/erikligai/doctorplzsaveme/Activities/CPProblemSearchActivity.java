package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.R;

public class CPProblemSearchActivity extends AppCompatActivity {

    private Button patientButton;
    private Button careProviderButton;


    // TODO: ADD BACK (STACK) NAVIGATION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cpproblem_search);

        patientButton = findViewById(R.id.patient_button);
        careProviderButton = findViewById(R.id.care_provider_button);


        // button listener for patientButton
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: check if we need to make a new profile or not, and either launch the
                // NewProfileActivity or the DisplayProblems activity

                // for now, just do NewProfileActivity, for testing
                //startActivity(new Intent(CPProblemSearchActivity.this, NewProfileActivity.class));
            }
        });

        // button listener for careProviderButton
        careProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here it should open a new activity where the care provider's patients are listed in a recycler view

                // open new activity
                startActivity(new Intent(CPProblemSearchActivity.this, CareProviderActivity.class));

            }
        });

    }

}

