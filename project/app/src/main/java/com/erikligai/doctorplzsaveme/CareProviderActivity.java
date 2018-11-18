package com.erikligai.doctorplzsaveme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class CareProviderActivity extends AppCompatActivity {

    private static final String TAG = "CareProviderActivity";

    private ArrayList<String> mPatientIds = new ArrayList<>();
    private ArrayList<String> mPatientEmails = new ArrayList<>();
    private ArrayList<String> mPatientPhones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_provider);
        Log.d(TAG, "onCreate: started");

        addPatients();
    }

    private void addPatients() {
        Log.d(TAG, "addPatients: preparing patients");

        mPatientIds.add("1");
        mPatientEmails.add("ligai@ualberta.ca");
        mPatientPhones.add("031491234");

        mPatientIds.add("2");
        mPatientEmails.add("qwer@ualberta.ca");
        mPatientPhones.add("3284578345");

        mPatientIds.add("3");
        mPatientEmails.add("tyui@ualberta.ca");
        mPatientPhones.add("234123");

        mPatientIds.add("4");
        mPatientEmails.add("opas@ualberta.ca");
        mPatientPhones.add("745625653");

        mPatientIds.add("5");
        mPatientEmails.add("dfgh@ualberta.ca");
        mPatientPhones.add("1432956873");

        mPatientIds.add("6");
        mPatientEmails.add("jklz@ualberta.ca");
        mPatientPhones.add("091782341");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mPatientIds, mPatientEmails, mPatientPhones, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
