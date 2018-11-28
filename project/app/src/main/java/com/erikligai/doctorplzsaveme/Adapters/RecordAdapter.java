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
import android.widget.ImageButton;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Activities.EditRecordActivity;
import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private final ArrayList<Record> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        final TextView title;
        final TextView date;
        final TextView comment;
        final ImageButton option;

        MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            title = v.findViewById(R.id.record_item_title);
            date = v.findViewById(R.id.record_item_date);
            comment = v.findViewById(R.id.record_item_comment);
            option = v.findViewById(R.id.record_item_options);
        }

        @Override
        public void onClick(View v) {
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(getAdapterPosition());
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecordAdapter(ArrayList<Record> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecordAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Record record = mDataset.get(position);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        Date date = record.getDate();
        String f_date = df.format(date);

        holder.title.setText(record.getTitle());
        holder.date.setText(f_date);
        holder.comment.setText(record.getComment());
        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_record_options);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.record_menu1:
                                //handle menu1 click
                                ClickMenuOne();
                                return true;

                            case R.id.record_menu2:
                                //handle menu2 click
                                ClickMenuTwo();
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }

            void ClickMenuOne() {
                Log.d("rview", "view/edit");
                Log.d("rview", Integer.toString(holder.getAdapterPosition()));
                Intent intent = new Intent(holder.itemView.getContext(), EditRecordActivity.class);
                intent.putExtra("Pos", holder.getAdapterPosition());
                Log.d("rview", "edit");
                holder.itemView.getContext().startActivity(intent);
            }

            void ClickMenuTwo() {
                Log.d("rview", "delete");
                Log.d("rview", Integer.toString(holder.getAdapterPosition()));
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