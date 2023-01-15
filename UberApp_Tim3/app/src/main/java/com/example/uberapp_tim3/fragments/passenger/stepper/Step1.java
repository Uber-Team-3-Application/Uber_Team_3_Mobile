package com.example.uberapp_tim3.fragments.passenger.stepper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.drives.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ernestoyaquello.com.verticalstepperform.Step;

public class Step1 extends Step<String> {


    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private EditText departure;
    private EditText destination;
    private Location departureLocation;
    private Location destinationLocation;


    @Override
    protected View createStepContentLayout() {
        // Here we generate the view that will be used by the library as the content of the step.
        // In this case we do it programmatically, but we could also do it by inflating an XML layout.
        View step1Content = LayoutInflater.from(getContext()).inflate(R.layout.stepper_step1, null, false);
        departure = step1Content.findViewById(R.id.departure_input);
        destination = step1Content.findViewById(R.id.destination_input);
        departure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Whenever the user updates the user name text, we update the state of the step.
                // The step will be marked as completed only if its data is valid, which will be
                // checked automatically by the form with a call to isStepDataValid().
                markAsCompletedOrUncompleted(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        destination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Whenever the user updates the user name text, we update the state of the step.
                // The step will be marked as completed only if its data is valid, which will be
                // checked automatically by the form with a call to isStepDataValid().
                markAsCompletedOrUncompleted(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return step1Content;
    }

    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        departureLocation.setLongitude(10.00);
        departureLocation.setLatitude(10.00);
        departureLocation.setAddress(departure.getText().toString());

        destinationLocation.setLongitude(10.00);
        destinationLocation.setLatitude(10.00);
        destinationLocation.setAddress(destination.getText().toString());

        // googleMap.addMarker(new MarkerOptions().position(departure));
        // googleMap.addMarker(new MarkerOptions().position(destination));
        // googleMap.moveCamera(CameraUpdateFactory.newLatLng(departure));
    }

    @Override
    protected void onStepOpened(boolean animated) {
        // This will be called automatically whenever the step gets opened.
    }

    @Override
    protected void onStepClosed(boolean animated) {
        // This will be called automatically whenever the step gets closed.
    }

    @Override
    protected void onStepMarkedAsCompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as completed.
    }

    @Override
    protected void onStepMarkedAsUncompleted(boolean animated) {
        // This will be called automatically whenever the step is marked as uncompleted.
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }
    public Location getDestinationLocation() {
        return destinationLocation;
    }
    public Step1(String stepTitle) {
        super(stepTitle);
    }

    @Override
    public String getStepData() {
        return null;
    }

    @Override
    public String getStepDataAsHumanReadableString() {
        return null;
    }

    @Override
    protected void restoreStepData(String data) {

    }

    @Override
    protected IsDataValid isStepDataValid(String stepData) {
        return null;
    }

}
