package com.erikligai.doctorplzsaveme;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainMapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /** will be tested with multiple problem locations*/
        mPerth = mMap.addMarker(new MarkerOptions().position(PERTH).title("marker test!!!"));
        mPerth.setTag(0);//pass data into marker here.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PERTH));

        mMap.setOnMarkerClickListener(this);

        //for record in recordList
        // LatLng location = record.getGeolocation();
        // mMap.addMarker(new MarkerOptions().position(location).title(record.getProblem));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(location)); // move camera to last added location.
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        int num = (int)marker.getTag();

        Toast.makeText(this, marker.getTitle() + " has been clicked ", Toast.LENGTH_SHORT).show();
        return false;
    }




}
