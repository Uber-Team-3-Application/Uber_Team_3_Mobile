package com.example.uberapp_tim3.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.vezbe4.adapters.DrawerListAdapter;

/**
 * A fragment representing a list of Items.
 */
public class DriverRideHistoryFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_layout, vg, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();

        //Dodaje se adapter
        DrawerListAdapter adapter = new DrawerListAdapter(getActivity());
        setListAdapter(adapter);


    }



}