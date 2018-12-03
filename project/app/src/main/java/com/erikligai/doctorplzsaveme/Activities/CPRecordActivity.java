package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Adapters.PatientRecordAdapter;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class CPRecordActivity extends AppCompatActivity {
    private static final String TAG = "PatientRecordActivity";

    PatientRecordAdapter adapter;
    //    private ArrayList<Record> recordList = new ArrayList<>();
    private ArrayList<Record> recordList;
    Backend backend = Backend.getInstance();

    private String patientID;
    private String problemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprecord);

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.record);

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
            adapter = new PatientRecordAdapter(recordList, this, patientID, problemID);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            // hide textview
            emptyView.setVisibility(View.GONE);
        }
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.search_keyword:
                Intent intent = new Intent(this, CPSearchKeywordActivity.class);
//                intent.putExtra("patientID", backend.getPatientProfile().getID()); // send patientID
                intent.putExtra("problemID", problemID); // send problemID
                intent.putExtra("patientID", patientID); // send patientID
                startActivity(intent);
                return true;

            case R.id.search_geo:
                Intent geo_intent = new Intent(this, CPSearchGeolocationActivity.class);
                geo_intent.putExtra("patientID", patientID);
                geo_intent.putExtra("problemID", problemID);
                startActivity(geo_intent);
                return true;

            case R.id.search_body:
                Intent body_intent = new Intent(this, CPSearchBodyActivity.class);
                body_intent.putExtra("patientID", patientID);
                body_intent.putExtra("problemID", problemID);
                startActivity(body_intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
