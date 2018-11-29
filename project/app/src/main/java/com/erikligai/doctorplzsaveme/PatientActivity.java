package com.erikligai.doctorplzsaveme;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.erikligai.doctorplzsaveme.Activities.EditProfileActivity;
import com.erikligai.doctorplzsaveme.Activities.ViewRecordLocationsActivity;
import com.erikligai.doctorplzsaveme.Activities.MainProblemActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button viewProblemsBtn = findViewById(R.id.viewProblemsButton);
        viewProblemsBtn.setOnClickListener(this); // calling onClick() method

        Button editProfileBtn = findViewById(R.id.editProfileButton);
        editProfileBtn.setOnClickListener(this);

        Button viewLocationBtn = findViewById(R.id.viewLocationButton);
        viewLocationBtn.setOnClickListener(this);

        Button uploadBodyLocationBtn = findViewById(R.id.uploadBodyLocationButton);
        uploadBodyLocationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.viewProblemsButton:
                viewProblems(findViewById(R.id.content));
                break;
            case R.id.uploadBodyLocationButton:
                uploadBodyLocation(findViewById(R.id.content));
                break;
            case R.id.editProfileButton:
                editProfile(findViewById(R.id.content));
                break;
            case R.id.viewLocationButton:
                viewLocation(findViewById(R.id.content));
                break;
            default:
                break;
        }
    }

    /** Called when the user taps the View Problems button */
    public void viewProblems(View view) {
        Intent intent = new Intent(this, MainProblemActivity.class);
        startActivity(intent);
    }

    public void uploadBodyLocation(View view) {
        dispatchTakePictureIntent();
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void viewLocation(View view) {
        Intent intent = new Intent(this, ViewRecordLocationsActivity.class);
        startActivity(intent);
    }

    /** Functions for taking a photo */
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);           //REQUEST_IMAGE_CAPTURE
        }
    }

    /** taking photo with IO functions
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         /* suffix
                storageDir      /* directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                System.out.print(ex.toString());
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    **/

}
