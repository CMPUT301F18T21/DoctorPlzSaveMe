package com.erikligai.doctorplzsaveme.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Activities.EditProblemActivity;
import com.erikligai.doctorplzsaveme.Activities.MainRecordActivity;
import com.erikligai.doctorplzsaveme.Activities.PatientSlideshowActivity;
import com.erikligai.doctorplzsaveme.Activities.PatientViewCommentActivity;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.MyViewHolder> implements Filterable {
    private final ArrayList<Problem> mDataset;
    private ArrayList<Problem> mProblemsCopy;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        final TextView title;
        final TextView date;
        final TextView desc;
        final TextView record_count;
        final ImageButton option;

        MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            title = v.findViewById(R.id.problem_item_title);
            date = v.findViewById(R.id.problem_item_date);
            desc = v.findViewById(R.id.problem_item_desc);
            record_count = v.findViewById(R.id.problem_item_record_count);
            option = v.findViewById(R.id.problem_item_options);
        }

        @Override
        public void onClick(View v) {
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(getAdapterPosition());
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProblemAdapter(ArrayList<Problem> myDataset) {
        this.mDataset = myDataset;
        this.mProblemsCopy = new ArrayList<>(mDataset);
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ProblemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_problem, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Problem problem = mDataset.get(position);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        Date date = problem.getDate();
        String f_date = df.format(date);
        String r_count = String.valueOf(problem.countRecords()) + " Records";

        holder.title.setText(problem.getTitle());
        holder.date.setText(f_date);
        holder.desc.setText(problem.getDescription());
        holder.record_count.setText(r_count);
        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(),view);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_problem_options);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.problem_menu1:
                                //handle menu1 click
                                ClickMenuOne();
                                return true;

                            case R.id.problem_menu2:
                                //handle menu2 click
                                ClickMenuTwo();
                                return true;

                            case R.id.problem_menu3:
                                //handle menu3 click
                                ClickMenuThree();
                                return true;
                            case R.id.problem_menu4:
                                //handle menu4 click
                                ClickMenuFour();
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
                Intent intent = new Intent(holder.itemView.getContext(), EditProblemActivity.class);
                intent.putExtra("Pos", holder.getAdapterPosition());
                holder.itemView.getContext().startActivity(intent);
            }

            void ClickMenuTwo(){
                Log.d("rview", "delete");
                Backend.getInstance().deletePatientProblem(holder.getAdapterPosition());
                notifyDataSetChanged();
            }

            void ClickMenuThree(){
                Intent intent = new Intent(holder.itemView.getContext(), PatientViewCommentActivity.class);
                intent.putExtra("Pos", holder.getAdapterPosition());
                Log.d("rview", "edit");
                holder.itemView.getContext().startActivity(intent);
            }

            void ClickMenuFour(){
                Intent intent = new Intent(holder.itemView.getContext(), PatientSlideshowActivity.class);
                intent.putExtra("Pos", holder.getAdapterPosition());
                Log.d("rview", "edit");
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

    @Override
    public Filter getFilter() {
        return patientProblemFilter;
    }

    private Filter patientProblemFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
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
            mDataset.clear();
            mDataset.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}

