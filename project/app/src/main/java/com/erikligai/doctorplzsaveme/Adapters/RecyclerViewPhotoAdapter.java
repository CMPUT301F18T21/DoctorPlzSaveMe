package com.erikligai.doctorplzsaveme.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.EditPhotosActivity;
import com.erikligai.doctorplzsaveme.Activities.UploadBodyLocationActivity;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

public class RecyclerViewPhotoAdapter extends RecyclerView.Adapter<RecyclerViewPhotoAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewPhotoAdapter";

    private Patient patient;
    private ArrayList<String> photoIds;
    private ArrayList<Bitmap> photos;
    private Context mContext;

    public RecyclerViewPhotoAdapter(Context mContext) {
        this.patient = Backend.getInstance().getPatientProfile();
        this.photos = patient.getPhotos();
        this.photoIds = patient.getPhotoIds();
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
        viewHolder.imgViewIcon.setImageBitmap(photos.get(i));                                           // obtains id at index

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "photo menu displayed! ");
                Toast.makeText(mContext, "photo menu displayed for: "+ photoIds.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgViewIcon;

        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewIcon = itemView.findViewById(R.id.photo_item);
        }
    }
}
