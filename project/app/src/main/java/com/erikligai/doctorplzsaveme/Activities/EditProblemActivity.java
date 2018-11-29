package com.erikligai.doctorplzsaveme.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewSwitcher;

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

public class EditProblemActivity extends AppCompatActivity implements View.OnClickListener{
    private Problem problem;
    private TextView titleText,descText,dateText;
    private EditText editTitle,editDesc;
    private ViewSwitcher title_switcher, desc_switcher;
    private String title, desc;
    private Date uf_date;
    private Calendar date;
    private int position, t_flag, d_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);

        title_switcher = findViewById(R.id.title_switcher);
        titleText = title_switcher.findViewById(R.id.problem_title);
        editTitle = title_switcher.findViewById(R.id.edit_problem_title);

        desc_switcher = findViewById(R.id.desc_switcher);
        descText = desc_switcher.findViewById(R.id.problem_desc);
        editDesc = desc_switcher.findViewById(R.id.edit_problem_desc);
        dateText = findViewById(R.id.problem_date);

        Button editDate = findViewById(R.id.editDateButton);
        Button editProblem = findViewById(R.id.editProblemButton);
        Button editDesc = findViewById(R.id.editDescButton);
        Button saveBtn = findViewById(R.id.saveButton);

        editDate.setOnClickListener(this);
        editProblem.setOnClickListener(this);
        editDesc.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.view_edit_problem);

        position = getIntent().getIntExtra("Pos", 0);
        problem = Backend.getInstance().getPatientProblems().get(position);
        title = problem.getTitle();
        desc = problem.getDescription();
        uf_date = problem.getDate();
        displayProblem();

        t_flag = 0;
        d_flag = 0;
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
                editProblem();
                break;

            case R.id.editDescButton:
                editDesc();
                break;

            case R.id.saveButton:
                saveProblem();
                break;
        }
    }


    /** Called when the user taps the Edit Problem button */
    private void editProblem(){
        if (t_flag == 0) {
            editTitle.setText(title);
            titleText.setText(title);
            title_switcher.showNext();
            t_flag += 1;
        } else {
            title = editTitle.getText().toString();
            titleText.setText(title);
            editTitle.setText(title);
            title_switcher.showNext();
            t_flag -= 1;
        }
    }

    /** Called when the user taps the Edit Description button */
    private void editDesc(){
        if (d_flag == 0) {
            editDesc.setText(desc);
            descText.setText(desc);
            desc_switcher.showNext();
            d_flag += 1;
        } else {
            desc = editDesc.getText().toString();
            descText.setText(desc);
            editDesc.setText(desc);
            desc_switcher.showNext();
            d_flag -= 1;
        }
    }

    /** Called when the user taps the Save button */
    private void saveProblem(){
        try {
            problem.setTitle(title);
            problem.setDesc(desc);
            problem.setDate(uf_date);
            Backend.getInstance().UpdatePatient();
            finish();
        } catch (TooLongProblemTitleException | TooLongProblemDescException e) {
            e.printStackTrace();
            displayToaster(e.getMessage());
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
                        uf_date = date.getTime();
                        displayProblem();
                        displayToaster(getString(R.string.toaster_date));
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }


    public void displayProblem(){
        titleText.setText(title);

        if (desc.equals("")){
            descText.setText(R.string.no_desc);
        } else{
            descText.setText(desc);
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        String f_date = getString(R.string.date_started) + df.format(uf_date);
        dateText.setText(f_date);
    }

    private void displayToaster(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
