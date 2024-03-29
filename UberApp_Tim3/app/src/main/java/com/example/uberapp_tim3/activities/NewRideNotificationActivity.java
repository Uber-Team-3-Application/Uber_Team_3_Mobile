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
import com.example.uberapp_tim3.fragments.driver.DriverUntilRideFragment;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.users.Driver;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.example.uberapp_tim3.tools.RideSocketConfiguration;
import com.example.uberapp_tim3.tools.SimulationSocketConfiguration;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewRideNotificationActivity extends AppCompatActivity {
    public static SimulationSocketConfiguration simulationSocketConfiguration;
    public static RideSocketConfiguration rideSocketConfiguration;

    public NewRideNotificationActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_driver);


        RideDTO ride = (RideDTO) getIntent().getParcelableExtra("ride");
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
        simulationSocketConfiguration = new SimulationSocketConfiguration();
        simulationSocketConfiguration.connect();
        rideSocketConfiguration = new RideSocketConfiguration();
        rideSocketConfiguration.connect();
    }


    private void setListeners(Button accept, Button decline, RideDTO ride) {

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<RideDTO> call = ServiceUtils.rideService.acceptRide(ride.getId());
                call.enqueue(new Callback<RideDTO>() {
                    @Override
                    public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        assert response.body() != null;
                        Bundle args = new Bundle();
                        args.putParcelable("ride", response.body());
                        RelativeLayout newRidePopup = findViewById(R.id.newRidePopup);
                        newRidePopup.setVisibility(View.INVISIBLE);
                        DriverUntilRideFragment untilRideFragment = new DriverUntilRideFragment();
                        untilRideFragment.setArguments(args);
                        FragmentTransition.to(untilRideFragment, NewRideNotificationActivity.this, true);


                    }

                    @Override
                    public void onFailure(Call<RideDTO> call, Throwable t) {

                    }
                });
                 }

        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RejectionDialog dialog = new RejectionDialog(getApplicationContext(),
                        NewRideNotificationActivity.this, ride);
                dialog.show();
            }
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