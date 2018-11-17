package com.erikligai.doctorplzsaveme;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button one = findViewById(R.id.viewProblemsButton);
        one.setOnClickListener(this); // calling onClick() method

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

    /** Functions for taking a photo */
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
