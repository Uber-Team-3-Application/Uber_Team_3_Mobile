package com.example.uberapp_tim3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.uberapp_tim3.model.NavItem;
import com.example.uberapp_tim3.model.Passenger;
import com.example.uberapp_tim3.tools.PassengerMockup;

import java.util.ArrayList;

public class PassengerAccountActivity extends AppCompatActivity {

    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_account);

        setTextViews();

    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        /* Postavljamo ikonicu unutar toolbar-a*/
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }

    }

    private void setTextViews(){
        Passenger passenger = PassengerMockup.getPassengers().get(0);

        TextView txtFullName = findViewById(R.id.txtPassengerFullName);
        txtFullName.setText(String.format("%s %s", passenger.getName(), passenger.getLastName()));

        TextView txtPhoneNumber = findViewById(R.id.txtPassengerPhoneNumber);
        txtPhoneNumber.setText(passenger.getPhoneNumber());

        TextView txtEmailAddress = findViewById(R.id.txtPassengerEmail);
        txtEmailAddress.setText(passenger.getEmailAddress());


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}