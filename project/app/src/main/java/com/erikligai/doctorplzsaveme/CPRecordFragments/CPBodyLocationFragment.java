package com.erikligai.doctorplzsaveme.CPRecordFragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

public class CPBodyLocationFragment extends Fragment {
    private Record record;
    private Patient patient;
    private ImageView image, pointer;

    public CPBodyLocationFragment(){}

    public static CPBodyLocationFragment newInstance(String patient_id, int problem_index, int record_index) {
        CPBodyLocationFragment fragment = new CPBodyLocationFragment();
        Bundle args = new Bundle();
        args.putString("patient_id",patient_id);
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
        View view = inflater.inflate(R.layout.fragment_view_record_two, container, false);

        String patient_id = getArguments().getString("patient_id");
        int problem_index = getArguments().getInt("problem_index");
        int record_index = getArguments().getInt("record_index");
        patient = Backend.getInstance().GetCPPatient(patient_id);
        record = Backend.getInstance().GetCPPatientRecord(patient_id, problem_index,record_index);

        TextView emptyView = view.findViewById(R.id.empty_bodylocation_view);

        image = view.findViewById(R.id.imageView2);
        pointer = view.findViewById(R.id.imageView);

        if (record.getPhotoid().equals("")){
            emptyView.setVisibility(View.VISIBLE);
        } else {
            displayBodyLocation();
        }

        return view;
    }

    public Bitmap StringToBitMap(String encodedString) {
        byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        return bitmap;
    }

    public void displayBodyLocation() {
        int photo_index = patient.getPhotoIds().indexOf(record.getPhotoid());
        if (photo_index == 0){
            image.setImageResource(R.drawable.front);
        } else if (photo_index == 1){
            image.setImageResource(R.drawable.back);
        } else {
            Bitmap bitmap = StringToBitMap(patient.getPhotos().get(photo_index));
            image.setImageBitmap(bitmap);
        }

        image.post(new Runnable() {
            @Override
            public void run() {
                pointer.setX(record.getXpos()*image.getWidth());
                pointer.setY(record.getYpos()*image.getHeight());
                pointer.setVisibility(View.VISIBLE);
            }
        });
    }
}

