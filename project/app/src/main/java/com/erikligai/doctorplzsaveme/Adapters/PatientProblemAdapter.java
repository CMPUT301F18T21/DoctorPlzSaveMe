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
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.Activities.CPViewProblemActivity;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class PatientProblemAdapter extends RecyclerView.Adapter<PatientProblemAdapter.PatientProblemViewHolder> implements Filterable {
//    private ArrayList<Patient> mPatients;
//    private ArrayList<Patient> mPatientsCopy;
    private Context mContext;

    private ArrayList<Problem> mProblems;
    private ArrayList<Problem> mProblemsCopy;
    private String patientID;

    public static class PatientProblemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;


//        TextView patientID;
//        TextView patientEmail;
//        TextView patientPhone;
        ConstraintLayout parentLayout;
        public PatientProblemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            description = itemView.findViewById(R.id.problem_description);
            date = itemView.findViewById(R.id.problem_date);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    public PatientProblemAdapter(ArrayList<Problem> problems, Context mContext, String patientID) {
        this.mProblems = problems;
        this.mContext = mContext;
        this.patientID = patientID;
        this.mProblemsCopy = new ArrayList<>(mProblems);
    }


    @NonNull
    @Override
    public PatientProblemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_problem_item, viewGroup, false);
        return new PatientProblemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientProblemViewHolder patientProblemViewHolder, final int i) {
        patientProblemViewHolder.title.setText(mProblems.get(i).getTitle()); // obtains id at index
        patientProblemViewHolder.description.setText(mProblems.get(i).getDescription()); // obtains email at index

        // format date into string
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
        String date = formatter.format(mProblems.get(i).getDate());

        patientProblemViewHolder.date.setText(date); // obtains phone at index

        patientProblemViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here the name in the patient list is clicked
                // this should start a new activity with the patient's records in a list

                // print patientID and problem index
//                Log.e("patientID", patientID);
//                Log.e("problemIndex", i +"");

                Intent intent = new Intent(mContext, CPViewProblemActivity.class);
                intent.putExtra("patientID", patientID); // attach patient id to intent
                intent.putExtra("problemID", i+"");
                mContext.startActivity(intent); // go to record list of patient

//                Log.d(TAG, "onClick: clicked on: " + mProblems.get(i).getTitle());
                Toast.makeText(mContext, mProblems.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProblems.size();
    }

    @Override
    public Filter getFilter() {
        return patientProblemFilter;
    }


    private Filter patientProblemFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
//            ArrayList<Patient> filteredPatients = new ArrayList<>();
            ArrayList<Problem> filteredProblems = new ArrayList<>();

            Log.d(TAG, "search: " + constraint);

            if (constraint == null || constraint.length() == 0) {
                filteredProblems.addAll(mProblemsCopy);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                Log.d(TAG, "filterPattern:  " + filterPattern);


                for (Problem problem : mProblemsCopy) {
                    Log.d(TAG, "problemTitle: " + problem.getTitle().toLowerCase());
                    if (problem.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredProblems.add(problem);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredProblems;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mProblems.clear();
            mProblems.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}