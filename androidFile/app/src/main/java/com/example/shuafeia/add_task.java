package com.example.shuafeia;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class add_task extends AppCompatActivity implements View.OnClickListener {

    private Button showdialog;
    private Button time;
    private TextView day;
    private TextView day_time;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Calendar calendar;
    private Switch reminder;
    private Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        showdialog = findViewById(R.id.showdailog);
        time = findViewById(R.id.time);
        day = findViewById(R.id.day);
        day_time = findViewById(R.id.day_time);
        reminder = findViewById(R.id.addTask_content_switch);
        type = findViewById(R.id.spinner);

        time.setOnClickListener(this);
        showdialog.setOnClickListener(this);
        calendar = Calendar.getInstance();

        reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });

        ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                this, R.array.addTask_type_array, android.R.layout.simple_spinner_item );
        nAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        if (type != null) {
            type.setAdapter(nAdapter);
        }
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    findViewById(R.id.limited).setVisibility( View.VISIBLE );
                    setTitle( "VISIBLE" );
                } else {
                    findViewById(R.id.limited).setVisibility( View.INVISIBLE );
                    setTitle( "INVISIBLE" );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showdailog:
                showDialog();
                break;
            case R.id.time:
                showTime();
                break;
        }
    }

    public void closeTab(View view) {

    }

    private void showDialog() {
        datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //String time = String.valueOf(year) + "/" + String.valueOf(monthOfYear + 1) + "/" + Integer.toString(dayOfMonth);
                String time = String.valueOf(monthOfYear + 1) + "/" + Integer.toString(dayOfMonth);
                day.setText(time);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void showTime() {
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d("test", Integer.toString(hourOfDay));
                Log.d("test", Integer.toString(minute));
                String hourAM = Integer.toString(hourOfDay);
                String hourPM = Integer.toString(hourOfDay - 12);
                String min = Integer.toString(minute);
                day_time.setText((hourOfDay > 12 ? hourPM : hourAM) + ":" + (minute < 10 ? "0" + min : min) + " " + (hourOfDay > 12 ? "PM" : "AM"));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
        timePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
