package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.StartAppActivities.MainActivity;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class EditProfileActivity extends AppCompatActivity {

    private TextView UserIdText, EmailText, PhoneText;
    private Button CancelButton, SaveChangesButton, logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        String User_Id = Backend.getInstance().getPatientProfile().getID();
        String Email = Backend.getInstance().getPatientProfile().getEmail();
        String Phone = Backend.getInstance().getPatientProfile().getPhone();

        UserIdText = findViewById(R.id.user_id_textview);
        UserIdText.setText(getString(R.string.user_id) + User_Id); // TODO: fix this so IDE is happy
        EmailText = findViewById(R.id.emailText);
        EmailText.setText(Email);
        PhoneText = findViewById(R.id.phoneText);
        PhoneText.setText(Phone);

        CancelButton = findViewById(R.id.cancelButton);
        SaveChangesButton = findViewById(R.id.saveChangesButton);
        logOutButton = findViewById(R.id.logOutButton);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backend.getInstance().clearLocalData();
                // https://stackoverflow.com/questions/6330260/finish-all-previous-activities
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        SaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: CHECK SET EMAIL/PHONE VALUES SO THEY ARE VALID
                if (Backend.isConnected()) {
                    Backend.getInstance().getPatientProfile().setEmail(EmailText.getText().toString());
                    Backend.getInstance().getPatientProfile().setPhone(PhoneText.getText().toString());
                    Backend.getInstance().UpdatePatient();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), (String) "No connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
