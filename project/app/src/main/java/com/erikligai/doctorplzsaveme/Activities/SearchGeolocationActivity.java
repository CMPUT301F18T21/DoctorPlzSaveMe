package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchGeolocationActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    private GoogleMap mMap;

    private Marker Van;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_geolocation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                LatLngBounds bound = toBounds(latLng);
                mMap.clear();

                int i,j;
                ArrayList<Problem> problems = Backend.getInstance().getPatientProblems();
                for (i = 0; i < problems.size(); i++) {
                    ArrayList<Record> records = problems.get(i).getRecords();
                    for( j = 0; j < records.size(); j++) {
                        if (records.get(j).getGeolocation() != null) {
                            if (bound.contains(records.get(j).getGeolocation())){
                                Van = mMap.addMarker(new MarkerOptions().position(records.get(j).getGeolocation()).title(problems.get(i).getTitle()).snippet(records.get(j).getTitle()));
                                List<Integer> index = new ArrayList<Integer>();
                                index.add(i);
                                index.add(j);
                                Van.setTag(index);
                                mMap.setOnMarkerClickListener(this);
                            }
                        }
                    }
                }
                if (Van != null) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            } catch (Exception e) {
                Toast.makeText(this, "Invalid search!", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

    public LatLngBounds toBounds(LatLng center) {
        double radiusInMeters = 1000;
        double distanceFromCenterToCorner = radiusInMeters * Math.sqrt(2.0);
        LatLng southwestCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 225.0);
        LatLng northeastCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 45.0);
        return new LatLngBounds(southwestCorner, northeastCorner);
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
