package com.example.shuafeia;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.Calendar;

public class add_task extends AppCompatActivity implements View.OnClickListener {

    private EditText date_edittext;
    private EditText time_edittext;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private EditText Limit_edittext,task_edittext;
    private Calendar calendar;
    private Switch reminder;
    private Spinner type;

    private final String LOG_TAG = add_task.class.getSimpleName();
    private String mDate,mTime,mType,mTask,mLimit;
    private boolean mReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        task_edittext = findViewById(R.id.editText_task);
        date_edittext = findViewById(R.id.Date_edittext);
        time_edittext = findViewById(R.id.Time_edittext);
        reminder = findViewById(R.id.addTask_content_switch);
        type = findViewById(R.id.type_spinner);
        Limit_edittext = findViewById(R.id.editText_Limit);

        time_edittext.setOnClickListener(this);
        date_edittext.setOnClickListener(this);
        calendar = Calendar.getInstance();

        reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mReminder=true;
                }else{
                    mReminder=false;
                }
                Log.d(LOG_TAG,"checker:"+mReminder);
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
                    mType = "Challenge";
                    setTitle( "VISIBLE" );
                } else {
                    findViewById(R.id.limited).setVisibility( View.INVISIBLE );
                    Limit_edittext.setText("");
                    setTitle( "INVISIBLE" );
                    mType = "Task";
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
            case R.id.Date_edittext:
                showDialog();
                break;
            case R.id.Time_edittext:
                showTime();
                break;
        }
    }

    private void showDialog() {
        datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(year) + "/" + String.valueOf(monthOfYear + 1) + "/" + Integer.toString(dayOfMonth);
                mDate = date;
                //String date = String.valueOf(monthOfYear + 1) + "/" + Integer.toString(dayOfMonth);
                date_edittext.setText(date);
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
                String time = Integer.toString(hourOfDay)+":"+Integer.toString(minute);
                mTime = time;
                time_edittext.setText(time);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
        timePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void closeTab(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void PostEvent(View view) {
        Intent intent = new Intent();
        mTask = task_edittext.getText().toString();
        mLimit = Limit_edittext.getText().toString();
        intent.putExtra("Task",mTask);
        intent.putExtra("Date",mDate);
        intent.putExtra("Time",mTime);
        intent.putExtra("Remind",mReminder);
        intent.putExtra("Type",mType);
        intent.putExtra("Limit",mLimit);
        setResult(RESULT_OK,intent);
        finish();
    }
}
