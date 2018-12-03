package com.erikligai.doctorplzsaveme.Activities;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.R;

import java.util.ArrayList;

/**
 *  Allow patient to edit body location photos.
 */

public class EditPhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photos);
        ArrayList<Bitmap> PhotoList = (ArrayList<Bitmap>) getIntent().getSerializableExtra("photoList");
        DisplayPhotos(PhotoList);
    }

    protected void DisplayPhotos(ArrayList<Bitmap> PhotoList){
        ImageView image = findViewById(R.id.imageView1);
        image.setImageBitmap(PhotoList.get(0));
        image.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Photo clicked!", Toast.LENGTH_SHORT).show();
                AlertDialog alertDialog = new AlertDialog.Builder(EditPhotosActivity.this).create();
                alertDialog.setTitle("EDIT Photos");
                alertDialog.setMessage("Would you like to delete the photo you clicked?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DELETE",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }
}
