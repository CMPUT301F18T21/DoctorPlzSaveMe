package com.erikligai.doctorplzsaveme.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.erikligai.doctorplzsaveme.Adapters.RecordAdapter;
import com.erikligai.doctorplzsaveme.Models.Comment;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;

import java.util.ArrayList;

public class CPViewRecordsActivity extends AppCompatActivity {

    private ArrayAdapter<Record> adapter;
    private RecyclerView recyclerView;

    private ArrayList<Record> records = new ArrayList<>();

    //sample record list
    Record r1 = new Record("Record1","recordDescription");
    Record r2 = new Record("Record1","recordDescription");
    Record r3 = new Record("Record1","recordDescription");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_problem);

        records.add(r1);
        records.add(r2);
        records.add(r3);

        recyclerView = findViewById(R.id.cp_record_recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ArrayAdapter<Record>(CPViewRecordsActivity.this, android.R.layout.simple_list_item_1, records);
        //recyclerView.setAdapter(adapter);
        //adapter.setOnEntryClickListener(new RecordAdapter.OnEntryClickListener() {

        //}
    }
}
