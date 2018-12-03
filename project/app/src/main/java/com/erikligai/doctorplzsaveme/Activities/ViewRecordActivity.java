package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.RecordFragments.BodyLocationFragment;
import com.erikligai.doctorplzsaveme.RecordFragments.DetailFragment;
import com.erikligai.doctorplzsaveme.RecordFragments.MapFragment;
import com.erikligai.doctorplzsaveme.RecordFragments.PhotoFragment;

public class ViewRecordActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int problem_index,record_index;
    private DetailFragment detailFragment;
    private MapFragment mapFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_detail:
                    getSupportActionBar().setTitle(R.string.title_detail);
                    DetailFragment detailFragment = DetailFragment.newInstance(problem_index,record_index);
                    loadFragment(detailFragment);
                    return true;

                case R.id.navigation_bodylocation:
                    getSupportActionBar().setTitle(R.string.title_bodylocation);
                    BodyLocationFragment bodyLocationFragment = BodyLocationFragment.newInstance(problem_index,record_index );
                    return true;

                case R.id.navigation_photo:
                    getSupportActionBar().setTitle(R.string.title_photo);
                    PhotoFragment photoFragment = PhotoFragment.newInstance(problem_index,record_index );
                    loadFragment(photoFragment);
                    return true;

                case R.id.navigation_map:
                    getSupportActionBar().setTitle(R.string.title_map);
                    MapFragment mapFragment = MapFragment.newInstance(problem_index,record_index );
                    loadFragment(mapFragment);
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
        problem_index = intent.getIntExtra("P_Pos", 0);
        record_index = intent.getIntExtra("R_Pos", 0);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        DetailFragment detailFragment = DetailFragment.newInstance(problem_index,record_index);
        MapFragment mapFragment = MapFragment.newInstance(problem_index,record_index );
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
