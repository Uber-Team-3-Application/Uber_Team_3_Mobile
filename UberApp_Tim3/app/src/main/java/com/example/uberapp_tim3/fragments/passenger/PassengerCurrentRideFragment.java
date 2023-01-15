package com.example.uberapp_tim3.fragments.passenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.fragments.driver.DriverInfoProfile;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.RideUserDTO;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PassengerCurrentRideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PassengerCurrentRideFragment extends Fragment {



    public PassengerCurrentRideFragment() {
        // Required empty public constructor
    }

    public static PassengerCurrentRideFragment newInstance(String param1, String param2) {
        PassengerCurrentRideFragment fragment = new PassengerCurrentRideFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        DriverRideDTO rideDTO = null;
        if (bundle != null)
            rideDTO = bundle.getParcelable("ride");
        setViews(rideDTO);
    }

    private void setViews(DriverRideDTO rideDTO) {
        assert rideDTO != null;
        requireActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.currentRideContainerDriver, new DrawRouteFragment(rideDTO)
        ).commit();

        TextView tvStartTime = requireActivity().findViewById(R.id.txtPassengerCurrentRideStartTime);
        TextView tvEndTIme = requireActivity().findViewById(R.id.txtPassengerCurrentRideEndTime);
        TextView tvDeparture = requireActivity().findViewById(R.id.txtPassengerCurrentRideDeparture);
        TextView tvDestination = requireActivity().findViewById(R.id.txtPassengerCurrentRideDestination);
        TextView tvPassengers = requireActivity().findViewById(R.id.txtPassengerCurrentRidePassengerCount);
        TextView tvPrice = requireActivity().findViewById(R.id.txtPassengerCurrentRidePrice);
        

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvStartTime.setText(sdf.format(rideDTO.getStartTime()));
        tvEndTIme.setText(sdf.format(rideDTO.getEndTime()));
        tvDeparture.setText(rideDTO.getLocations().get(0).getDeparture().getAddress());
        tvDestination.setText(rideDTO.getLocations().get(rideDTO.getLocations().size() - 1).getDestination().getAddress());
        String totalPassengers = Integer.toString(rideDTO.getPassengers().size());
        tvPassengers.setText(totalPassengers);
        String totalCost = Double.toString(rideDTO.getTotalCost());
        tvPrice.setText(totalCost);
        setDriver(rideDTO.getDriver());
    }
    
    private void setDriver(RideUserDTO driver) {
        LinearLayout linearLayout = (LinearLayout) requireView().findViewById(R.id.lyRideDriver);
        LayoutInflater inflater = (LayoutInflater)requireView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View driverView = inflater.inflate(R.layout.driver_item, (ViewGroup) getView(), false);
        TextView twEmail = driverView.findViewById(R.id.txtRideDriverEmail);
        twEmail.setText(driver.getEmail());
        linearLayout.addView(driverView);

        driverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putLong("driverId", driver.getId());

                DriverInfoProfile profile = new DriverInfoProfile();
                profile.setArguments(args);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profile).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_current_ride, container, false);
    }
}