package com.erikligai.doctorplzsaveme.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.erikligai.doctorplzsaveme.ElasticsearchProblemController;
import com.erikligai.doctorplzsaveme.Models.Problem;
import com.erikligai.doctorplzsaveme.R;
import com.erikligai.doctorplzsaveme.TooLongProblemDescException;
import com.erikligai.doctorplzsaveme.TooLongProblemTitleException;
import com.erikligai.doctorplzsaveme.backend.Backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddProblemActivity extends AppCompatActivity implements View.OnClickListener{

    private Button nowDateBtn,cusDateBtn,backBtn,saveBtn;
    private TextView dateText;
    private Calendar date;
    private EditText titleText,descriptionText;
    private Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_problem);

        // Get buttons
        nowDateBtn = findViewById(R.id.nowDateButton);
        cusDateBtn = findViewById(R.id.customDateButton);
        backBtn = findViewById(R.id.backButton);
        saveBtn = findViewById(R.id.saveButton);

        // Buttons setOnClickListener
        nowDateBtn.setOnClickListener(this);
        cusDateBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        // Set title, comment editText & date TextView
        descriptionText = findViewById(R.id.editProblemDescription);
        titleText = findViewById(R.id.editProblemTitle);
        dateText = findViewById(R.id.date);

        // Display now date
        date = Calendar.getInstance(Locale.CANADA);
        displayDate();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nowDateButton:
                date = Calendar.getInstance(Locale.CANADA);
                displayDate();
                break;
            case R.id.customDateButton:
                setCustomDate();
                break;
            case R.id.backButton:
                finish();
                break;
            case R.id.saveButton:
                String title = titleText.getText().toString();
                String desc = descriptionText.getText().toString();
                addProblem(title, desc, date.getTime());
                break;
        }
    }


    private void openMainProblemActivity(){
        Intent intent = new Intent(this,MainProblemActivity.class);
        startActivity(intent);
    }
    
    protected void addProblem(String title, String desc, Date date){
        try {
            problem = new Problem(title, desc, date);
            Backend.getInstance().addPatientProblem(problem);
            finish();
        } catch (TooLongProblemTitleException | TooLongProblemDescException e) {
            e.printStackTrace();
            displayException(e.getMessage());
        }
    }

    private void displayDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        Date uf_date = date.getTime();
        String f_date = df.format(uf_date);
        dateText.setText(f_date);
    }

    private void setCustomDate(){
        final Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date.getTime());
        date = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(AddProblemActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        Log.v("abc", "The chosen one " + date.getTime());
                        displayDate();
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    private void displayException(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
