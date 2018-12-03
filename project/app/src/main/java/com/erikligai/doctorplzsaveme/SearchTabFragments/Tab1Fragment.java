package com.erikligai.doctorplzsaveme.SearchTabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Adapters.RecyclerViewAdapter;
import com.erikligai.doctorplzsaveme.Models.Patient;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.ArrayList;

public class Tab1Fragment extends Fragment {

//    ArrayList<Record> recordList = new ArrayList<>();
    ArrayList<Patient> patientList = new ArrayList<>();
//    PatientRecordAdapter adapter;
    RecyclerViewAdapter adapter;
    Backend backend = Backend.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search_one, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.patient_problems_recycler_view_search);
        TextView emptyView = (TextView) rootView.findViewById(R.id.empty_view);

//        Record record = new Record("title", "comment");
        Patient patient = new Patient("user2", ";lahjrg", "893482");
        patientList.add(patient);

//        if (recordList.isEmpty()) {
//            // hide recyclerview
//            recyclerView.setVisibility(View.GONE);
//            // display textview
//            emptyView.setVisibility(View.VISIBLE);
//        } else {
        // display recyclerview
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(patientList, getActivity());
        recyclerView.setAdapter(adapter);

        // hide textview
//        emptyView.setVisibility(View.GONE);
//        }
        return inflater.inflate(R.layout.fragment_search_one, container, false);
    }



}