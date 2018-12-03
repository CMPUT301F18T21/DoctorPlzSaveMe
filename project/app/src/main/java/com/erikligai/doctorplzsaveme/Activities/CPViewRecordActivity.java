package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.CPRecordFragments.CPBodyLocationFragment;
import com.erikligai.doctorplzsaveme.CPRecordFragments.CPDetailFragment;
import com.erikligai.doctorplzsaveme.CPRecordFragments.CPGeolocationFragment;
import com.erikligai.doctorplzsaveme.CPRecordFragments.CPPhotoFragment;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.RecordFragments.BodyLocationFragment;
import com.erikligai.doctorplzsaveme.RecordFragments.DetailFragment;
import com.erikligai.doctorplzsaveme.RecordFragments.GeolocationFragment;
import com.erikligai.doctorplzsaveme.RecordFragments.PhotoFragment;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class CPViewRecordActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CPDetailFragment detailFragment;
    private CPBodyLocationFragment bodyLocationFragment;
    private CPPhotoFragment photoFragment;
    private CPGeolocationFragment geolocationFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_detail:
                    getSupportActionBar().setTitle(R.string.title_detail);
                    loadFragment(detailFragment);
                    return true;

                case R.id.navigation_bodylocation:
                    getSupportActionBar().setTitle(R.string.title_bodylocation);
                    loadFragment(bodyLocationFragment);
                    return true;

                case R.id.navigation_photo:
                    getSupportActionBar().setTitle(R.string.title_photo);
                    loadFragment(photoFragment);
                    return true;

                case R.id.navigation_map:
                    getSupportActionBar().setTitle(R.string.title_map);
                    loadFragment(geolocationFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        Intent intent = getIntent();
        int problem_index = intent.getIntExtra("problemIndex",-1);
        int record_index = intent.getIntExtra("recordIndex",-1);
        String patientID = intent.getStringExtra("patientID");

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        detailFragment = CPDetailFragment.newInstance(patientID,problem_index,record_index);
        bodyLocationFragment = CPBodyLocationFragment.newInstance(patientID,problem_index,record_index );
        photoFragment = CPPhotoFragment.newInstance(patientID,problem_index,record_index );
        geolocationFragment = CPGeolocationFragment.newInstance(patientID,problem_index,record_index );

        loadFragment(detailFragment);
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

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}
