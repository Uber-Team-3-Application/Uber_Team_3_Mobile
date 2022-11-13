package com.example.uberapp_tim3.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.Passenger;
import com.example.uberapp_tim3.tools.PassengerMockup;

public class PassengerAccountFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_passenger_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setTextViews();
        setOnClickListeners();
    }


    private void setTextViews(){
        Passenger passenger = PassengerMockup.getPassengers().get(0);

        TextView txtFullName = getView().findViewById(R.id.txtPassengerFullName);
        txtFullName.setText(String.format("%s %s", passenger.getName(), passenger.getLastName()));

        TextView txtPhoneNumber = getView().findViewById(R.id.txtPassengerPhoneNumber);
        txtPhoneNumber.setText(passenger.getPhoneNumber());

        TextView txtEmailAddress = getView().findViewById(R.id.txtPassengerEmail);
        txtEmailAddress.setText(passenger.getEmailAddress());

        TextView txtAddress = getView().findViewById(R.id.txtPassengerAddress);
        txtAddress.setText(passenger.getAddress());

        TextView tvBlocked = getView().findViewById(R.id.txtBlocked);
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
    }

    private void setOnClickListeners() {
        Button btnEditBasicInfo = getView().findViewById(R.id.btnEditBasicPassInfo);
        Button btnPassRoutes = getView().findViewById(R.id.btnFavouriteRoutes);
        Button btnPassReports = getView().findViewById(R.id.btnPassengerReports);

        btnEditBasicInfo.setOnClickListener(this);
        btnPassReports.setOnClickListener(this);
        btnPassRoutes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "Functionality not implemented!", Toast.LENGTH_SHORT).show();
    }


}