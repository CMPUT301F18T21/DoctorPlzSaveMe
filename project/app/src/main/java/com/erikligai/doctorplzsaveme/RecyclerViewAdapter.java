package com.erikligai.doctorplzsaveme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mPatientIds = new ArrayList<>();
    private ArrayList<String> mPatientEmails = new ArrayList<>();
    private ArrayList<String> mPatientPhones = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mPatientIds, ArrayList<String> mPatientEmails, ArrayList<String> mPatientPhones, Context mContext) {
        this.mPatientIds = mPatientIds;
        this.mPatientEmails = mPatientEmails;
        this.mPatientPhones = mPatientPhones;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder called");
        viewHolder.patientID.setText(mPatientIds.get(i)); // obtains id at index
        viewHolder.patientEmail.setText(mPatientEmails.get(i)); // obtains email at index
        viewHolder.patientPhone.setText(mPatientPhones.get(i)); // obtains phone at index

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mPatientIds.get(i));
                Toast.makeText(mContext, mPatientIds.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPatientIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView patientID;
        TextView patientEmail;
        TextView patientPhone;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientID = itemView.findViewById(R.id.patient_id);
            patientEmail = itemView.findViewById(R.id.patient_email);
            patientPhone = itemView.findViewById(R.id.patient_phone);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
