package com.erikligai.doctorplzsaveme.LogInFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.CareProviderActivity;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.erikligai.doctorplzsaveme.backend.ElasticsearchProblemController;

public class CPLoginFragment extends Fragment {

    private Button logInButton;
    private TextView usernameText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cplogin, container, false);

        logInButton = view.findViewById(R.id.cpLoginButton);
        usernameText = view.findViewById(R.id.cpUsernameText);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ElasticsearchProblemController.CheckIfCPExistsTask checkIfCPExistsTask = new ElasticsearchProblemController.CheckIfCPExistsTask();
                    int r = Backend.cpIDExists(usernameText.getText().toString());
                    if(r == 0) // if the id exists
                    {
                        // fetch and set the patients, and launch the care provider activity
                        Backend.getInstance().setCP_ID(usernameText.getText().toString());
                        Backend.getInstance().ClearPatients();
                        Backend.getInstance().PopulatePatients();
                        if (Backend.getInstance().getCPPatients() != null)
                        {
                            getActivity().onBackPressed();
                            Backend.getInstance().SaveCPProfile();
                            startActivity(new Intent(getActivity(), CareProviderActivity.class));
                        } else // error check if DB fetch failed
                        {
                            Toast.makeText(getActivity(), "Could not get patients!", Toast.LENGTH_SHORT).show();
                        }
                    } else if (r == 1)
                    // if we connected to DB but not found, prompt to add username and log in
                    {
                        // https://stackoverflow.com/questions/2478517/how-to-display-a-yes-no-dialog-box-on-android
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        ElasticsearchProblemController.AddCPTask addCPTask = new ElasticsearchProblemController.AddCPTask();
                                        addCPTask.execute(usernameText.getText().toString());
                                        ElasticsearchProblemController.CheckIfCPExistsTask checkIfCPExistsTask = new ElasticsearchProblemController.CheckIfCPExistsTask();
                                        try {
                                            if (checkIfCPExistsTask.execute(usernameText.getText().toString()).get()!=0)
                                            {
                                                Toast.makeText(getActivity(), "Could not login!", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        } catch (Exception e) {
                                            Toast.makeText(getActivity(), "Could not login!", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        Backend.getInstance().setCP_ID(usernameText.getText().toString());
                                        dialog.dismiss();
                                        getActivity().onBackPressed();
                                        Backend.getInstance().SaveCPProfile();
                                        startActivity(new Intent(getActivity(), CareProviderActivity.class));
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        };

                        // alert for prompt
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("This username does not exist. Would you like to create a new profile with this username and log in?")
                                .setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    } else if (r == -1) // couldn't connect to DB
                    {
                        Toast.makeText(getActivity(), "Could not connect to DB!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Could not login!", Toast.LENGTH_SHORT).show();
                    // shouldn't happen really
                }
            }
        });

        return view;
    }
}