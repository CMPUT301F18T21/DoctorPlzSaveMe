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
                if (Backend.isConnected()) {
                    // TODO: Scan in profile
                    IntentIntegrator integrator = new IntentIntegrator(NoProfileActivity.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button listener for logInButton
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Backend.isConnected()) {
                    Backend.getInstance().setPatientFromES(usernameText.getText().toString());
                    try { wait(1000); } catch (Exception e) {} // might work
                    if (Backend.getInstance().getPatientProfile() != null)
                    {
                        finish();
                        startActivity(new Intent(NoProfileActivity.this, PatientActivity.class));
                    } else
                    {
                        Toast.makeText(getApplicationContext(), (String) "Profile does not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button listener for newProfileButton
        newProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Backend.isConnected()) {
                    finish();
                    startActivity(new Intent(NoProfileActivity.this, NewProfileActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
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
//                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                if (backend.userIDExists(result.getContents())) {

                    Log.e("username", "CHECK");
                    Log.e("username", result.getContents());
                    Log.e("username", "CHECK");

//                    backend.AddPatient(result.getContents());

                    if (Backend.isConnected()) {
                        Backend.getInstance().setPatientFromES(result.getContents()); // sets scanned user id to login
                        try { wait(1000); } catch (Exception e) {} // might work
                        if (Backend.getInstance().getPatientProfile() != null)
                        {
                            finish();
                            startActivity(new Intent(NoProfileActivity.this, PatientActivity.class));
                        } else
                        {
                            Toast.makeText(getApplicationContext(), (String) "Profile does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NoProfileActivity.this, "username does not exist", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
