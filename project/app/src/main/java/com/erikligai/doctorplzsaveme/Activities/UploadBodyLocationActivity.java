package com.erikligai.doctorplzsaveme.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Adapters.RecyclerViewAdapter;
import com.erikligai.doctorplzsaveme.Adapters.RecyclerViewPhotoAdapter;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UploadBodyLocationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_body_location);
        /**
        RecyclerView photoRecycler = findViewById(R.id.PhotoRecyclerView);
        photoRecycler.setHasFixedSize(true);
        RecyclerViewPhotoAdapter adapter = new RecyclerViewPhotoAdapter(this);
        photoRecycler.setAdapter(adapter);
        photoRecycler.setLayoutManager(new LinearLayoutManager(this));
        Button uploadNewPhoto = findViewById(R.id.addPhotoButton);
        uploadNewPhoto.setOnClickListener(this);
         */
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
        // finish current display before calling camera.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, ACTION_IMAGE_CAPTURE);
        Log.i("message", "String")
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoId;
        Bitmap photo;

        Intent intent = new Intent(this, UploadBodyLocationActivity.class);
        if (requestCode == ACTION_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            String photoStr = getStringFromBitmap(photo);
            photoId = System.currentTimeMillis() +"_.jpg";                                          // current time in milliseconds.
            Log.i("photo", photoStr);
            Log.i("photoId", photoId);
            //Backend.getInstance().addPatientPhoto(photoId, photo);                                // store photo in backend.
            Toast.makeText(getApplicationContext(), "Photo added!", Toast.LENGTH_SHORT).show();
        }
        //Backend.getInstance().UpdatePatient();
        //intent.putExtra("photoList", photoList);
        startActivity(intent);
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
