package com.erikligai.doctorplzsaveme.LogInFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class NewProfileFragment extends Fragment {

    private Button createProfile;
    private TextView emailInputText;
    private TextView phoneInputText;
    private TextView userIDText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_profile, container, false);

        createProfile = (Button) view.findViewById(R.id.createProfileButton);
        emailInputText = (TextView) view.findViewById(R.id.emailInputText);
        phoneInputText = (TextView) view.findViewById(R.id.phoneInputText);
        userIDText = (TextView) view.findViewById(R.id.user_id_textview);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * set createProfile button listener
             */
            public void onClick(View v) {
                // check if a field is blank
                if (userIDText.getText().toString().equals("") ||
                        emailInputText.getText().toString().equals("") ||
                        phoneInputText.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), (String) "A field is blank", Toast.LENGTH_SHORT).show();
                    return;
                }
                // check if that user id exists in db
                int result = Backend.userIDExists(userIDText.getText().toString());
                // handle results
                if (result == 1) // no patient with that ID
                {
                    // create a new patient profile with that id and add to db, then go to patient activity
                    Patient new_patient = new Patient(
                            userIDText.getText().toString(),
                            emailInputText.getText().toString(),
                            phoneInputText.getText().toString());
                    Backend.getInstance().setPatientProfile(new_patient);
                    Intent intent = new Intent(getActivity(), PatientActivity.class);
                    getActivity().onBackPressed();
                    getActivity().onBackPressed(); // messy but works
                    startActivity(intent);
                } else if (result == 0) // patient with that ID already exists
                {
                    Toast.makeText(getActivity(), (String) "Username exists!", Toast.LENGTH_SHORT).show();
                } else if (result == -1)
                {
                    Toast.makeText(getActivity(), (String) "Could not connect to DB!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("createProfile.onClick: ", "something went wrong!");
                }
            }
        });
        return view;
    }
}