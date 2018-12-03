package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Adapters.CPRecordBodyAdapter;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class CPSearchBodyActivity extends AppCompatActivity {

    private static final String TAG = "PatientRecordActivity";

    CPRecordBodyAdapter adapter;
    //    private ArrayList<Record> recordList = new ArrayList<>();
    private ArrayList<Record> recordList;
    Backend backend = Backend.getInstance();

    private String patientID;
    private String problemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpsearch_body);

        // pull patient's records from back end using passed in problem id
        Intent intent = getIntent(); // receive intent
        problemID = intent.getExtras().getString("problemID");
        patientID = intent.getExtras().getString("patientID");
//        Log.e("patientIDH", patientID);
//        Log.e("problemIDH", problemID);

//        Date date = new Date();
//        // format date into string
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
//        String formattedDate = formatter.format(date);

//        Record testRecord = new Record("testRecord", "comment");
////
//        backend.addPatientRecord(Integer.valueOf(problemID), testRecord);
//        Log.e("marker", "REACHES");
        recordList = backend.GetCPPatientRecords(patientID, Integer.valueOf(problemID));

//        Log.e("BOOLEAN: ", recordList.get(0).toString());

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        initRecyclerView();
    }

    // This activity should receive the care provider that was passed
    // Then access the list of all patients, subtract the patients the care provider already has
    // And display that filtered list for the care provider to choose from
    // There should also be a search bar at the top to look for patient ID

    private void initRecyclerView() {
//        Log.d(TAG, "initRecyclerView: init");

        RecyclerView recyclerView = findViewById(R.id.cp_patient_records_recycler_view_body);
        TextView emptyView = findViewById(R.id.empty_view);

        if (recordList.isEmpty()) {
            // hide recyclerview
            recyclerView.setVisibility(View.GONE);
            // display textview
            emptyView.setVisibility(View.VISIBLE);
        } else {
            // display recyclerview
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new CPRecordBodyAdapter(recordList, this, patientID, problemID);
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
}