package com.erikligai.doctorplzsaveme.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.erikligai.doctorplzsaveme.R;

import java.util.Date;

public class CPAddCommentActivity  extends AppCompatActivity {

    private Button backBtn4,saveBtn1;
    private TextView patientProblem;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_add_comment);

        // Get buttons
        backBtn4 = findViewById(R.id.backButton4);
        saveBtn1 = findViewById(R.id.saveButton1);
        // Set comment editText
        commentText = findViewById(R.id.editComment);
        patientProblem = findViewById(R.id.CurrentProblemView);

        backBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        saveBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentText.getText().toString();
                Date date = new Date();
            }
        });
    }
}
