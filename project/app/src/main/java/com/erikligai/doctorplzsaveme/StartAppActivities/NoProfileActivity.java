package com.erikligai.doctorplzsaveme.StartAppActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class NoProfileActivity extends AppCompatActivity {

    private Button scanButton, newProfileButton, logInButton;
    private TextView usernameText;
    Backend backend = Backend.getInstance();

    // TODO: ADD BACK (STACK) NAVIGATION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_profile);

        scanButton = (Button) findViewById(R.id.scan_button);
        newProfileButton = (Button) findViewById(R.id.new_profile_button);
        logInButton = (Button) findViewById(R.id.logInButton);
        usernameText = (TextView) findViewById(R.id.usernameText);

        // button listener for scanButton
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Scan in profile
                IntentIntegrator integrator = new IntentIntegrator(NoProfileActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        // button listener for logInButton
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: FIX
                Backend.getInstance().setPatientFromES(usernameText.getText().toString());
                if (Backend.getInstance().getPatientProfile() == null)
                {
                    try { wait(1000); } catch (Exception e) {} // wait a bit
                }
                if (Backend.getInstance().getPatientProfile() != null)
                {
                    finish();
                    startActivity(new Intent(NoProfileActivity.this, PatientActivity.class));
                } else
                {
                    Toast.makeText(getApplicationContext(), (String) "Could not retrieve profile!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button listener for newProfileButton
        newProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(NoProfileActivity.this, NewProfileActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();
            } else {
                int r = backend.userIDExists(result.getContents());
                if (r == 0) {
                    Backend.getInstance().setPatientFromES(result.getContents()); // sets scanned user id to login
                    if (Backend.getInstance().getPatientProfile() == null)
                    {
                        try { wait(1000); } catch (Exception e) {} // wait a bit (timeout)
                    }
                    if (Backend.getInstance().getPatientProfile() != null)
                    {
                        finish();
                        startActivity(new Intent(NoProfileActivity.this, PatientActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), (String) "Could not retrieve profile!", Toast.LENGTH_SHORT).show();
                    }
                } else if (r == 1) {
                    Toast.makeText(NoProfileActivity.this, "username does not exist", Toast.LENGTH_LONG).show();
                } else if (r == -1)
                {
                    Toast.makeText(NoProfileActivity.this, "Could not connect", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
