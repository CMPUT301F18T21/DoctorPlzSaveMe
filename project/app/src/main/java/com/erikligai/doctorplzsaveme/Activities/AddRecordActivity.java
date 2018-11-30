package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erikligai.doctorplzsaveme.Models.BodyLocation;
import com.erikligai.doctorplzsaveme.Models.Geolocation;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.RecordFragments.AddRecordFragment2;

import java.util.Date;

public class AddRecordActivity extends AppCompatActivity {

    private Button backBtn1,nextBtn1,backBtn2,nextBtn2,backBtn3,saveBtn;
    private EditText titleText,commentText;
    private int problem_index;
    private String title;
    private String comment;
    private Date date;
    private BodyLocation bodyLocation;
    private Geolocation geolocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos",0);
        // Get buttons

        nextBtn1 = findViewById(R.id.nextButton1);
        // Set title & comment editText
        commentText = findViewById(R.id.editRecordComment);
        titleText = findViewById(R.id.editRecordTitle);



        nextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = titleText.getText().toString();
                comment = commentText.getText().toString();
                date = new Date();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AddRecordFragment2 fragment = new AddRecordFragment2();
                fragmentTransaction.replace(R.id.FragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
