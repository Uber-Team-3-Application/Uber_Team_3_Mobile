package com.example.uberapp_tim3.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.Driver;
import com.example.uberapp_tim3.tools.DriverMockup;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.google.android.material.snackbar.Snackbar;

public class DriverAccountFragment extends Fragment implements View.OnClickListener {


    public static DriverAccountFragment newInstance() {
        return new DriverAccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_driver_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setOnClickListeners();
        setTextViews();
    }

    private void setOnClickListeners() {
        ((Button)getActivity().findViewById(R.id.btnEditDriverInfo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverEditInfoFragment(), getActivity(), true);
            }
        });
        ((Button)getActivity().findViewById(R.id.btnDriverReports)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverReportFragment(), getActivity(), true);
            }
        });
        ((Button)getActivity().findViewById(R.id.btnDriverStatistics)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverStatisticsFragment(), getActivity(), true);
            }
        });

    }


    private void setTextViews(){
        Driver driver = DriverMockup.getDrivers().get(0);

        TextView txtFullName = getView().findViewById(R.id.txtdriverFullName);
        txtFullName.setText(String.format("%s %s", driver.getName(), driver.getLastName()));

        TextView txtPhoneNumber =  getView().findViewById(R.id.txtdriverPhoneNumber);
        txtPhoneNumber.setText(driver.getPhoneNumber());

        TextView txtEmailAddress =  getView().findViewById(R.id.txtdriverEmail);
        txtEmailAddress.setText(driver.getEmailAddress());

        TextView txtAddress = getView().findViewById(R.id.txtDriverHomeAddress);
        txtAddress.setText(driver.getAddress());

        TextView tvBlocked =  getView().findViewById(R.id.txtDriverBlocked);
        if(!driver.isBlocked())
            tvBlocked.setText("");
        else {
            tvBlocked.setText(R.string.blocked_text);
            tvBlocked.setTextColor(Color.RED);
        }
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "Functionality not implemented!", Toast.LENGTH_SHORT).show();
    }

}