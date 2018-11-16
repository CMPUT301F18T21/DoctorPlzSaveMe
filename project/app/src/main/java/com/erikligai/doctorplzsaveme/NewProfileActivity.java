package com.erikligai.doctorplzsaveme;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewProfileActivity extends AppCompatActivity {

    private Button createProfile;
    private TextView emailInputText;
    private TextView phoneInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        createProfile = (Button) findViewById(R.id.createProfileButton);
        emailInputText = (TextView) findViewById(R.id.emailInputText);
        phoneInputText = (TextView) findViewById(R.id.phoneInputText);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add profile to DB and launch ViewProblemsActivity
            }
        });

    }

}