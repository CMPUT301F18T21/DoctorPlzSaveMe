package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.StartAppActivities.NoProfileActivity;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.util.Date;

public class CPAddCommentActivity  extends AppCompatActivity {

    private Button backBtn4,saveBtn1;
    private TextView patientProblem;
    private EditText commentText;
    private int ProblemPosition;
    private String patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_add_comment);

        // Get buttons
        backBtn4 = findViewById(R.id.backButton4);
        saveBtn1 = findViewById(R.id.saveButton1);
        // Set comment editText
        commentText = findViewById(R.id.editComment);

        Intent intent = getIntent();
        ProblemPosition = intent.getIntExtra("ProblemPos",-1);
        patientID = intent.getStringExtra("patientId");

        backBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentText.getText().toString();
                Backend backend = Backend.getInstance();
                if (backend.addComment(patientID,ProblemPosition,comment))
                {
                    finish();
                } else
                {
                    Toast.makeText(CPAddCommentActivity.this, "Could not add comment", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
