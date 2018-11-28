package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.erikligai.doctorplzsaveme.Activities.AddRecordActivity;
import com.erikligai.doctorplzsaveme.Adapters.RecordAdapter;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class MainRecordActivity extends AppCompatActivity {
    private RecordAdapter adapter;
    private ArrayList<Record> records;
    private int problem_index;

    //sample record list
    Record r1 = new Record("Record1","recordDescription");
    Record r2 = new Record("Record1","recordDescription");
    Record r3 = new Record("Record1","recordDescription");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_record);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",0);
        Problem problem = Backend.getInstance().getProblemList();
        records = problem.getRecords();

//        records.add(r1);
//        records.add(r2);
//        records.add(r3);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.record_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.records);

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

        RecyclerView recordRecycler = findViewById(R.id.record_recyclerview);
        recordRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recordRecycler.setLayoutManager(layoutManager);


        adapter = new RecordAdapter(records);
        recordRecycler.setAdapter(adapter);
        adapter.setOnEntryClickListener(new RecordAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(int position) {
//                Intent intent = new Intent(getApplicationContext(), EditRecordActivity.class);
//                intent.putExtra("Pos", position);
//                startActivity(intent);
                Log.d("rview", Integer.toString(position));
            }
        });
    }
}
