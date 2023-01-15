package com.example.uberapp_tim3.fragments.passenger.stepper;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.uberapp_tim3.R;

import ernestoyaquello.com.verticalstepperform.Step;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class PassengerOrderARide extends Fragment implements StepperFormListener {

    private Step1 step1;
    private Step2 step2;
    private Step3 step3;

    private VerticalStepperFormView verticalStepperForm;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.passenger_order_a_ride, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step1 = new Step1("Departure and destination");
        step2 = new Step2("Vehicle type");
        step3 = new Step3("Passengers");

        verticalStepperForm = getView().findViewById(R.id.stepper_form);
        verticalStepperForm
                .setup(this, step1, step2, step3)
                .init();
    }

    @Override
    public void onCompletedForm() {
        // This method will be called when the user clicks on the last confirmation button of the
        // form in an attempt to save or send the data.
    }

    @Override
    public void onCancelledForm() {
        // This method will be called when the user clicks on the cancel button of the form.
    }

    @Override
    public void onStepAdded(int index, Step<?> addedStep) {
        // This will be called when a step is added dynamically through the form method addStep().
    }

    @Override
    public void onStepRemoved(int index) {
        // This will be called when a step is removed dynamically through the form method removeStep().
    }

}
