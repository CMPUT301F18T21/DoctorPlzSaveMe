package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EditRecordFourActivity extends FragmentActivity implements OnMapReadyCallback,View.OnClickListener  {
    private GoogleMap mMap;
    private Button backBtn3,saveBtn;
    private int problem_index,record_index;
    private LatLng geolocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_four);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("P_Pos",-1);
        record_index = intent.getIntExtra("R_Pos",-1);
        backBtn3 = findViewById(R.id.backButton3);
        saveBtn = findViewById(R.id.saveButton);
        backBtn3.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        geolocation = RecordBuffer.getInstance().getRecord().getGeolocation();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton3:
                openEditRecordThreeActivity();
                break;

            case R.id.saveButton:
                RecordBuffer.getInstance().editRecord(problem_index, record_index);
                finish();
                break;
        }
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

    //TODO: Get onClick LatLng.

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng edmonton = new LatLng(53.5444, -113.4909);
        if (geolocation != null) {
            mMap.addMarker(new MarkerOptions().position(geolocation).title(geolocation.latitude + " : " + geolocation.longitude));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geolocation,15));
            //mMap.animateCamera(CameraUpdateFactory.zoomIn());

            //mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(edmonton,15));
            //mMap.animateCamera(CameraUpdateFactory.zoomIn());
            //mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);

                RecordBuffer.getInstance().getRecord().setGeolocation(markerOptions.getPosition());
            }
        });
    }

//    @Override
//    public boolean onMarkerClick(final Marker marker) {
//
//        Toast.makeText(this, marker.getTitle() + " has been clicked ", Toast.LENGTH_SHORT).show();
//        return false;
//    }

    private void openEditRecordThreeActivity(){
        Intent intent = new Intent(this, EditRecordThreeActivity.class);
        intent.putExtra("R_Pos", record_index);
        intent.putExtra("P_Pos", problem_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openEditRecordThreeActivity();
    }
}
