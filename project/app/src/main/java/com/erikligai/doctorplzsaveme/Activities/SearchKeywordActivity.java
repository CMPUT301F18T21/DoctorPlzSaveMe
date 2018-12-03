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

import com.erikligai.doctorplzsaveme.Adapters.RecordAdapter;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class SearchKeywordActivity extends AppCompatActivity {

    private static final String TAG = "SearchKeywordActivity";

    RecordAdapter adapter;
    //    private ArrayList<Record> recordList = new ArrayList<>();
    private ArrayList<Record> recordList;
    Backend backend = Backend.getInstance();

    private String patientID = backend.getPatientProfile().getID();
    private int problemID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_keyword);

        // pull patient's records from back end using passed in problem id
        Intent intent = getIntent(); // receive intent
        problemID = intent.getIntExtra("problemID",0);
//        patientID = intent.getExtras().getString("patientID");
        Log.e("patientIDH", patientID);
        Log.e("problemIDH", problemID+"");



//        Date date = new Date();
//        // format date into string
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
//        String formattedDate = formatter.format(date);

//        Record testRecord = new Record("testRecord", "comment");
//        recordList.add(testRecord);
////
//        backend.addPatientRecord(Integer.valueOf(problemID), testRecord);
//        Log.e("marker", "REACHES");

        recordList = backend.getPatientRecords(problemID);

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



        RecyclerView recyclerView = findViewById(R.id.patient_records_recycler_view);
        TextView emptyView = findViewById(R.id.empty_view);

        if (recordList.isEmpty()) {
            // hide recyclerview
            recyclerView.setVisibility(View.GONE);
            // display textview
            emptyView.setVisibility(View.VISIBLE);
        } else {
            // display recyclerview
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new RecordAdapter(recordList, problemID);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter.setOnEntryClickListener(new RecordAdapter.OnEntryClickListener() {
                @Override
                public void onEntryClick(int position) {
                    Intent intent = new Intent(getApplicationContext(), ViewRecordActivity.class);
                    intent.putExtra("R_Pos", position);
                    intent.putExtra("P_Pos", problemID);
                    startActivity(intent);
                    Log.d("rview", Integer.toString(position));
                }
            });

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

