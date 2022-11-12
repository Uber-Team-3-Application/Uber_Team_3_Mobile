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

public class DriverAccountFragment extends Fragment{

    Activity activity = getActivity();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_driver_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setTextViews();
    }


    private void setTextViews(){
        Driver driver = DriverMockup.getDrivers().get(0);

        TextView txtFullName = getView().findViewById(R.id.txtdriverFullName);
        txtFullName.setText(String.format("%s %s", driver.getName(), driver.getLastName()));

        TextView txtPhoneNumber =  getView().findViewById(R.id.txtdriverPhoneNumber);
        txtPhoneNumber.setText(driver.getPhoneNumber());

        TextView txtEmailAddress =  getView().findViewById(R.id.txtdriverEmail);
        txtEmailAddress.setText(driver.getEmailAddress());

        TextView tvBlocked =  getView().findViewById(R.id.txtDriverBlocked);
        if(!driver.isBlocked())
            tvBlocked.setText("");
        else {
            tvBlocked.setText(R.string.blocked_text);
            tvBlocked.setTextColor(Color.RED);
        }
    }

}