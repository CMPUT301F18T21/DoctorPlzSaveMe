package com.erikligai.doctorplzsaveme;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import java.util.ArrayList;

public class RecordListActivity extends AppCompatActivity {
    private RecordAdapter adapter;
    private ArrayList<Record> records = new ArrayList<>();

    //sample problem list
    Record r1 = new Record("Record1",null,"reordDescription");
    Record r2 = new Record("Record1",null,"reordDescription");
    Record r3 = new Record("Record1",null,"reordDescription");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        records.add(r1);
        records.add(r2);
        records.add(r3);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.records);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fab", "add problems!");
                //calls AddProblemsActivity
            }
        });

        RecyclerView problemRView = findViewById(R.id.problems_recyclerview);
        problemRView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        problemRView.setLayoutManager(layoutManager);


        adapter = new ProblemAdapter(problems);
        problemRView.setAdapter(adapter);
        adapter.setOnEntryClickListener(new ProblemAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(int position) {
//                Intent intent = new Intent(getApplicationContext(), EditProblemActivity.class);
//                intent.putExtra("Pos", position);
//                startActivity(intent);
            }
        });
    }
}
