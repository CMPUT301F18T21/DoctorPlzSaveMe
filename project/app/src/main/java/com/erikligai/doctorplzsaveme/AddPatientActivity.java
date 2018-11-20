package com.erikligai.doctorplzsaveme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;

public class AddPatientActivity extends AppCompatActivity {
    private static final String TAG = "AddPatientActivity";

    AddPatientAdapter adapter;
    private ArrayList<Patient> patientList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        addPatients();
    }

    // This activity should receive the care provider that was passed
    // Then access the list of all patients, subtract the patients the care provider already has
    // And display that filtered list for the care provider to choose from
    // There should also be a search bar at the top to look for patient ID

    private void addPatients() {
        // this should add all patients that are not currently under the given care provider
        Log.d(TAG, "addPatients: preparing patients");

        patientList.add(new Patient("Erik", "1", "ligai@ualberta.ca", "12312341"));
        patientList.add(new Patient("Joe", "2", "qwer@ualberta.ca", "12348573"));
        patientList.add(new Patient("Daniil", "3", "asdf@ualberta.ca", "746746"));
        patientList.add(new Patient("Weng", "4", "crack@ualberta.ca", "0918234"));
        patientList.add(new Patient("Iyun", "5", "lksdfg@ualberta.ca", "1234869023"));
        patientList.add(new Patient("Bruce", "6", "owerti@ualberta.ca", "6458349"));

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init");
        RecyclerView recyclerView = findViewById(R.id.add_patient_recycler_view);
        adapter = new AddPatientAdapter(patientList, this);
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
