package com.example.uberapp_tim3.fragments.passenger.stepper;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.uberapp_tim3.R;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step2 extends Step<String> {

    private Spinner choicesSpinner;
    private String selectedChoice;

    public Step2(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Inflate the layout for this step
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View step2Content = inflater.inflate(R.layout.stepper_step2, null, false);

        // Initialize the Spinner
        choicesSpinner = step2Content.findViewById(R.id.choices_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.vehicle_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choicesSpinner.setAdapter(adapter);
        choicesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedChoice = parent.getItemAtPosition(position).toString();
                markAsCompletedOrUncompleted(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing to do here
            }
        });

        return step2Content;
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

    @Override
    public String getStepData() {
        return selectedChoice;
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return null;
    }

    @Override
    public void restoreStepData(String stepData) {
        selectedChoice = stepData;
        int position = ((ArrayAdapter) choicesSpinner.getAdapter()).getPosition(stepData);
        choicesSpinner.setSelection(position);
    }

    @Override
    public IsDataValid isStepDataValid(String stepData) {
        return new IsDataValid(true);
    }
}
