package com.erikligai.doctorplzsaveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CareProviderActivity extends AppCompatActivity {
    private static final String TAG = "CareProviderActivity";

    private ArrayList<Patient> patientList = new ArrayList<>();

//    private ArrayList<String> mPatientIds = new ArrayList<>();
//    private ArrayList<String> mPatientEmails = new ArrayList<>();
//    private ArrayList<String> mPatientPhones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider);
        Log.d(TAG, "onCreate: started");


        // https://developer.android.com/guide/topics/ui/floating-action-button

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CareProviderActivity.this, AddPatientActivity.class));
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        addPatients();
    }

    private void addPatients() {
        Log.d(TAG, "addPatients: preparing patients");

        patientList.add(new Patient("Erik", "1", "ligai@ualberta.ca", "12312341"));
        patientList.add(new Patient("Joe", "2", "qwer@ualberta.ca", "12348573"));
        patientList.add(new Patient("Daniil", "3", "bam@ualberta.ca", "746746"));
        patientList.add(new Patient("Weng", "4", "asdfadf@ualberta.ca", "0918234"));
        patientList.add(new Patient("Iyun", "5", "asdfasdf@ualberta.ca", "1234869023"));
        patientList.add(new Patient("Bruce", "6", "owerti@ualberta.ca", "6458349"));

//        mPatientIds.add("1");
//        mPatientEmails.add("ligai@ualberta.ca");
//        mPatientPhones.add("031491234");
//
//        mPatientIds.add("2");
//        mPatientEmails.add("qwer@ualberta.ca");
//        mPatientPhones.add("3284578345");
//
//        mPatientIds.add("3");
//        mPatientEmails.add("tyui@ualberta.ca");
//        mPatientPhones.add("234123");
//
//        mPatientIds.add("4");
//        mPatientEmails.add("opas@ualberta.ca");
//        mPatientPhones.add("745625653");
//
//        mPatientIds.add("5");
//        mPatientEmails.add("dfgh@ualberta.ca");
//        mPatientPhones.add("1432956873");
//
//        mPatientIds.add("6");
//        mPatientEmails.add("jklz@ualberta.ca");
//        mPatientPhones.add("091782341");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(patientList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
