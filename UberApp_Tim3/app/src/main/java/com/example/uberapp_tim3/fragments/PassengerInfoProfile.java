package com.example.uberapp_tim3.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.users.Passenger;
import com.example.uberapp_tim3.tools.PassengerMockup;

public class PassengerInfoProfile extends Fragment {


    public PassengerInfoProfile() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fpassenger_info_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setTextViews();

    }

    private void setTextViews(){
        Passenger passenger = PassengerMockup.getPassengers().get(0);

        TextView txtFullName = getView().findViewById(R.id.txtdriverFullName);
        txtFullName.setText(String.format("%s %s", passenger.getName(), passenger.getLastName()));

        TextView txtPhoneNumber = getView().findViewById(R.id.txtdriverPhoneNumber);
        txtPhoneNumber.setText(passenger.getPhoneNumber());

        TextView txtEmailAddress = getView().findViewById(R.id.txtdriverEmail);
        txtEmailAddress.setText(passenger.getEmailAddress());

        TextView txtAddress = getView().findViewById(R.id.txtDriverHomeAddress);
        txtAddress.setText(passenger.getAddress());

        TextView tvBlocked = getView().findViewById(R.id.txtDriverBlocked);
        if(!passenger.isBlocked())
            tvBlocked.setText("");
        else {
            tvBlocked.setText(R.string.blocked_text);
            tvBlocked.setTextColor(Color.RED);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Passenger profile");
    }

}