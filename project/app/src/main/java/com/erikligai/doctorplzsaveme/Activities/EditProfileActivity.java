package com.erikligai.doctorplzsaveme.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.R;

public class EditProfileActivity extends AppCompatActivity {

    private TextView UserIdText;
    private TextView EmailText;
    private TextView PhoneText;

    private Button CancelButton;
    private Button SaveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // TODO: GET THESE FROM DB
        String User_Id = "placeholderID";
        String Email = "placeholderEmail";
        String Phone = "(999)999-9999";

        UserIdText = findViewById(R.id.user_id_textview);
        UserIdText.setText(getString(R.string.user_id) + User_Id); // TODO: fix this
        EmailText = findViewById(R.id.emailText);
        EmailText.setText(Email);
        PhoneText = findViewById(R.id.phoneText);
        PhoneText.setText(Phone);

        CancelButton = findViewById(R.id.cancelButton);
        SaveChangesButton = findViewById(R.id.saveChangesButton);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: RETURN TO MAIN (PATIENT) MENU
            }
        });

        SaveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: UPDATE DB
            }
        });

    }
}
