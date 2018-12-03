package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erikligai.doctorplzsaveme.Adapters.RecyclerViewAdapter;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.StartAppActivities.MainActivity;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class CareProviderActivity extends AppCompatActivity {
    private static final String TAG = "CareProviderActivity";


    private ArrayList<Patient> patientList;
//    private ArrayList<Patient> patientList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider);


        // pull list of patients from backend
        Backend backend = Backend.getInstance();
        patientList = backend.GetPatients();

//        Log.d(TAG, "onCreate: started");
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Here we start new activity where we can add a patient
                // We don't need to pass care provider id since it's globally available
                startActivity(new Intent(CareProviderActivity.this, AddPatientActivity.class));
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        addPatients();
    }

    private void addPatients() {
//        Log.d(TAG, "addPatients: preparing patients");

        // Do I need this anymore? I pulled patient list from database in onCreate







//        patientList.add(new Patient("Erik", "1", "ligai@ualberta.ca", "12312341"));
//        patientList.add(new Patient("Joe", "2", "qwer@ualberta.ca", "12348573"));
        //patientList.add(new Patient("1", "ligai@ualberta.ca", "12312341"));
        /*patientList.add(new Patient("Joe", "2", "qwer@ualberta.ca", "12348573"));
        patientList.add(new Patient("Daniil", "3", "bam@ualberta.ca", "746746"));
        patientList.add(new Patient("Weng", "4", "asdfadf@ualberta.ca", "0918234"));
        patientList.add(new Patient("Iyun", "5", "asdfasdf@ualberta.ca", "1234869023"));
        patientList.add(new Patient("Bruce", "6", "owerti@ualberta.ca", "6458349"));
        */

        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cp_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_logout:
                Backend.getInstance().clearCPData();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void initRecyclerView() {
//        Log.d(TAG, "initRecyclerView: init");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(patientList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
