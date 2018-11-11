package com.erikligai.doctorplzsaveme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button patientButton = findViewById(R.id.patient_button);
    private Button careProviderButton = findViewById(R.id.care_provider_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // button listener for patientButton
        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if we need to make a new profile or not, and either launch the
                // newProfileActivity or the displayProblems activity
            }
        });

        // button listener for careProviderButton
        careProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
