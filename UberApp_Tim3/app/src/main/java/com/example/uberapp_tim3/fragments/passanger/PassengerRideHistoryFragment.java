package com.example.uberapp_tim3.fragments.passanger;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.DrawerListAdapter;
import com.example.uberapp_tim3.fragments.DriveItemDetail;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.tools.DrivesMockUp;
import com.example.uberapp_tim3.tools.FragmentTransition;


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