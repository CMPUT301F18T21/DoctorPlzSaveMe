package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewRecordLocationsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private Marker mPerth;


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
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //TODO: 1. Talk to backend. Get all records for patient.
    //TODO: 2. for each record, try getting the geolocation.
    //TODO: 3. if record has a geolocation info, create a marker from it.

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mPerth = mMap.addMarker(new MarkerOptions().position(PERTH).title("Record name").snippet("record info"));
        mPerth.setTag(0);        //pass data into marker here
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PERTH));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                int num = (int) marker.getTag();                                // get data from marker(probably recordID)

                Intent I = new Intent(ViewRecordLocationsActivity.this, EditRecordActivity.class);
                startActivity(I);
            }
        });

        //for record in recordList
        // LatLng location = record.getGeolocation();
        // mMap.addMarker(new MarkerOptions().position(location).title(record.getProblem));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location)); // move camera to last added location.
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        int num = (int) marker.getTag();                         // get data from marker(probably recordID)
        Toast.makeText(this, marker.getTitle() + " has been clicked ", Toast.LENGTH_SHORT).show();
        return false;
    }

}
