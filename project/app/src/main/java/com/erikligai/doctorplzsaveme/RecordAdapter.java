package com.erikligai.doctorplzsaveme;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordAdapter{
    private ArrayList<Record> records;
    private OnEntryClickListener mOnEntryClickListener;

    public class RecordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView date;
        public TextView comment;
        public ImageButton option;

        RecordViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            title = v.findViewById(R.id.record_item_title);
            date = v.findViewById(R.id.record_item_date);
            comment = v.findViewById(R.id.record_item_comment);
            option = v.findViewById(R.id.problem_item_options);
        }
        @Override
        public void onClick(View v){
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(getAdapterPosition());
            }

        }
    }


}
