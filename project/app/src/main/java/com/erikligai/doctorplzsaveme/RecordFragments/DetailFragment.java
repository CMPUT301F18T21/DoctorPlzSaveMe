package com.erikligai.doctorplzsaveme.RecordFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.Models.Record;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailFragment extends Fragment {
    private TextView title, comment, date;

    public DetailFragment(){}

    public static DetailFragment newInstance(int problem_index, int record_index) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt("problem_index", problem_index);
        args.putInt("record_index",record_index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_record_one, container, false);

        title = view.findViewById(R.id.title);
        comment = view.findViewById(R.id.comment);
        date = view.findViewById(R.id.date);

        int problem_index = getArguments().getInt("problem_index");
        int record_index = getArguments().getInt("record_index");
        Record record = Backend.getInstance().getPatientRecords(problem_index).get(record_index);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        Date uf_date = record.getDate();
        String f_date = getString(R.string.timestamp) +": "+ df.format(uf_date);
        String title_text = record.getTitle();
        String comment_text = record.getComment();

        if (title_text.equals("")){
            title_text = "<" + getString(R.string.no_title) + ">";
        }
        if (comment_text.equals("")){
            comment_text = "<" + getString(R.string.no_comment) + ">";
        }
        title.setText(title_text);
        comment.setText(comment_text);
        date.setText(f_date);

        return view;
    }
}
