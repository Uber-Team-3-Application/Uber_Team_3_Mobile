package com.example.uberapp_tim3.fragments.passenger.stepper;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.uberapp_tim3.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step4 extends Step<String> {

    private CheckBox babyTransport;
    private CheckBox petTransport;
    private TextView dateTimeTextView;
    private Calendar selectedDateTime;

    public Step4(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Inflate the layout for this step
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View step4Content = inflater.inflate(R.layout.stepper_step4, null, false);

        // Initialize the checkboxes and the date time picker
        babyTransport = step4Content.findViewById(R.id.checkbox1);
        petTransport = step4Content.findViewById(R.id.checkbox2);
        dateTimeTextView = step4Content.findViewById(R.id.date_time_text_view);
        dateTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });

        return step4Content;
    }

    @Override
    protected void onStepOpened(boolean animated) {

    }

    @Override
    protected void onStepClosed(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {

    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {

    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime = Calendar.getInstance();
                        selectedDateTime.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
                        dateTimeTextView.setText(getFormattedDateTime(selectedDateTime));
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }

    private String getFormattedDateTime(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    @Override
    public String getStepData() {
        return babyTransport.isChecked() + "," + petTransport.isChecked() + "," + selectedDateTime.getTimeInMillis();
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return null;
    }

    @Override
    public void restoreStepData(String stepData) {
        String[] data = stepData.split(",");
        babyTransport.setChecked(Boolean.parseBoolean(data[0]));
        petTransport.setChecked(Boolean.parseBoolean(data[1]));
        selectedDateTime = Calendar.getInstance();
        selectedDateTime.setTimeInMillis(Long.parseLong(data[2]));
        dateTimeTextView.setText(getFormattedDateTime(selectedDateTime));
    }

    @Override
    public IsDataValid isStepDataValid(String stepData) {
        return new IsDataValid(true);
    }


}
