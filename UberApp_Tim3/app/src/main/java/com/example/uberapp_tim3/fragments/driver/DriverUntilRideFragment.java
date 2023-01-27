package com.example.uberapp_tim3.fragments.driver;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.fragments.MapFragment;
import com.example.uberapp_tim3.model.DTO.LocationDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.VehicleDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverUntilRideFragment extends Fragment {

    RideDTO rideDTO = null;
    private TextView tvHours, tvMinutes, tvSeconds;
    private int elapsedTime = 0;
    private SharedPreferences preferences;
    private boolean isDriverArrived = false;

    public DriverUntilRideFragment() {
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null)
            rideDTO = bundle.getParcelable("ride");
        preferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        setViews(rideDTO);
        initializeTime();
        setListeners();
    }

    private void setListeners() {
        setStartRideListener();
    }

    private void setStartRideListener() {
        Button startRide = requireActivity().findViewById(R.id.btnStartRideDriver);
        startRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ako je stigao zapocni voznju
                if (isDriverArrived) {
                    DriverCurrentRideFragment currentRideFragment = new DriverCurrentRideFragment();
                    Bundle args = new Bundle();
                    args.putParcelable("ride", rideDTO);
                    currentRideFragment.setArguments(args);
                    FragmentTransition.to(currentRideFragment, requireActivity(), false);
                }
                else {
                    Toast.makeText(getContext(), "You are not arrived to destination!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initializeTime() {
    }

    private void setViews(RideDTO rideDTO) {

        Call<VehicleDTO> call = ServiceUtils.driverService.getVehicle(rideDTO.getDriver().getId());
        call.enqueue(new Callback<VehicleDTO>() {
            @Override
            public void onResponse(@NonNull Call<VehicleDTO> call, @NonNull Response<VehicleDTO> response) {
                assert response.body() != null;
                LocationDTO currentLocation = response.body().getCurrentLocation();
                LocationDTO departure = rideDTO.getLocations().get(0).getDeparture();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.untilRideContainerDriver, new DrawRouteFragment(currentLocation, departure)
                ).commit();
            }

            @Override
            public void onFailure(@NonNull Call<VehicleDTO> call, @NonNull Throwable t) {

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_driver_until_ride, container, false);
    }
}