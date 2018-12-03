package com.erikligai.doctorplzsaveme.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erikligai.doctorplzsaveme.Models.RecordBuffer;
import com.erikligai.doctorplzsaveme.R;

public class EditRecordActivity extends AppCompatActivity {

    private Button backBtn,nextBtn;
    private EditText titleText,commentText;
    private int problem_index, record_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_one);

        Intent intent = getIntent();
        problem_index = intent.getIntExtra("P_Pos", 0);
        record_index = intent.getIntExtra("R_Pos", 0);

        // Get buttons
        nextBtn = findViewById(R.id.nextButton1);
        backBtn = findViewById(R.id.backButton1);
        // Set comment editText
        titleText = findViewById(R.id.editRecordTitle);
        commentText = findViewById(R.id.editRecordComment);

        RecordBuffer.getInstance().setExistingRecord(problem_index, record_index);
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

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditRecordTwoActivity();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordBuffer.getInstance().ClearBuffer();
                finish();
            }
        });
    }

    private void openEditRecordTwoActivity(){
        RecordBuffer.getInstance().getRecord().setTitle(titleText.getText().toString());
        RecordBuffer.getInstance().getRecord().setComment(commentText.getText().toString());
        Intent intent = new Intent(this, EditRecordTwoActivity.class);
        intent.putExtra("P_Pos", problem_index);
        intent.putExtra("R_Pos", record_index);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        RecordBuffer.getInstance().ClearBuffer();
        finish();
    }
}
