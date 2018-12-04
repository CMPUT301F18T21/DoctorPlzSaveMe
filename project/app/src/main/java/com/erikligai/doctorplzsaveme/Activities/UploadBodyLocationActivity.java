package com.erikligai.doctorplzsaveme.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Adapters.RecyclerViewPhotoAdapter;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.io.ByteArrayOutputStream;

public class UploadBodyLocationActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerViewPhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_body_location);

        RecyclerView photoRecycler = findViewById(R.id.PhotoRecyclerView);
        photoRecycler.setHasFixedSize(true);
        adapter = new RecyclerViewPhotoAdapter(this);
        photoRecycler.setAdapter(adapter);
        photoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Button uploadNewPhoto = findViewById(R.id.addPhotoButton);
        uploadNewPhoto.setOnClickListener(this);
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //findViewById(R.id.imageView12).setVisibility(View.INVISIBLE);
        adapter.notifyDataSetChanged();
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
    static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;

    private void dispatchTakePictureIntent() {

        // finish current display before calling camera.
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else{
            Log.i("CAMERA OPENED", "TWO");
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, ACTION_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("CAMERA OPENED", "TWO");
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent, ACTION_IMAGE_CAPTURE);

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    Toast.makeText(getApplicationContext(), "Camera denied", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoId;
        Bitmap photo;
        String photoLabel = "";

        //Intent intent = new Intent(this, UploadBodyLocationActivity.class);
        if (requestCode == ACTION_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            String photoStr = getStringFromBitmap(photo);
            photoId = System.currentTimeMillis() +"_.jpg";                                          // current time in milliseconds.
            Log.i("photo", photoStr);
            Log.i("photoId", photoId);
            Backend.getInstance().getPatientProfile().addPhoto(photoId, photoStr, photoLabel);                           // store photo in backend.
            Toast.makeText(getApplicationContext(), "Photo added!", Toast.LENGTH_SHORT).show();
            //Backend.getInstance().UpdatePatientRunnable();
        }
    }

    private String getStringFromBitmap(Bitmap bitmapPicture) {
        /*
         * This functions converts Bitmap picture to a string which can be
         * JSONified.
         * */
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

}
