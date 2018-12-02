package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CPViewGeolocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Button doneBtn;
    private Button backBtn;
    private Record record;
    private int ProblemPosition;
    private int RecordPosition;
    private String patientID;
    private LatLng geolocation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_view_geolocation);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        RecordPosition = intent.getIntExtra("RecordPos",-1);
        patientID = intent.getStringExtra("patientId");

        Backend backend = Backend.getInstance();
        record = backend.GetCPPatientRecord(patientID,ProblemPosition,RecordPosition);

        geolocation = record.getGeolocation();

        doneBtn = findViewById(R.id.cpRecordDone);
        backBtn = findViewById(R.id.cpRecordBack3);

        doneBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPRecordActivity();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCPViewBodylocationActivity();
            }
        });

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng edmonton = new LatLng(53.5444, -113.4909);
        if (geolocation != null) {
            mMap.addMarker(new MarkerOptions().position(geolocation).title("Marker"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geolocation,15));
            //mMap.animateCamera(CameraUpdateFactory.zoomIn());

            //mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(edmonton,15));
            //mMap.animateCamera(CameraUpdateFactory.zoomIn());
            //mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        }
    }

    public void openCPViewBodylocationActivity(){
        Intent intent = new Intent(this,AddRecordTwoActivity.class);
        intent.putExtra("ProblemPos", ProblemPosition);
        intent.putExtra("RecordPos", RecordPosition);
        intent.putExtra("patientId",patientID);
        startActivity(intent);
    }

    public void openCPRecordActivity(){
        Intent intent = new Intent(this,CPRecordActivity.class);
        intent.putExtra("problemID", Integer.toString(ProblemPosition));
        intent.putExtra("patientID",patientID);
        startActivity(intent);
    }
}
