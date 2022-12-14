package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.uberapp_tim3.DriverMainActivity;
import com.example.uberapp_tim3.R;


public class DriverHomeFragment extends Fragment {


    public static DriverHomeFragment newInstance() {
        return new DriverHomeFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.home);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_driver_home, container, false);

        return view;
    }
}