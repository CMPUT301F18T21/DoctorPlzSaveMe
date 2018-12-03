package com.erikligai.doctorplzsaveme.RecordFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BodyLocationFragment extends Fragment {

    public BodyLocationFragment(){}

    public static BodyLocationFragment newInstance(int problem_index, int record_index) {
        BodyLocationFragment fragment = new BodyLocationFragment();
        Bundle args = new Bundle();
        args.putInt("problem_index", problem_index);
        args.putInt("record_index",record_index);
        fragment.setArguments(args);
        return fragment;
    }
}
