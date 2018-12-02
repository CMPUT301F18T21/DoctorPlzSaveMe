package com.erikligai.doctorplzsaveme.LogInFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.PatientActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class PatientLoginFragment extends Fragment {

    private Button scanButton, newProfileButton, logInButton;
    private TextView usernameText;
    Backend backend = Backend.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_login, container, false);
        scanButton = (Button) view.findViewById(R.id.scan_button);
        newProfileButton = (Button) view.findViewById(R.id.new_profile_button);
        logInButton = (Button) view.findViewById(R.id.logInButton);
        usernameText = (TextView) view.findViewById(R.id.usernameText);

        // button listener for scanButton
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * set button listener for scanButton
             */
            public void onClick(View v) {
                // scan in qr code
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
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
            /**
             * set logInButton listener
             */
            public void onClick(View v) {
                // see if we can get it from DB
                Backend.getInstance().setPatientFromES(usernameText.getText().toString());
                // check if the patient profile is null (shouldn't be if we fetched profile from DB)
                if (Backend.getInstance().getPatientProfile() == null)
                {
                    try { wait(1000); } catch (Exception e) {} // wait a bit to try again
                }
                if (Backend.getInstance().getPatientProfile() == null)
                {
                    try { wait(1000); } catch (Exception e) {} // wait a bit to try again
                }
                if (Backend.getInstance().getPatientProfile() != null)
                {
                    // we fetched profile so go to patient activity
                    getActivity().onBackPressed();
                    startActivity(new Intent(getActivity(), PatientActivity.class));
                } else // complain we couldn't get that profile
                {
                    Toast.makeText(getActivity().getApplicationContext(), (String) "Could not retrieve profile!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button listener for newProfileButton
        newProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * set newProfileButton listener
             */
            public void onClick(View v) {
                // go to new profile activity (no checking required)
                Fragment fragment = new NewProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.nothing, R.anim.nothing, R.anim.slide_out_bottom);
                fragmentTransaction.add(R.id.bottom_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    /**
     * QR code handler (activity result)
     */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // handle QR code, similar functionality to above...
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "Fail", Toast.LENGTH_LONG).show();
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
                        getActivity().onBackPressed();
                        startActivity(new Intent(getActivity(), PatientActivity.class));
                    } else {
                        Toast.makeText(getActivity(), (String) "Could not retrieve profile!", Toast.LENGTH_SHORT).show();
                    }
                } else if (r == 1) {
                    Toast.makeText(getActivity(), "Username does not exist!", Toast.LENGTH_LONG).show();
                } else if (r == -1)
                {
                    Toast.makeText(getActivity(), "Could not connect to DB!", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}