package com.erikligai.doctorplzsaveme.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class RecyclerSelectBodyLocationAdapter extends RecyclerView.Adapter<RecyclerSelectBodyLocationAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewPhotoAdapter";

    private Patient patient;
    private ArrayList<String> photoIds;
    private ArrayList<String> photos;
    private ArrayList<String> photoLables;
    private Context mContext;

    public RecyclerSelectBodyLocationAdapter(Context mContext) {
        this.patient = Backend.getInstance().getPatientProfile();
        this.photos = patient.getPhotos();
        this.photoIds = patient.getPhotoIds();
        this.photoLables = patient.getPhotoLabels();
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder called");
            // do something with key and/or tab
        //SortedMap<String, Bitmap> photo = photos.entrySet().toArray();
        viewHolder.imgViewIcon.setScaleType((ImageView.ScaleType.CENTER_CROP));
        if(i == 0){
            viewHolder.imgViewIcon.setImageResource(R.drawable.front);
        } else if (i==1){
            viewHolder.imgViewIcon.setImageResource(R.drawable.back);
        } else {
            viewHolder.imgViewIcon.setImageBitmap(getBitmapFromString(photos.get(i)));
        }
        viewHolder.label.setText(photoLables.get(i));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "photo menu displayed! ");
                AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();

                alertDialog.setTitle(photoLables.get(i));
                alertDialog.setMessage("Would you like to select this Body Location Photo?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Select",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //photoLables.add(i,input.getText().toString());

                                RecordBuffer.getInstance().setImageID(photoIds.get(i));
                                dialog.dismiss();
                                ((Activity)v.getContext()).finish();
                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgViewIcon;
        public TextView label;

        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            imgViewIcon = itemView.findViewById(R.id.photo);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    private Bitmap getBitmapFromString(String stringPicture) {
        /*
         * This Function converts the String back to Bitmap
         * */
        byte[] decodedString = Base64.decode(stringPicture, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
