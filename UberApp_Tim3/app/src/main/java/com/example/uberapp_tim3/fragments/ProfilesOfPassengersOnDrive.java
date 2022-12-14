package com.example.uberapp_tim3.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.PassengersListAdapter;
import com.example.uberapp_tim3.fragments.placeholder.PlaceholderContent;
import com.example.uberapp_tim3.model.Drive;
import com.example.uberapp_tim3.model.Passenger;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.example.uberapp_tim3.tools.PassengerMockup;

/**
 * A fragment representing a list of Items.&laquo;
 */
public class ProfilesOfPassengersOnDrive extends ListFragment {
    public ProfilesOfPassengersOnDrive() {}


    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle  = this.getArguments();

        assert bundle != null;
        Drive drive = bundle.getParcelable("driveInfo");
        PassengersListAdapter profiles = new PassengersListAdapter(getActivity(), drive.getPassengerList());
        setListAdapter(profiles);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Passengers");
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        PassengerInfoProfile profile = new PassengerInfoProfile();
        FragmentTransition.to(profile, requireActivity(), true);

    }
}