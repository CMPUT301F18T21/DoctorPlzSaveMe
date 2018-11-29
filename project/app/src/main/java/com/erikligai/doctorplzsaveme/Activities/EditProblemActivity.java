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
    private EditText editTitle,editDesc,editDate;
    private Calendar date;
    private ViewSwitcher switcher;
    private String title, description;
    private Date uf_date;
    private int position, flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);

        switcher = findViewById(R.id.title_switcher);
        titleText = switcher.findViewById(R.id.problem_title);
        editTitle = switcher.findViewById(R.id.edit_problem_title);
        descText = findViewById(R.id.problem_desc);
        dateText = findViewById(R.id.problem_date);


        Button editDate = findViewById(R.id.editDateButton);
        Button editProblem = findViewById(R.id.editProblemButton);
        Button editDesc = findViewById(R.id.editDescButton);

        editDate.setOnClickListener(this);
        editProblem.setOnClickListener(this);
        editDesc.setOnClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.view_edit_problem);

        position = getIntent().getIntExtra("Pos", 0);
        problem = Backend.getInstance().getPatientProblems().get(position);
        title = problem.getTitle();
        description = problem.getDescription();
        uf_date = problem.getDate();
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
                editProblem2();
                break;

            case R.id.editDescButton:
                editDesc();
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
                        problem.setDate(date.getTime());
                        displayProblem();
                        displayToaster(getString(R.string.toaster_date));
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

//    /** Called when the user taps the Edit Problems button */
//    public void editProblem() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        //LayoutInflater inflater = this.getLayoutInflater();
//        final View dialogView = View.inflate(this, R.layout.dialog_edit_problem, null);
//        builder.setView(dialogView);
//
//        final EditText edit = dialogView.findViewById(R.id.edit_title_text);
//
//        builder.setTitle(R.string.edit_problem)
//                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        //do something
//                        String text = edit.getText().toString();
//                        try {
//                            problem.setTitle(text);
//                            displayProblem();
//                            displayToaster(getString(R.string.toaster_title));
//                        } catch (TooLongProblemTitleException e) {
//                            e.printStackTrace();
//                            displayToaster(e.getMessage());
//                        }
//                    }
//                })
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // do nothing
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }

    /** Called when the user taps the Edit Description button */
    public void editDesc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = View.inflate(this, R.layout.dialog_edit_desc, null);
        builder.setView(dialogView);

        final EditText edit = dialogView.findViewById(R.id.edit_desc_text);

        builder.setTitle(R.string.edit_desc)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //do something
                        String text = edit.getText().toString();
                        try {
                            problem.setDesc(text);
                            displayProblem();
                            displayToaster(getString(R.string.toaster_desc));
                        } catch (TooLongProblemDescException e) {
                            e.printStackTrace();
                            displayToaster(e.getMessage());
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do nothing
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void displayProblem(){
        titleText.setText(title);

        if (description.equals("")){
            descText.setText(R.string.no_desc);
        } else{
            descText.setText(description);
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        String f_date = df.format(uf_date);
        dateText.setText(f_date);
    }

    private void displayToaster(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void editProblem2(){
        if (flag == 0) {
            editTitle.setText(title);
            titleText.setText(title);
            switcher.showNext();
            flag += 1;
        } else {
            title = editTitle.getText().toString();
            titleText.setText(title);
            editTitle.setText(title);
            switcher.showNext();
            flag -= 1;
        }
    }
}
