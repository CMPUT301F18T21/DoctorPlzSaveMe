package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Adapters.PatientProblemAdapter;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class PatientProblemsActivity extends AppCompatActivity {

    private static final String TAG = "PatientProblemActivity";

    PatientProblemAdapter adapter;
//    private ArrayList<Problem> problemList = new ArrayList<>();
    private ArrayList<Problem> problemList;
    Backend backend = Backend.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_problem_list);

        // pull patient's problems from back end using passed in patient id
        Intent intent = getIntent(); // receive intent
        String patientID = intent.getExtras().getString("patientID");
//        Log.e("patientIDH", patientID);

        problemList = backend.GetCPPatientProblems(patientID);

//        Log.e("BOOLEAN: ", problemList.get(0).toString());

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(patientID + "'s " + getString(R.string.problems));

        initRecyclerView(patientID);
    }

    // This activity should receive the care provider that was passed
    // Then access the list of all patients, subtract the patients the care provider already has
    // And display that filtered list for the care provider to choose from
    // There should also be a search bar at the top to look for patient ID

    private void initRecyclerView(String patientID) {
//        Log.d(TAG, "initRecyclerView: init");

        RecyclerView recyclerView = findViewById(R.id.patient_problems_recycler_view);
        TextView emptyView = findViewById(R.id.empty_view);

        if (problemList.isEmpty()) {
            // hide recyclerview
            recyclerView.setVisibility(View.GONE);
            // display textview
            emptyView.setVisibility(View.VISIBLE);
        } else {
            // display recyclerview
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new PatientProblemAdapter(problemList, this, patientID);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            // hide textview
            emptyView.setVisibility(View.GONE);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
