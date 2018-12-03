package com.erikligai.doctorplzsaveme.RecordFragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PhotoFragment extends Fragment implements View.OnClickListener {
    private Record record;
    private ArrayList<String> photos;
    private ImageView zoomImg;

    public PhotoFragment(){}

    public static PhotoFragment newInstance(int problem_index, int record_index) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putInt("problem_index", problem_index);
        args.putInt("record_index",record_index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_record_three, container, false);


        int problem_index = getArguments().getInt("problem_index");
        int record_index = getArguments().getInt("record_index");
        record = Backend.getInstance().getPatientRecords(problem_index).get(record_index);
        photos = record.getPhotos();

        zoomImg = view.findViewById(R.id.imageView12);
        zoomImg.setOnClickListener(this);
        TextView emptyView = view.findViewById(R.id.empty_photo_view);


        if (photos.size()!=0) {
            displayPhotos(view);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public void onClick(View v) {
        switch (v.getId()) {
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

    public void displayPhotos(View view) {
        for (int i = 0; i < photos.size(); i++) {
            ImageView imageView = setImageView(view, i);
            Bitmap bitmap = StringToBitMap(photos.get(i));
            imageView.setImageBitmap(bitmap);
        }
    }

    public ImageView setImageView(View view, int index) {

        ImageView imageView;
        switch (index) {
            case 0:
                imageView = view.findViewById(R.id.imageView1);
                break;
            case 1:
                imageView = view.findViewById(R.id.imageView2);
                break;
            case 2:
                imageView = view.findViewById(R.id.imageView3);
                break;
            case 3:
                imageView = view.findViewById(R.id.imageView4);
                break;
            case 4:
                imageView = view.findViewById(R.id.imageView5);
                break;
            case 5:
                imageView = view.findViewById(R.id.imageView6);
                break;
            case 6:
                imageView = view.findViewById(R.id.imageView7);
                break;
            case 7:
                imageView = view.findViewById(R.id.imageView8);
                break;
            case 8:
                imageView = view.findViewById(R.id.imageView9);
                break;
            case 9:
                imageView = view.findViewById(R.id.imageView10);
                break;
            case 10:
                imageView = view.findViewById(R.id.imageView11);
                break;
            default:
                imageView = null;
                break;
        }
        imageView.setOnClickListener(this);
        imageView.setClickable(true);
        imageView.setVisibility(View.VISIBLE);
        return imageView;
    }

    public Bitmap StringToBitMap(String encodedString) {
        byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        return bitmap;
    }


    public void zoomImage(int index){
        if(photos.get(index)!=null){
            Bitmap bitmap = StringToBitMap(photos.get(index));
            zoomImg.setImageBitmap(bitmap);
            zoomImg.setVisibility(View.VISIBLE);
        }
    }
}