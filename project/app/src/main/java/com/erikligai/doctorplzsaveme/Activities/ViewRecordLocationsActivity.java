package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;

import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;
import java.util.List;

public class ViewRecordLocationsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {
    /** creates and displays markers from record geolocation
     *  handles onclick marker and onWindowClick.
     */

    private GoogleMap mMap;
    private Marker Van;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // create markers here
        int i,j;
        ArrayList<Problem> problems = Backend.getInstance().getPatientProblems();
        for (i = 0; i < problems.size(); i++) {
            ArrayList<Record> records = problems.get(i).getRecords();
            for( j = 0; j < records.size(); j++) {
                if (records.get(j).getGeolocation() != null) {
                    // each marker has geolocation from record, title from problem title, snippet from record title.
                    Van = mMap.addMarker(new MarkerOptions().position(records.get(j).getGeolocation()).title(problems.get(i).getTitle()).snippet(records.get(j).getTitle()));

                    List<Integer> index = new ArrayList<Integer>();
                    index.add(i);
                    index.add(j);
                    Van.setTag(index);

                    mMap.setOnMarkerClickListener(this);
                    mMap.setOnInfoWindowClickListener(this);
                }
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Van.getPosition(),10));
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        // handle event when marker is clicked.
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        // handle when marker's info window is clicked.
        Intent intent = new Intent(ViewRecordLocationsActivity.this, ViewRecordActivity.class);
        List<Integer> index = (List<Integer>) marker.getTag();
        intent.putExtra("P_Pos", index.get(0));
        intent.putExtra("R_Pos", index.get(1));
        startActivity(intent);
    }

}
