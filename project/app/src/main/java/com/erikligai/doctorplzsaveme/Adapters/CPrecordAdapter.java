package com.erikligai.doctorplzsaveme.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CPrecordAdapter extends RecyclerView.Adapter<CPrecordAdapter.MyViewHolder> {

    private final ArrayList<Record> prRecords;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        final TextView title;
        final TextView date;
        final TextView comment;

        MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            title = v.findViewById(R.id.record_item_title);
            date = v.findViewById(R.id.record_item_date);
            comment = v.findViewById(R.id.record_item_comment);
        }

        @Override
        public void onClick(View v) {
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(getAdapterPosition());
            }
        }
    }

    public CPrecordAdapter(ArrayList<Record> myDataset) {
        prRecords = myDataset;
    }
    @NonNull
    @Override
    public CPrecordAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);
        return new CPrecordAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Record record = prRecords.get(position);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        Date date = record.getDate();
        String f_date = df.format(date);

        holder.title.setText(record.getTitle());
        holder.date.setText(f_date);
        holder.comment.setText(record.getComment());


    }

    @Override
    public int getItemCount() {
        return prRecords.size();
    }

    private RecordAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(int position);
    }

    public void setOnEntryClickListener(RecordAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}
