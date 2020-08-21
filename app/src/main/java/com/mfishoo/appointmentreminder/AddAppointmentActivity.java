package com.mfishoo.appointmentreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity {

    TextView txtDate;
    TextView txtTime;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    static final int DATE_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID = 998;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        setCurrentDateAndTimeOnView();
    }

    private void setCurrentDateAndTimeOnView() {
        txtDate = (TextView) findViewById(R.id.txttvDate);
        txtTime = (TextView) findViewById(R.id.txttvTime);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MARCH);
        day = c.get(Calendar.DAY_OF_MONTH);

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        txtTime.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
        txtDate.setText(new StringBuilder().append(month + 1).append("-").append(day).append('-').append(year).append(" "));
    }


    // opens a Date Picker Dialog when clicks the date
    public void edittxtDate(View view) {
        showDialog(DATE_DIALOG_ID);
    }

    // opens a Time Picker Dialog when clicks the time
    public void edittxtTime(View view) {
        showDialog(TIME_DIALOG_ID);
    }

    // Displays a new dialog for date picker or time picker
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year, month, day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, hour, minute, false);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDayOfMonth;

            txtDate.setText((new StringBuilder().append(month + 1).append("-").append(day).append("-").append(year).append(" ")));
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;

            // set current time into textview
            txtTime.setText(new StringBuilder().append(pad(hour))
                    .append(":").append(pad(minute)));
        }
    };


    // Closes addAppointmentActivity
    public void cancelAppointmentBtn(View view) {
        finish();
    }

    // Closes addAppintmentActivity and returns the info entered in each field
    public void addAppointmentDetailBtn(View view) {
        EditText editAppointmentName = (EditText) findViewById(R.id.editTextName);
        Spinner spinnerAppointmentType = (Spinner) findViewById(R.id.spnTaskType);

        System.out.println(editAppointmentName.getText().toString());

        if(editAppointmentName.getText().toString().length() > 0){
            Intent intent = new Intent();
            intent.putExtra("name", editAppointmentName.getText().toString());
            intent.putExtra("type", spinnerAppointmentType.getSelectedItem().toString());
            intent.putExtra("monthOfYear", displayTheMonthInCharacters(month));
            intent.putExtra("dayOfMonth", day);
            intent.putExtra("year", year);
            intent.putExtra("hour", formatTheHour(hour));
            intent.putExtra("minute", minute);
            intent.putExtra("AMorPM", aMorPM(hour));

            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast toast = Toast.makeText(AddAppointmentActivity.this, "Please enter an Appointment name", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    // helper methods
    // Returns the Month Abbreviation for the corresponding number
    private String displayTheMonthInCharacters(int passedMonth) {
        String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return month[passedMonth];
    }

    // Converts the 24 hours PassedHour to a 12 Hour time
    private int formatTheHour(int passedHour) {
        return passedHour > 12 ? passedHour - 12 : passedHour;
    }

    // Returns AM or PM depending on the hour(1-24)
    private String aMorPM(int passedHour) {
        return passedHour > 12 ? "PM" : "AM";
    }

    private String pad(int c) {
        return c >= 10 ? String.valueOf(c) : "0" + String.valueOf(c);
    }
}