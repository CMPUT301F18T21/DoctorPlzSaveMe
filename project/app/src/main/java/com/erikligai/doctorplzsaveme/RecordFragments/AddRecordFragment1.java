package com.erikligai.doctorplzsaveme.RecordFragments;

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
        return inflater.inflate(R.layout.fragment_add_record_one, container, false);

    }
}
