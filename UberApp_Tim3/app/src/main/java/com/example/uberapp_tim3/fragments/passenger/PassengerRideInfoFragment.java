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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.tools.DrivesMockUp;

import java.util.List;
import java.util.Objects;


public class PassengerRideInfoFragment extends Fragment {


    public PassengerRideInfoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passenger_ride_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        DriverRideDTO rideDTO = null;
        if (bundle != null)
            rideDTO = bundle.getParcelable("ride");
        SetViews(rideDTO);
        setOnClickListeners();
        fillOtherPassengers();
    }

    @SuppressLint("SetTextI18n")
    private void SetViews(DriverRideDTO ride) {
        RatingBar passengerRideInfoDriverRating = requireActivity().findViewById(R.id.passengerRideInfoDriverRating);
        passengerRideInfoDriverRating.setNumStars(4);

        RatingBar passengerRideInfoCarRating = requireActivity().findViewById(R.id.passengerRideInfoCarRating);
        passengerRideInfoCarRating.setNumStars(3);

        EditText txtComment = requireActivity().findViewById(R.id.passRideInfoComment);
        txtComment.setText("Voznja je bila ok brt, al auto malo los");

        assert ride != null;
        requireActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.map_route_fragment_container,new DrawRouteFragment(ride)
        ).commit();

    }


    private void setOnClickListeners(){
        ImageView imgInbox = requireActivity().findViewById(R.id.imgInbox);

        ImageView imgDriver = requireActivity().findViewById(R.id.imgDriver);

        imgInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerInboxFragment()).addToBackStack(null).commit();

            }
        });
        imgDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerInfoProfile()).addToBackStack(null).commit();

            }
        });
    }

    private void fillOtherPassengers(){
        LinearLayout ly = requireActivity().findViewById(R.id.lyOtherPassengers);
        LayoutInflater inflater = (LayoutInflater) requireView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        List<Drive> drives = DrivesMockUp.getDrives();
        for(int i =1;i<drives.get(0).getPassengerList().size();i++){
            View passenger = inflater.inflate(R.layout.other_passenger_item, (ViewGroup) getView(), false);
            TextView txtPassengerFullName = passenger.findViewById(R.id.txtPassengerFullName);
            txtPassengerFullName.setText(drives.get(0).getPassengerList().get(i).getName() + " " + drives.get(0).getPassengerList().get(i).getLastName());
            ly.addView(passenger);
        }

    }

}