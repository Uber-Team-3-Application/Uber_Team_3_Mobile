package com.example.uberapp_tim3.fragments.passenger;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.CommentsListAdapter;
import com.example.uberapp_tim3.adapters.DrawerListAdapter;
import com.example.uberapp_tim3.adapters.PassengerRideHistoryAdapter;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.tools.DrivesMockUp;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.util.List;


public class PassengerRideHistoryFragment extends ListFragment {

    public PassengerRideHistoryFragment() {
        // Required empty public constructor
    }

    public static PassengerRideHistoryFragment newInstance() {

        PassengerRideHistoryFragment mpf;
        mpf = new PassengerRideHistoryFragment();
        return mpf;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Drive> drives = DrivesMockUp.getDrives();
        PassengerRideHistoryAdapter adapter = new PassengerRideHistoryAdapter(getActivity(), drives);
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_ride_history, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.drive_history);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().setTitle(R.string.drive_history);
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        PassengerRideInfoFragment rideInfo = new PassengerRideInfoFragment();
        FragmentTransition.to(rideInfo, requireActivity(), true);

    }


}