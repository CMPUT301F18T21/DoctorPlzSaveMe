package com.erikligai.doctorplzsaveme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class AddPatientActivity extends AppCompatActivity {
    private static final String TAG = "AddPatientActivity";

    AddPatientAdapter adapter;

    private ArrayList<String> mPatientIds = new ArrayList<>();
    private ArrayList<String> mPatientEmails = new ArrayList<>();
    private ArrayList<String> mPatientPhones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        addPatients();
    }

    // This activity should receive the care provider that was passed
    // Then access the list of all patients, subtract the patients the care provider already has
    // And display that filtered list for the care provider to choose from
    // There should also be a search bar at the top to look for patient ID

    private void addPatients() {
        // this should add all patients that are not currently under the given care provider
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
        mPatientEmails.add("crack@ualberta.ca");
        mPatientPhones.add("091782341");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init");
        RecyclerView recyclerView = findViewById(R.id.add_patient_recycler_view);
        adapter = new AddPatientAdapter(mPatientIds, mPatientEmails, mPatientPhones, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}
