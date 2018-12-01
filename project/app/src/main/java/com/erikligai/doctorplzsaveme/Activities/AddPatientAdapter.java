package com.erikligai.doctorplzsaveme.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;

import java.util.ArrayList;

public class AddPatientAdapter extends RecyclerView.Adapter<AddPatientAdapter.AddPatientViewHolder> implements Filterable {
    private ArrayList<Patient> mPatients;

    private ArrayList<Patient> mPatientsCopy;
    private Context mContext;

    public static class AddPatientViewHolder extends RecyclerView.ViewHolder {
        TextView patientID;
        TextView patientEmail;
        TextView patientPhone;
        ConstraintLayout parentLayout;
        public AddPatientViewHolder(@NonNull View itemView) {
            super(itemView);
            patientID = itemView.findViewById(R.id.patient_id);
            patientEmail = itemView.findViewById(R.id.patient_email);
            patientPhone = itemView.findViewById(R.id.patient_phone);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    public AddPatientAdapter(ArrayList<Patient> patients, Context mContext) {
        this.mPatients = patients;
        this.mContext = mContext;

        this.mPatientsCopy = new ArrayList<>(mPatients);
    }


    @NonNull
    @Override
    public AddPatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new AddPatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddPatientViewHolder addPatientViewHolder, final int i) {
        addPatientViewHolder.patientID.setText(mPatients.get(i).getID()); // obtains id at index
        addPatientViewHolder.patientEmail.setText(mPatients.get(i).getEmail()); // obtains email at index
        addPatientViewHolder.patientPhone.setText(mPatients.get(i).getPhone()); // obtains phone at index

        addPatientViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here the name in the patient list is clicked
                // this should add the patient to the care provider's patient list and return to patient list activity

                // use global static variable of userid to retrieve care provider's patient list from backend
                // check if clicked on patient already exists in list
                // if not, add to list
                // update backend
                // return to CareProviderActivity
                // Display toast message to notify care provider that patient was added




//
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

    @Override
    public Filter getFilter() {
        return addPatientFilter;
    }


    private Filter addPatientFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Patient> filteredPatients = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredPatients.addAll(mPatientsCopy);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Patient patient : mPatientsCopy) {
                    if (patient.getID().contains(filterPattern)) {
                        filteredPatients.add(patient);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredPatients;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mPatients.clear();
            mPatients.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
