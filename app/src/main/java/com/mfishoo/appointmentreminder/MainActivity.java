package com.mfishoo.appointmentreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public List<Appointment> appointmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateSomeTestAppointmentsToStartWith();
    }

    private void CreateSomeTestAppointmentsToStartWith() {
        appointmentList.add(new Appointment.AppointmentBuilder().name("Doctors Visit").type("Health").monthDate("Jan").dayDate(1).yearDate(2020).hourTime(8).minuteTime(30).AMorPMTime("am").build());
        appointmentList.add(new Appointment.AppointmentBuilder().name("Hair Cut Appointment").type("Personal").monthDate("Feb").dayDate(2).yearDate(2020).hourTime(9).minuteTime(30).AMorPMTime("pm").build());
        appointmentList.add(new Appointment.AppointmentBuilder().name("Meeting with Account").type("Personal").monthDate("March").dayDate(3).yearDate(2020).hourTime(8).minuteTime(30).AMorPMTime("am").build());
        appointmentList.add(new Appointment.AppointmentBuilder().name("Boss Meeting").type("Work").monthDate("April").dayDate(4).yearDate(2020).hourTime(8).minuteTime(30).AMorPMTime("am").build());
        appointmentList.add(new Appointment.AppointmentBuilder().name("Teacher Conference").type("School").monthDate("May").dayDate(1).yearDate(2020).hourTime(8).minuteTime(30).AMorPMTime("am").build());
        appointmentList.add(new Appointment.AppointmentBuilder().name("Dinner with Rose").type("Other").monthDate("June").dayDate(1).yearDate(2020).hourTime(8).minuteTime(30).AMorPMTime("pm").build());

        for(int i = 0; i < appointmentList.size();i++){
            populateTable(i);
        }
    }

    private void populateTable(int i){
        TableLayout appointmentTBL = (TableLayout) findViewById(R.id.tblTaskContent);
        TableRow newTableRow = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        newTableRow.setLayoutParams(layoutParams);

        TextView txtvName = new TextView(this);
        txtvName.setLayoutParams(layoutParams);
        txtvName.setGravity(Gravity.CENTER);
        txtvName.setText(appointmentList.get(i).getName());
        txtvName.setWidth(140);
        txtvName.setTextSize(14);
        txtvName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvType = new TextView(this);
        txtvType.setLayoutParams(layoutParams);
        txtvType.setGravity(Gravity.CENTER);
        txtvType.setText(appointmentList.get(i).getType());
        txtvType.setWidth(93);
        txtvType.setTextSize(14);
        txtvType.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvDate = new TextView(this);
        txtvDate.setLayoutParams(layoutParams);
        txtvDate.setGravity(Gravity.CENTER);
        txtvDate.setText(setToDateAndTime(appointmentList.get(i)));
        txtvDate.setWidth(97);
        txtvDate.setTextSize(14);
        txtvDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        newTableRow.addView(txtvName);
        newTableRow.addView(txtvType);
        newTableRow.addView(txtvDate);
        appointmentTBL.addView(newTableRow, i + 1);
    }

    private String setToDateAndTime(Appointment appointment) {
        long currentDateAndTime = System.currentTimeMillis();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM d, yyyy");

        String todaysDate =  formatDate.format(currentDateAndTime);
        String passDate = appointment.getMonthDate() + " " + appointment.getDayDate() + ", " + appointment.getYearDate();

        // only show time if it's cur day
        if(Objects.equals(todaysDate, passDate)){
            return appointment.getHourTime() +":" + appointment.getMinuteTime() + " " + appointment.getAMorPMTime();
        }
        return appointment.getMonthDate() + " " + appointment.getDayDate() + " " + appointment.getYearDate();
    }

    @Override
    // Returns info passed from addAppointmentActicity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                // Create new Appointment obj
                appointmentList.add(new Appointment.AppointmentBuilder()
                        .name(data.getStringExtra("name"))
                        .type(data.getStringExtra("type"))
                        .monthDate(data.getStringExtra("monthOfYear"))
                        .dayDate(data.getIntExtra("dayOfMonth", 0))
                        .yearDate(data.getIntExtra("year", 1111))
                        .hourTime(data.getIntExtra("hour",11))
                        .minuteTime(data.getIntExtra("minute", 11))
                        .AMorPMTime(data.getStringExtra("AMorPM"))
                        .build());

                populateTable(appointmentList.size() - 1);
            }

        }
    }


    public void addAppointmentBtn(View view) {
        startActivityForResult(new Intent(this, AddAppointmentActivity.class), 1);
    }
}