package com.erikligai.doctorplzsaveme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Comment;
import com.erikligai.doctorplzsaveme.R;

import java.util.ArrayList;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.ViewHolder> {

    private static final String TAG = "commentAdapter";

    private ArrayList<Comment> mComments;
    private Context mContext;

    public commentAdapter(ArrayList<Comment> comments, Context mContext) {

        this.mComments = comments;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public commentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item, viewGroup, false);
        return new commentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull commentAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.commentText.setText(mComments.get(i).getComment()); // obtains id at index
        viewHolder.commentDate.setText(mComments.get(i).getDate()); // obtains email at index

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView commentText;
        TextView commentDate;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentText = itemView.findViewById(R.id.comment_text);
            commentDate = itemView.findViewById(R.id.comment_date);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
