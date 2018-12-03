package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class SearchGeolocationActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    private GoogleMap mMap;

    //private static final LatLng VAN = new LatLng(49.246292, -123.116226);
    private Marker Van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_geolocation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     */

    //TODO: 1. Talk to backend. Get all records for patient.
    //TODO: 2. For each record, try getting the geolocation.
    //TODO: 3. if record has a geolocation info, create a marker from it.

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        int i,j;
        ArrayList<Problem> problems = Backend.getInstance().getPatientProblems();
        for (i = 0; i < problems.size(); i++) {
            ArrayList<Record> records = problems.get(i).getRecords();
            for( j = 0; j < records.size(); j++) {
                if (records.get(j).getGeolocation() != null) {
                    Van = mMap.addMarker(new MarkerOptions().position(records.get(j).getGeolocation()).title(problems.get(i).getTitle()).snippet(records.get(j).getTitle()));

                    List<Integer> index = new ArrayList<Integer>();
                    index.add(i);
                    index.add(j);
                    Van.setTag(index);
                    mMap.setOnMarkerClickListener(this);
                }
            }
        }
        if (Van != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Van.getPosition(), 10));
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        //int num = (int) marker.getTag();                         // get data from marker(probably recordID)
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
