package com.erikligai.doctorplzsaveme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProblemActivity extends AppCompatActivity {

    private Button backBtn,saveBtn;
    private EditText titleText,descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);

        // Get buttons
        backBtn = findViewById(R.id.backButton);
        saveBtn = findViewById(R.id.saveButton);
        // Set title & comment editText
        descriptionText = findViewById(R.id.editProblemDescription);
        titleText = findViewById(R.id.editProblemTitle);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString();
                String description = descriptionText.getText().toString();
                addProblem(title, description);
                finish();
            }
        });
    }

    private void openMainProblemActivity(){
        Intent intent = new Intent(this,MainProblemActivity.class);
        startActivity(intent);
    }

    protected void addProblem(String title, String description){
        Problem newProblem = new Problem(title,description);
        ElasticsearchProblemController.AddProblemsTask addTweetsTask = new ElasticsearchProblemController.AddProblemsTask();
        addTweetsTask.execute(newProblem);
    }
}
