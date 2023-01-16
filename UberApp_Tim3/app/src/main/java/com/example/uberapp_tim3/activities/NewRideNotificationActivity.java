package com.example.uberapp_tim3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.dialogs.LocationDialog;
import com.example.uberapp_tim3.dialogs.RejectionDialog;
import com.example.uberapp_tim3.fragments.MapFragment;
import com.example.uberapp_tim3.fragments.driver.DriverAccountFragment;
import com.example.uberapp_tim3.fragments.driver.DriverCurrentRideFragment;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.users.Driver;
import com.example.uberapp_tim3.tools.FragmentTransition;

public class NewRideNotificationActivity extends AppCompatActivity {


    public NewRideNotificationActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_driver);


        DriverRideDTO ride = (DriverRideDTO) getIntent().getParcelableExtra("ride");
        String start = ride.getLocations().get(0).getDeparture().getAddress();
        String end = ride.getLocations().get(ride.getLocations().size()-1).getDestination().getAddress();
        TextView startStation = (TextView) findViewById(R.id.txtStartStationPopup);
        startStation.setText(start);
        TextView endStation = (TextView) findViewById(R.id.txtEndStationPopup);
        endStation.setText(end);
        TextView passengersNum  = (TextView) findViewById(R.id.txtNumPassengersPopup);
        passengersNum.setText(String.valueOf(ride.getPassengers().size()));
        TextView price = (TextView) findViewById(R.id.txtPricePopup);
        price.setText(String.valueOf(ride.getTotalCost()));
        Button accept = (Button) findViewById(R.id.btnAcceptDrive);
        Button decline = (Button) findViewById(R.id.btnDeclineDrive);
        setListeners(accept, decline, ride);

    }


    private void setListeners(Button accept, Button decline, DriverRideDTO ride) {
        accept.setOnClickListener(view -> {
            Log.i("RRIDE", ride.toString());
            Bundle args = new Bundle();
            args.putParcelable("ride", ride);
            RelativeLayout newRidePopup = findViewById(R.id.newRidePopup);
            newRidePopup.setVisibility(View.INVISIBLE);
            DriverCurrentRideFragment currentRideFragment = new DriverCurrentRideFragment();
            currentRideFragment.setArguments(args);
            FragmentTransition.to(currentRideFragment, this, true);

        });

        decline.setOnClickListener(view -> {
            RejectionDialog dialog = new RejectionDialog(this, this);
            dialog.show();
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}