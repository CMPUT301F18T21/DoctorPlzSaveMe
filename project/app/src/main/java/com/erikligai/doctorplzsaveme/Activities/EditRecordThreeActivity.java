package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;

import java.util.ArrayList;

public class EditRecordThreeActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backBtn2,nextBtn2;
    private ImageView zoomImg;
    private int problem_index, record_index;
    private Record record;
    private ArrayList<String> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_three);
        Intent intent = getIntent();
        problem_index = intent.getIntExtra("P_Pos",-1);
        record_index = intent.getIntExtra("R_Pos",-1);
        backBtn2 = findViewById(R.id.backButton3);
        nextBtn2 = findViewById(R.id.nextButton3);
        zoomImg = findViewById(R.id.imageView13);
        backBtn2.setOnClickListener(this);
        nextBtn2.setOnClickListener(this);
        zoomImg.setOnClickListener(this);

        record = RecordBuffer.getInstance().getRecord();
        photos = record.getPhotos();

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
                openEditRecordTwoActivity();
                break;

            case R.id.nextButton3:
                openEditRecordFourActivity();
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

            case R.id.imageView13:
                zoomImg.setVisibility(View.GONE);
                break;
        }
    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        findViewById(R.id.imageView12).setVisibility(View.INVISIBLE);
        displayPhotos();
    }

    public Bitmap StringToBitMap(String encodedString) {
        byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        return bitmap;
    }

    public void displayPhotos() {
        for (int i = 0; i < photos.size(); i++) {
            ImageView imageView = setImageView(i);
            Bitmap bitmap = StringToBitMap(photos.get(i));
            Bitmap r_bitmap = RotateBitmap(bitmap, 90);
            imageView.setImageBitmap(r_bitmap);
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
        if(photos.get(index)!=null){
            Log.d("click", "click");
            Bitmap bitmap = StringToBitMap(photos.get(index));
            Bitmap r_bitmap = RotateBitmap(bitmap, 90);
            zoomImg.setImageBitmap(r_bitmap);
            zoomImg.setVisibility(View.VISIBLE);
        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private void openEditRecordTwoActivity() {
        record.setPhotos(photos);
        Intent intent = new Intent(this, EditRecordTwoActivity.class);
        intent.putExtra("P_Pos", problem_index);
        intent.putExtra("R_Pos", record_index);
        finish();
        startActivity(intent);
    }

    private void openEditRecordFourActivity() {
        record.setPhotos(photos);
        Intent intent = new Intent(this, EditRecordFourActivity.class);
        intent.putExtra("P_Pos", problem_index);
        intent.putExtra("R_Pos", record_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        openEditRecordTwoActivity();
    }
}