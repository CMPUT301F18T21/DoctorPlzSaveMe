package com.erikligai.doctorplzsaveme;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditProblemActivity extends AppCompatActivity implements View.OnClickListener{
    private Problem problem;
    private TextView titleText;
    private TextView descText;
    private TextView dateText;
    private Calendar date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        problem = new Problem("Problem 1","Problem Description 1fsfdfsdsfgsgdsggdgsdgdgdxvxvfdsfffd",new Date());
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

        displayProblem();
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
                editDate();
                break;

            case R.id.editProblemButton:
                //call editProblemActivity
                editProblem(findViewById(R.id.content));
                break;
        }
    }

    /** Called when the user taps the Edit Date button */
    public void editDate() {
        final Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(problem.getDate());
        date = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(EditProblemActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        Log.v("abc", "The chosen one " + date.getTime());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    /** Called when the user taps the View Problems button */
    public void editProblem(View view) {

    }

    public void displayProblem(){
        titleText.setText(problem.getTitle());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        Date uf_date = problem.getDate();
        String f_date = df.format(uf_date);

        dateText.setText(f_date);
        String desc = problem.getDescription();
        if (desc.equals("")){
            descText.setText(R.string.no_desc);
        } else{
            descText.setText(desc);
        }
    }
}
