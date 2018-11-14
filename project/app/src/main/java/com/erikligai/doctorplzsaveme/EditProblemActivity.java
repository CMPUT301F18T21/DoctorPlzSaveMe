package com.erikligai.doctorplzsaveme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditProblemActivity extends AppCompatActivity implements View.OnClickListener{
    private Problem problem;
    private TextView titleText;
    private TextView descText;
    private TextView dateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);

        titleText = findViewById(R.id.problem_title);
        descText = findViewById(R.id.problem_desc);
        dateText = findViewById(R.id.problem_date);
        Button editDate = findViewById(R.id.editDateButton);
        Button editProblem = findViewById(R.id.editProblemButton);

        editDate.setOnClickListener(this);
        editProblem.setOnClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.view_edit_problem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //back button
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editDateButton:
                //call editDateActivity
                editDate(findViewById(R.id.content));
                break;

            case R.id.editProblemButton:
                //call editProblemActivity
                editProblem(findViewById(R.id.content));
                break;
        }
    }

    /** Called when the user taps the Edit Date button */
    public void editDate(View view) {
        Intent intent = new Intent(this, EditProblemActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the View Problems button */
    public void editProblem(View view) {
        Intent intent = new Intent(this, EditProblemActivity.class);
        startActivity(intent);
    }
}
