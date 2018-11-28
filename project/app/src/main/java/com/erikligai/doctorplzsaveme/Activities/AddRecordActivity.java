package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erikligai.doctorplzsaveme.R;

import java.util.Date;

public class AddRecordActivity extends AppCompatActivity {

    private Button backBtn1,nextBtn1;
    private EditText titleText,commentText;
    private int problem_index;
    private String title;
    private String comment;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",0);
        // Get buttons
        backBtn1 = findViewById(R.id.backButton1);
        nextBtn1 = findViewById(R.id.editNextButton3);
        // Set title & comment editText
        commentText = findViewById(R.id.editRecordComment);
        titleText = findViewById(R.id.editRecordTitle);

        backBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordListActivity();

            }
        });

        nextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = titleText.getText().toString();
                comment = commentText.getText().toString();
                date = new Date();
                openAddBodyLocationActivity();
            }
        });
    }
    
    private void openAddBodyLocationActivity(){
        Intent intent = new Intent(this,AddBodylocationActivity.class);
        intent.putExtra("date",date);
        intent.putExtra("title",title);
        intent.putExtra("comment",comment);
        intent.putExtra("Pos", problem_index);
        startActivity(intent);
    }

    private void openRecordListActivity(){
        Intent intent = new Intent(this,MainRecordActivity.class);
        startActivity(intent);
    }

}
