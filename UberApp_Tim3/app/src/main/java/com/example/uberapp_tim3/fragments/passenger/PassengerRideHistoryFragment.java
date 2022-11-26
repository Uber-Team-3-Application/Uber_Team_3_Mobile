package com.example.uberapp_tim3.fragments.passenger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uberapp_tim3.R;


public class PassengerRideHistoryFragment extends Fragment {

    public PassengerRideHistoryFragment() {
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
        return inflater.inflate(R.layout.fragment_passenger_ride_history, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().setTitle(R.string.drive_history);
    }


}