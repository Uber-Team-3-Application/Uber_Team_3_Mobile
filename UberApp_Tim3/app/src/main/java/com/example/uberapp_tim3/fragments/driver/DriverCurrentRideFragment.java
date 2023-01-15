package com.example.uberapp_tim3.fragments.driver;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;

import java.text.SimpleDateFormat;

public class DriverCurrentRideFragment extends Fragment {


    public DriverCurrentRideFragment() {
        // Required empty public constructor
    }

    public static DriverCurrentRideFragment newInstance(String param1, String param2) {
        DriverCurrentRideFragment fragment = new DriverCurrentRideFragment();
        return fragment;
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

        TextView tvStartTime = getActivity().findViewById(R.id.txtDriverCurrentRideStartTime);
        TextView tvEndTIme = getActivity().findViewById(R.id.txtDriverCurrentRideEndTime);
        TextView tvDeparture = getActivity().findViewById(R.id.txtDriverCurrentRideDeparture);
        TextView tvDestination = getActivity().findViewById(R.id.txtDriverCurrentRideDestination);
        TextView tvPassengers = getActivity().findViewById(R.id.txtDriverCurrentRidePassengerCount);
        TextView tvPrice = getActivity().findViewById(R.id.txtDriverCurrentRidePrice);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvStartTime.setText(sdf.format(rideDTO.getStartTime()));
        tvEndTIme.setText(sdf.format(rideDTO.getEndTime()));
        tvDeparture.setText(rideDTO.getLocations().get(0).getDeparture().getAddress());
        tvDestination.setText(rideDTO.getLocations().get(rideDTO.getLocations().size() - 1).getDestination().getAddress());
        String totalPassengers = Integer.toString(rideDTO.getPassengers().size());
        tvPassengers.setText(totalPassengers);
        String totalCost = Double.toString(rideDTO.getTotalCost());
        tvPrice.setText(totalCost);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_current_ride, container, false);
    }
}