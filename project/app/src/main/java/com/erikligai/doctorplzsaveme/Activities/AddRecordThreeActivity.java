package com.erikligai.doctorplzsaveme.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class AddRecordThreeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backBtn3, nextBtn3;
    private ImageView addBtn,zoomImg;
    private int problem_index;
    private ArrayList<String> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_three);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos", 0);
        backBtn3 = findViewById(R.id.backButton3);
        nextBtn3 = findViewById(R.id.nextButton3);
        addBtn = findViewById(R.id.imageView12);
        zoomImg = findViewById(R.id.imageView13);
        backBtn3.setOnClickListener(this);
        nextBtn3.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        zoomImg.setOnClickListener(this);

        photos = RecordBuffer.getInstance().getRecord().getPhotos();

        //date = intent.getLongExtra("date",-1);
        // Get buttons
//        backBtn2 = findViewById(R.id.backButton2);
//        nextBtn2 = findViewById(R.id.nextButton2);
//        addPhotoBtn  =findViewById(R.id.addPhotoButton);
//        addBodylocationBtn = findViewById(R.id.addBodylocationButton);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton3:
                openAddRecordTwoActivity();
                break;
            case R.id.nextButton3:
                openAddRecordFourActivity();
                break;
            case R.id.imageView1:
                zoomImage(0);
                break;
            case R.id.imageView2:
                zoomImage(1);
                break;
            case R.id.imageView3:
                zoomImage(2);
                break;
            case R.id.imageView4:
                zoomImage(3);
                break;
            case R.id.imageView5:
                zoomImage(4);
                break;
            case R.id.imageView6:
                zoomImage(5);
                break;
            case R.id.imageView7:
                zoomImage(6);
                break;
            case R.id.imageView8:
                zoomImage(7);
                break;
            case R.id.imageView9:
                zoomImage(8);
                break;
            case R.id.imageView10:
                zoomImage(9);
                break;
            case R.id.imageView11:
                zoomImage(10);
                break;

            case R.id.imageView12:
                if (photos.size()<12) {
                    dispatchTakePictureIntent();
                } else {
                    Toast.makeText(getApplicationContext(), "You can only add a maximum of 11 photos per Record!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.imageView13:
                zoomImg.setVisibility(View.GONE);
                break;
        }
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        displayPhotos();
    }

    static final int ACTION_IMAGE_CAPTURE = 1;
    //static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        // finish current display before calling camera.
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, ACTION_IMAGE_CAPTURE);
        Log.i("message", "String");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap photo;
        if (requestCode == ACTION_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            String photoStr = getStringFromBitmap(photo);
            photos.add(photoStr);
            Log.i("photo", photoStr);
            Toast.makeText(getApplicationContext(), "Photo added!", Toast.LENGTH_SHORT).show();
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

    public Bitmap StringToBitMap(String encodedString) {
        byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        return bitmap;
    }

    private void openAddRecordTwoActivity() {
        RecordBuffer.getInstance().getRecord().setPhotos(photos);
        Intent intent = new Intent(this, AddRecordTwoActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }

    private void openAddRecordFourActivity() {
        RecordBuffer.getInstance().getRecord().setPhotos(photos);
        Intent intent = new Intent(this, AddRecordFourActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openAddRecordTwoActivity();
    }

    public void displayPhotos() {
        for (int i = 0; i < photos.size(); i++) {
            ImageView imageView = setImageView(i);
            Bitmap bitmap = StringToBitMap(photos.get(i));
            imageView.setImageBitmap(bitmap);
        }

    }

    public ImageView setImageView(int index) {

        ImageView imageView;
        switch (index) {
            case 0:
                imageView = findViewById(R.id.imageView1);
                break;
            case 1:
                imageView = findViewById(R.id.imageView2);
                break;
            case 2:
                imageView = findViewById(R.id.imageView3);
                break;
            case 3:
                imageView = findViewById(R.id.imageView4);
                break;
            case 4:
                imageView = findViewById(R.id.imageView5);
                break;
            case 5:
                imageView = findViewById(R.id.imageView6);
                break;
            case 6:
                imageView = findViewById(R.id.imageView7);
                break;
            case 7:
                imageView = findViewById(R.id.imageView8);
                break;
            case 8:
                imageView = findViewById(R.id.imageView9);
                break;
            case 9:
                imageView = findViewById(R.id.imageView10);
                break;
            case 10:
                imageView = findViewById(R.id.imageView11);
                break;
            default:
                imageView = null;
                break;
        }
        imageView.setOnClickListener(this);
        imageView.setClickable(true);
        return imageView;
    }

    public void zoomImage(int index){
        Bitmap bitmap = StringToBitMap(photos.get(index));
        zoomImg.setImageBitmap(bitmap);
        zoomImg.setVisibility(View.VISIBLE);
    }

}

