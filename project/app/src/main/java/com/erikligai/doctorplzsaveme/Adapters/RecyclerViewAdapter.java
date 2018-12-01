package com.erikligai.doctorplzsaveme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.PatientProblemsActivity;
import com.erikligai.doctorplzsaveme.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Patient> mPatients;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Patient> patients, Context mContext) {

        this.mPatients = patients;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        Log.d(TAG, "onBindViewHolder called");
        viewHolder.patientID.setText(mPatients.get(i).getID()); // obtains id at index
        viewHolder.patientEmail.setText(mPatients.get(i).getEmail()); // obtains email at index
        viewHolder.patientPhone.setText(mPatients.get(i).getPhone()); // obtains phone at index

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PatientProblemsActivity.class);
                intent.putExtra("patientID", mPatients.get(i).getID()); // attach patient id to intent
                mContext.startActivity(intent); // go to problem list of patient
//                Log.d(TAG, "onClick: clicked on: " + mPatients.get(i).getName());
//                Toast.makeText(mContext, mPatients.get(i).getName(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onClick: clicked on: " + mPatients.get(i).getID());
                Toast.makeText(mContext, mPatients.get(i).getID(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPatients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView patientID;
        TextView patientEmail;
        TextView patientPhone;
        ConstraintLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientID = itemView.findViewById(R.id.comment_text);
            patientEmail = itemView.findViewById(R.id.comment_date);
            patientPhone = itemView.findViewById(R.id.patient_phone);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
