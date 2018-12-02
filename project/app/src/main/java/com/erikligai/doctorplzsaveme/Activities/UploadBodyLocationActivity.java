package com.erikligai.doctorplzsaveme.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class UploadBodyLocationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_body_location);
        RecyclerView photoRecycler = findViewById(R.id.PhotoRecyclerView);
        photoRecycler.setHasFixedSize(true);
        Button uploadNewPhoto = findViewById(R.id.addPhotoButton);
        uploadNewPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

        switch (v.getId()) {
            case R.id.addPhotoButton:
                dispatchTakePictureIntent();
                break;
        }
    }

    static final int ACTION_IMAGE_CAPTURE = 1;
    //static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, ACTION_IMAGE_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Map<String, Bitmap> photoMap = new HashMap();
        String photoId;
        Bitmap photo;
        Patient patient = Backend.getInstance().getPatientProfile();

        Intent intent = new Intent(this, UploadBodyLocationActivity.class);
        if (requestCode == ACTION_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            photoId = System.currentTimeMillis() +"_.jpg";
            patient.addPhoto(photoId, photo);
            Toast.makeText(getApplicationContext(), "Photo added!", Toast.LENGTH_SHORT).show();
        }
        //Backend.getInstance().UpdatePatient();
        //intent.putExtra("photoList", photoList);
        startActivity(intent);
    }

}
