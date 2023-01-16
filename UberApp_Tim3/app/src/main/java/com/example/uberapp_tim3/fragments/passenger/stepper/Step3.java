package com.example.uberapp_tim3.fragments.passenger.stepper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;

import com.example.uberapp_tim3.R;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step3 extends Step<String> {

    private MultiAutoCompleteTextView emailAddressesInput;
    private String emailAddresses;

    public Step3(String stepTitle) {
        super(stepTitle);
    }

    @Override
    protected View createStepContentLayout() {
        // Inflate the layout for this step
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View step3Content = inflater.inflate(R.layout.stepper_step3, null, false);

        // Initialize the MultiAutoCompleteTextView
        emailAddressesInput = step3Content.findViewById(R.id.email_addresses_input);
        emailAddressesInput.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        emailAddressesInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailAddresses = s.toString();
                markAsCompletedOrUncompleted(isValidEmailAddresses());
            }

            @Override
            public void afterTextChanged(Editable s) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        });

        return step3Content;
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

    private boolean isValidEmailAddresses() {
        if(emailAddresses != null && emailAddresses.contains(",")) {
            String[] emailAddressesArray = emailAddresses.split(",");
            for (String email : emailAddressesArray) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getStepData() {
        if(emailAddresses != null) return emailAddresses;

        return "";
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return null;
    }

    @Override
    public void restoreStepData(String stepData) {
        emailAddresses = stepData;
        emailAddressesInput.setText(stepData);
    }

    @Override
    public IsDataValid isStepDataValid(String stepData) {
        boolean isValid = isValidEmailAddresses();
        String errorMessage = !isValid ? "Please enter valid email addresses separated by commas" : "";
        return new IsDataValid(isValid, errorMessage);
    }
}
