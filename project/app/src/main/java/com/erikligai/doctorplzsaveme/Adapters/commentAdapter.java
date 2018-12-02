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

/**
 * Comment adapter recycerlview for display cp's comments
 */
public class commentAdapter extends RecyclerView.Adapter<commentAdapter.ViewHolder> {

    private static final String TAG = "commentAdapter";

    private ArrayList<Comment> mComments;
    private Context mContext;

    /**
     * constructor for the adapter
     * @param comments : ArrayList<Comment>
     * @param mContext : Context
     */
    public commentAdapter(ArrayList<Comment> comments, Context mContext) {

        this.mComments = comments;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    /**
     * inflate the view when it is created
     */
    public commentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item, viewGroup, false);
        return new commentAdapter.ViewHolder(view);
    }

    @Override
    /**
     * set the comment and date texts
     */
    public void onBindViewHolder(@NonNull commentAdapter.ViewHolder viewHolder, final int i) {
//        Log.d(TAG, "onBindViewHolder called");
        viewHolder.commentText.setText(mComments.get(i).getComment()); // obtains id at index
        viewHolder.commentDate.setText(mComments.get(i).getDate()); // obtains email at index

    }

    @Override
    /**
     * return number of comments
     */
    public int getItemCount() {
        return mComments.size();
    }

    /**
     * view holder class for the adapter
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView commentText;
        TextView commentDate;
        ConstraintLayout parentLayout;

        /**
         * constructor for the viewholder that sets the views
         * @param itemView : View
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentText = itemView.findViewById(R.id.comment_text);
            commentDate = itemView.findViewById(R.id.comment_date);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
