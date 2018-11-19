package com.erikligai.doctorplzsaveme;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainRecordActivity extends AppCompatActivity {
    private RecordAdapter adapter;
    private ArrayList<Record> records = new ArrayList<>();

    //sample record list
    Record r1 = new Record("Record1","recordDescription");
    Record r2 = new Record("Record1","recordDescription");
    Record r3 = new Record("Record1","recordDescription");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_record);

        records.add(r1);
        records.add(r2);
        records.add(r3);

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
//                Intent intent = new Intent(getApplicationContext(), EditProblemActivity.class);
//                intent.putExtra("Pos", position);
//                startActivity(intent);
                Log.d("rview", Integer.toString(position));
            }
        });
    }
}