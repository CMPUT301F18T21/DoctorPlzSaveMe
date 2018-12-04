package com.erikligai.doctorplzsaveme.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class RecyclerSlideshowAdapter extends RecyclerView.Adapter<RecyclerSlideshowAdapter.ViewHolder>{
    private static final String TAG = "RecyclerSlideshowAdapter";

    private ArrayList<String> photos = new ArrayList<>();
    private ArrayList<Record> records;
    private Context mContext;

    public RecyclerSlideshowAdapter(Context mContext, int problemIndex) {
        this.records = Backend.getInstance().getPatientRecords(problemIndex);
        for (int i=0; i < this.records.size();i++){
            for (int j=0; j< this.records.get(i).getPhotos().size(); j++){
                this.photos.add(this.records.get(i).getPhotos().get(j));
            }
        }
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerSlideshowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerSlideshowAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.imgViewIcon.setScaleType((ImageView.ScaleType.CENTER_CROP));
        viewHolder.imgViewIcon.setImageBitmap(getBitmapFromString(photos.get(i)));
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
