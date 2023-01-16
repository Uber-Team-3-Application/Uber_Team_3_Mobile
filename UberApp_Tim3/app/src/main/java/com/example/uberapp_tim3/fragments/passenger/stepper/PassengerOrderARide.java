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
import com.example.uberapp_tim3.fragments.passenger.PassengerReportFragment;

import ernestoyaquello.com.verticalstepperform.Step;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView;
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener;

public class PassengerOrderARide extends Fragment implements StepperFormListener {

    private Step1 step1;
    private Step2 step2;
    private Step3 step3;
    private Step4 step4;

    private VerticalStepperFormView verticalStepperForm;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        step1 = new Step1("Departure and destination");
        step2 = new Step2("Vehicle type");
        step3 = new Step3("Passengers");
        step4 = new Step4("Additional Info");
        verticalStepperForm = getView().findViewById(R.id.stepper_form);
        verticalStepperForm
                .setup(this, step1, step2, step3, step4)
                .init();

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.passenger_order_a_ride, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onCompletedForm() {
        // This method will be called when the user clicks on the last confirmation button of the
        // form in an attempt to save or send the data.
        Bundle bundle = new Bundle();
        bundle.putString("departure", step1.getDeparture());
        bundle.putString("destination", step1.getDestination());
        bundle.putString("dateTme", step4.getDateTime());
        bundle.putString("passengers", step3.getStepData());
        bundle.putBoolean("babyTransport", step4.isBabyTransport());
        bundle.putBoolean("petTransport", step4.isPetTransport());
        bundle.putString("vehicleType", step2.getStepData());
        ConfirmRideFragment fragment = new ConfirmRideFragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

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
