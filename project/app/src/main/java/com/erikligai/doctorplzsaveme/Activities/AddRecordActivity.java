package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener{

    private Button backBtn1,nextBtn1;
    private EditText titleText,commentText;
    private int problem_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_one);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("Pos", 0);

        // Get buttons
        backBtn1 = findViewById(R.id.backButton1);
        nextBtn1 = findViewById(R.id.nextButton1);
        backBtn1.setOnClickListener(this);
        nextBtn1.setOnClickListener(this);

        // Set title & comment editText
        titleText = findViewById(R.id.editRecordTitle);
        commentText = findViewById(R.id.editRecordComment);
        String title = RecordBuffer.getInstance().getRecord().getTitle();
        String comment = RecordBuffer.getInstance().getRecord().getComment();
        // Set buttons OnClickListener
        if (title == "") {
            titleText.setHint(getString(R.string.record_title_hint));
        } else {
            titleText.setText(title);
        }
        if (comment == ""){
            commentText.setHint(getString(R.string.record_comment_hint));
        } else {
            commentText.setText(comment);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton1:
                RecordBuffer.getInstance().ClearBuffer();
                finish();
                break;

            case R.id.nextButton1:
                openAddRecordTwoActivity();
                break;
        }
    }

    private void openAddRecordTwoActivity(){
        RecordBuffer.getInstance().getRecord().setTitle(titleText.getText().toString());
        RecordBuffer.getInstance().getRecord().setComment(commentText.getText().toString());
        Intent intent = new Intent(this, AddRecordTwoActivity.class);
        intent.putExtra("Pos", problem_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        RecordBuffer.getInstance().ClearBuffer();
        finish();
    }
}
