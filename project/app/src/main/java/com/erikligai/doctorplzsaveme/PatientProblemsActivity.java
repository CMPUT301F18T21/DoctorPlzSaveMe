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

public class PatientProblemsActivity extends AppCompatActivity {

    private static final String TAG = "PatientProblemActivity";

    PatientProblemAdapter adapter;
//    private ArrayList<Patient> problemList = new ArrayList<>();
    private ArrayList<Problem> problemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_problem_list);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        addProblems();
    }

    // This activity should receive the care provider that was passed
    // Then access the list of all patients, subtract the patients the care provider already has
    // And display that filtered list for the care provider to choose from
    // There should also be a search bar at the top to look for patient ID

    private void addProblems() {
        // this should add all patients that are not currently under the given care provider
        Log.d(TAG, "addProblems: preparing patients");

        problemList.add(new Problem("Flu", "Coughing for 2 days."));
        problemList.add(new Problem("Cough", "Coughing for 2 days."));
        problemList.add(new Problem("Cancer", "Coughing for 2 days."));
        problemList.add(new Problem("Cold", "Coughing for 2 days."));
        problemList.add(new Problem("Flu", "Coughing for 2 days."));
        problemList.add(new Problem("Flu", "Coughing for 2 days."));

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init");
        RecyclerView recyclerView = findViewById(R.id.patient_problems_recycler_view);
        adapter = new PatientProblemAdapter(problemList, this);
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
