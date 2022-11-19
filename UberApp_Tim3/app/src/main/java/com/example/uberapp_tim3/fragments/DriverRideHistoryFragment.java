package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.DrawerListAdapter;
import com.example.uberapp_tim3.model.drives.Drive;
import com.example.uberapp_tim3.tools.FragmentTransition;

/**
 * A fragment representing a list of Items.
 */
public class DriverRideHistoryFragment extends ListFragment {

    public static DriverRideHistoryFragment newInstance() {
        return new DriverRideHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_layout, vg, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawerListAdapter adapter = new DrawerListAdapter(getActivity());
        setListAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.drive_history);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Drive drive = DrivesMockUp.getDrives().get(position);
        Bundle args = new Bundle();
        args.putParcelable("driveInfo", drive);
        DriveItemDetail driveItemDetail = new DriveItemDetail();
        driveItemDetail.setArguments(args);
        FragmentTransition.to(driveItemDetail, getActivity(), true);

    }


}