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
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Adapters.RecordAdapter;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class MainRecordActivity extends AppCompatActivity {
    private RecordAdapter adapter;
    private ArrayList<Record> records;

    public int getProblem_index() {
        return problem_index;
    }

    public void setProblem_index(int problem_index) {
        this.problem_index = problem_index;
    }

    private int problem_index;
    private RecyclerView recordRecycler;
    private TextView emptyView;
    Backend backend = Backend.getInstance();

    //sample record list
//    Record r1 = new Record("Record1","recordDescription");
//    Record r2 = new Record("Record1","recordDescription");
//    Record r3 = new Record("Record1","recordDescription");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_record);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",0);
        records = Backend.getInstance().getPatientRecords(problem_index);

//        records.add(r1);
//        records.add(r2);
//        records.add(r3);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.record_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title = Backend.getInstance().getPatientProblems().get(problem_index).getTitle() + " " + getString(R.string.record);
        getSupportActionBar().setTitle(title);

        FloatingActionButton fab = findViewById(R.id.record_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fab", "add record");
                //calls AddRecordActivity
                Intent intent = new Intent(view.getContext(), AddRecordActivity.class);
                intent.putExtra("Pos", problem_index);
                startActivity(intent);
            }
        });

        recordRecycler = findViewById(R.id.record_recyclerview);
        recordRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recordRecycler.setLayoutManager(layoutManager);

        emptyView = findViewById(R.id.empty_record_view);

        adapter = new RecordAdapter(records);
        adapter.setParentActivity(this);
        recordRecycler.setAdapter(adapter);
        adapter.setOnEntryClickListener(new RecordAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(int position) {
                Intent intent = new Intent(getApplicationContext(), ViewRecordActivity.class);
                intent.putExtra("R_Pos", position);
                intent.putExtra("P_Pos", problem_index);
                startActivity(intent);
                Log.d("rview", Integer.toString(position));
            }
        });
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("abc","onresume");
        adapter.notifyDataSetChanged();
        checkEmpty();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search_toolbar, menu);
//        return true;
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

//            case R.id.action_search:
//                // User chose the "Settings" item, show the app settings UI...
//                Log.d("toolbar", "search function!");
//                //calls ProblemSearchActivity
//                Intent intent = new Intent(this, RecordSearchActivity.class);
//                startActivity(intent);
//                return true;

            case R.id.search_keyword:
                Intent intent = new Intent(this, SearchKeywordActivity.class);
                intent.putExtra("patientID", backend.getPatientProfile().getID()); // send patientID
                intent.putExtra("problemID", problem_index); // send problemID
                startActivity(intent);

            case R.id.search_geo:
                Intent geo_intent = new Intent(this, SearchGeolocationActivity.class);
                geo_intent.putExtra("patientID", backend.getPatientProfile().getID());
                geo_intent.putExtra("problemID", problem_index);
                startActivity(geo_intent);

            case R.id.search_body:
                Intent body_intent = new Intent(this, SearchBodyActivity.class);
                body_intent.putExtra("patientID", backend.getPatientProfile().getID());
                body_intent.putExtra("problemID", problem_index);
                startActivity(body_intent);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void checkEmpty(){
        if (records.isEmpty()) {
            recordRecycler.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recordRecycler.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }
}
