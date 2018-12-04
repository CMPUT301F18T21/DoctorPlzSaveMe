package com.erikligai.doctorplzsaveme.Adapters;

import android.content.Context;
import android.content.Intent;
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

import com.erikligai.doctorplzsaveme.Activities.CPViewRecordActivity;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;

public class CPRecordKeywordAdapter extends RecyclerView.Adapter<CPRecordKeywordAdapter.CPRecordKeywordViewHolder> implements Filterable {
    private Context mContext;

    private ArrayList<Record> mRecords;
    private ArrayList<Record> mRecordsCopy;

    private String patientID;
    private String problemID;

    public static class CPRecordKeywordViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView comment;
        ConstraintLayout parentLayout;

        public CPRecordKeywordViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.record_title);
            date = itemView.findViewById(R.id.record_date);
            comment = itemView.findViewById(R.id.record_comment);
            parentLayout = itemView.findViewById(R.id.record_parent_layout);
        }
    }

    public CPRecordKeywordAdapter(ArrayList<Record> records, Context mContext, String patientID, String problemID) {
        this.mRecords = records;
        this.mContext = mContext;
        this.mRecordsCopy = new ArrayList<>(mRecords);
        this.patientID = patientID;
        this.problemID = problemID;
    }

    @NonNull
    @Override
    public CPRecordKeywordAdapter.CPRecordKeywordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_record_item, viewGroup, false);
        return new CPRecordKeywordAdapter.CPRecordKeywordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CPRecordKeywordAdapter.CPRecordKeywordViewHolder patientRecordViewHolder, final int i) {
        patientRecordViewHolder.title.setText(mRecords.get(i).getTitle()); // obtains id at index

        // format date into string
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy",Locale.CANADA); // format date
        String date = formatter.format(mRecords.get(i).getDate());

        patientRecordViewHolder.date.setText(date); // obtains date at index
        patientRecordViewHolder.comment.setText(mRecords.get(i).getComment()); // obtains comment at index

        patientRecordViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here the record in the record list is clicked
                // this should start a new activity with the record view
                Intent intent = new Intent(mContext, CPViewRecordActivity.class);
                intent.putExtra("patientID", patientID); // attach patient id to intent
                intent.putExtra("problemIndex", Integer.parseInt(problemID)); // attach problem index to intent
                intent.putExtra("recordIndex", Integer.parseInt(i + "")); // attack record index to intent
                mContext.startActivity(intent); // go to record list of patient
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    @Override
    public Filter getFilter() {
        return patientRecordFilter;
    }


    private Filter patientRecordFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Record> filteredRecords = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredRecords.addAll(mRecordsCopy);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();


                for (Record record : mRecordsCopy) {
                    Log.d(TAG, "problemTitle: " + record.getTitle().toLowerCase());
                    if (record.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredRecords.add(record);
                    }
                }



            }
            FilterResults results = new FilterResults();
            results.values = filteredRecords;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mRecords.clear();
            mRecords.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
