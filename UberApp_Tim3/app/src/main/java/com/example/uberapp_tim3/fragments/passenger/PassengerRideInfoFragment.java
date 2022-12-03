package com.example.uberapp_tim3.fragments.passenger;

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
import com.example.uberapp_tim3.fragments.ChatFragment;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.model.mockup.Driver;
import com.example.uberapp_tim3.tools.DrivesMockUp;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.util.List;


public class PassengerRideInfoFragment extends Fragment {


    public PassengerRideInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_ride_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SetViews();
        setOnClickListeners();
        fillOtherPassengers();
    }

    private void SetViews() {
        RatingBar passengerRideInfoDriverRating = getActivity().findViewById(R.id.passengerRideInfoDriverRating);
        passengerRideInfoDriverRating.setNumStars(4);

        RatingBar passengerRideInfoCarRating = getActivity().findViewById(R.id.passengerRideInfoCarRating);
        passengerRideInfoCarRating.setNumStars(3);

        EditText txtComment = getActivity().findViewById(R.id.passRideInfoComment);
        txtComment.setText("Voznja je bila ok brt, al auto malo los");
    }

    private void setOnClickListeners(){
        ImageView imgInbox = getActivity().findViewById(R.id.imgInbox);

        ImageView imgDriver = getActivity().findViewById(R.id.imgDriver);

        imgInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerInboxFragment()).addToBackStack(null).commit();

            }
        });
        imgDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerInfoProfile()).addToBackStack(null).commit();

            }
        });
    }

    private void fillOtherPassengers(){
        LinearLayout ly = getActivity().findViewById(R.id.lyOtherPassengers);
        LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        List<Drive> drives = DrivesMockUp.getDrives();
        for(int i =1;i<drives.get(0).getPassengerList().size();i++){
            View passenger = inflater.inflate(R.layout.other_passenger_item, (ViewGroup) getView(), false);
            TextView txtPassengerFullName = passenger.findViewById(R.id.txtPassengerFullName);
            txtPassengerFullName.setText(drives.get(0).getPassengerList().get(i).getName() + " " + drives.get(0).getPassengerList().get(i).getLastName());
            ly.addView(passenger);
        }

    }

}