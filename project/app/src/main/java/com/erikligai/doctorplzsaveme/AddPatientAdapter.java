package com.erikligai.doctorplzsaveme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AddPatientAdapter extends RecyclerView.Adapter<AddPatientAdapter.AddPatientViewHolder> implements Filterable {
//    private ArrayList<Patient> mPatients;
//
//    private ArrayList<Patient> mPatientsCopy;

    private ArrayList<String> mPatientIds = new ArrayList<>();
    private ArrayList<String> mPatientEmails = new ArrayList<>();
    private ArrayList<String> mPatientPhones = new ArrayList<>();
    private Context mContext;

    private ArrayList<String> mPatientIdsCopy;
    private ArrayList<String> mPatientEmailsCopy;
    private ArrayList<String> mPatientPhonesCopy;

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

    public AddPatientAdapter(ArrayList<String> mPatientIds, ArrayList<String> mPatientEmails, ArrayList<String> mPatientPhones, Context mContext) {
        this.mPatientIds = mPatientIds;
        this.mPatientEmails = mPatientEmails;
        this.mPatientPhones = mPatientPhones;
        this.mContext = mContext;

        mPatientIdsCopy = new ArrayList<>(mPatientIds);
        mPatientEmailsCopy = new ArrayList<>(mPatientEmails);
        mPatientPhonesCopy = new ArrayList<>(mPatientPhones);
    }


    @NonNull
    @Override
    public AddPatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new AddPatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddPatientViewHolder addPatientViewHolder, final int i) {
        addPatientViewHolder.patientID.setText(mPatientIds.get(i)); // obtains id at index
        addPatientViewHolder.patientEmail.setText(mPatientEmails.get(i)); // obtains email at index
        addPatientViewHolder.patientPhone.setText(mPatientPhones.get(i)); // obtains phone at index

        addPatientViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public Filter getFilter() {
        return addPatientFilter;
    }

    private Filter addPatientFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<String> filteredIds = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredIds.addAll(mPatientIdsCopy);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (String id : mPatientIdsCopy) {
                    if (id.contains(filterPattern)) {
                        filteredIds.add(id);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredIds;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mPatientIds.clear();
            mPatientIds.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
