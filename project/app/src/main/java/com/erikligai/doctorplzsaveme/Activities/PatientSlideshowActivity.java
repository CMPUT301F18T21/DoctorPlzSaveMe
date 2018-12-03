package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.erikligai.doctorplzsaveme.Adapters.RecyclerSlideshowAdapter;
import com.erikligai.doctorplzsaveme.Adapters.RecyclerViewPhotoAdapter;
import com.erikligai.doctorplzsaveme.R;

public class PatientSlideshowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_slideshow);

        Intent intent = getIntent();

        // create recycler view with RecyclerSlideshowAdapter.
        RecyclerView photoRecycler = findViewById(R.id.PhotoRecyclerView);
        photoRecycler.setHasFixedSize(true);
        RecyclerSlideshowAdapter adapter = new RecyclerSlideshowAdapter(this,intent.getIntExtra("Pos",-1));
        photoRecycler.setAdapter(adapter);
        photoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
