package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

/*
*  Adding a new patient to the app*/

public class AddPatientActivity extends AppCompatActivity {
    private static final String TAG = "AddPatientActivity";

    AddPatientAdapter adapter;
    private ArrayList<Patient> patientList;
    Backend backend = Backend.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // pull list of patients from backend

        patientList = backend.GetPatients();

        EditText editText = findViewById(R.id.patient_id_search);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnScan = findViewById(R.id.btnScan);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = editText.getText().toString();

                boolean exists = false;

                // have to iterate through CP's patient ids and check it doesn't already exist inside
                // exists is false if it doesn't already exist and we are free to add
                for (Patient patient : backend.getCPPatients()) {
                    if (patient.getID().equals(userid)) {
                        exists = true;
                    }
                }
                if (exists) {
                    Toast.makeText(AddPatientActivity.this, "username already in your list", Toast.LENGTH_LONG).show();
                    return;
                }
                int r = backend.userIDExists(userid);
                if (r == 0) {
                    backend.AddPatient(userid);
                    // go back to patient list
                    Intent intent = new Intent(AddPatientActivity.this, CareProviderActivity.class);
                    Toast.makeText(AddPatientActivity.this, userid + " was added.", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else if (r == 1){
                    Toast.makeText(AddPatientActivity.this, "username does not exist", Toast.LENGTH_LONG).show();
                } else if (r == -1)
                {
                    Toast.makeText(AddPatientActivity.this, "No connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(AddPatientActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
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
                    backend.AddPatient(result.getContents());

                    // go back to patient list
                    Intent intent = new Intent(AddPatientActivity.this, CareProviderActivity.class);
                    Toast.makeText(AddPatientActivity.this, result.getContents() + " was added.", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                } else if (r == 1){
                    Toast.makeText(AddPatientActivity.this, "username does not exist or already in your list", Toast.LENGTH_LONG).show();
                } else if (r == -1)
                {
                    Toast.makeText(AddPatientActivity.this, "No connection", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
