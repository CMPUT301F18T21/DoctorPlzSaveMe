package com.erikligai.doctorplzsaveme.SearchTabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Activities.AddRecordActivity;
import com.erikligai.doctorplzsaveme.R;

import java.util.Date;

public class AddRecordFragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setContentView(R.layout.activity_problem_search);
//        nextBtn1 = findViewById(R.id.editNextButton3);
//        // Set title & comment editText
//        commentText = findViewById(R.id.editRecordComment);
//        titleText = findViewById(R.id.editRecordTitle);
//
//
//
//        nextBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                title = titleText.getText().toString();
//                comment = commentText.getText().toString();
//                date = new Date();
//                AddRecordActivity activity = (AddRecordActivity) getActivity();
//                activity.setComment(comment);
//                activity.setTitle(title);
//                activity.setDate(date);
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager
//                        .beginTransaction();
//
//                AddRecordFragment1 fragment = new AddRecordFragment1();
//                fragmentTransaction.replace(R.id.Fragment1, fragment3);
//            }
//        });
        return inflater.inflate(R.layout.fragment_add_record_one, container, false);

    }
}
