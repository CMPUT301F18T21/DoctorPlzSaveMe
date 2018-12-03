package com.erikligai.doctorplzsaveme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.CPViewRecordLocationsActivity;
import com.erikligai.doctorplzsaveme.Activities.MainRecordActivity;
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.patientID.setText(mPatients.get(i).getID()); // obtains id at index
        viewHolder.patientEmail.setText(mPatients.get(i).getEmail()); // obtains email at index
        viewHolder.patientPhone.setText(mPatients.get(i).getPhone()); // obtains phone at index
        viewHolder.option.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(),view);
                //inflating menu from xml resource
                popup.inflate(R.menu.patient_item_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.patient_menu1:
                                //handle menu1 click
                                ClickMenuOne();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }

            void ClickMenuOne(){
                Log.d("rview", "view/add");
                Intent intent = new Intent(viewHolder.itemView.getContext(), CPViewRecordLocationsActivity.class);
                intent.putExtra("patientID", mPatients.get(i).getID());
                viewHolder.itemView.getContext().startActivity(intent);
            }

        });

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PatientProblemsActivity.class);
                intent.putExtra("patientID", mPatients.get(i).getID()); // attach patient id to intent
                mContext.startActivity(intent); // go to problem list of patient
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
        ImageButton option;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientID = itemView.findViewById(R.id.patient_username);
            patientEmail = itemView.findViewById(R.id.patient_email);
            patientPhone = itemView.findViewById(R.id.patient_phone);
            option = itemView.findViewById(R.id.patient_item_options);
            parentLayout = itemView.findViewById(R.id.patient_record_layout);
        }
    }

}
