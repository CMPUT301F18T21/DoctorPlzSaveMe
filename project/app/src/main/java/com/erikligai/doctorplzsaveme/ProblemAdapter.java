package com.erikligai.doctorplzsaveme;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.MyViewHolder> {
    private final ArrayList<Problem> mDataset;

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
                mOnEntryClickListener.onEntryClick(getLayoutPosition());
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProblemAdapter(ArrayList<Problem> myDataset) {
        mDataset = myDataset;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Problem problem = mDataset.get(position);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        Date date = problem.getDate();
        String f_date = df.format(date);

        holder.title.setText(problem.getTitle());
        holder.date.setText(f_date);
        holder.desc.setText(problem.getDescription());
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

                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }

            public void ClickMenuOne(){
                Intent intent = new Intent(holder.itemView.getContext(), EditProblemActivity.class);
                intent.putExtra("Pos", position);
                Log.d("rview", "view/add");
                holder.itemView.getContext().startActivity(intent);
            }

            public void ClickMenuTwo(){
                Intent intent = new Intent(holder.itemView.getContext(), EditProblemActivity.class);
                intent.putExtra("Pos", position);
                Log.d("rview", "edit");
                holder.itemView.getContext().startActivity(intent);
            }

            public void ClickMenuThree(){
                Intent intent = new Intent(holder.itemView.getContext(), EditProblemActivity.class);
                intent.putExtra("Pos", position);
                Log.d("rview", "delete");
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
}

