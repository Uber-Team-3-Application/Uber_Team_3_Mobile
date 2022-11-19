package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.mockup.Driver;
import com.example.uberapp_tim3.tools.DriverMockup;


public class DriverEditInfoFragment extends Fragment {


    public DriverEditInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_driver_edit_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fillViews();
        setOnClickListeners();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setOnClickListeners() {
        ((Button)getActivity().findViewById(R.id.btnChangeDriverAvatar)).setOnClickListener(null);
        ((Button)getActivity().findViewById(R.id.btnCancelEditDriver)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        ((Button)getActivity().findViewById(R.id.btnAcceptEditDriver)).setOnClickListener(null);
    }

    private void fillViews() {

        Driver driver = DriverMockup.getDrivers().get(0);

        ((EditText)getActivity().findViewById(R.id.txtDriverEditFirstName)).setText(driver.getName());
        ((EditText)getActivity().findViewById(R.id.txtDriverEditLastName)).setText(driver.getLastName());
        ((EditText)getActivity().findViewById(R.id.txtDriverEditPhoneNumber)).setText(driver.getPhoneNumber());

        ((EditText)getActivity().findViewById(R.id.txtDriverEditLicense)).setText(driver.getDriversLicenseNumber());

        ((EditText)getActivity().findViewById(R.id.txtDriverAddressEdit)).setText(driver.getAddress());

    }

}