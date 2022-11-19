package com.example.uberapp_tim3.fragments.passanger;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.mockup.Passenger;
import com.example.uberapp_tim3.tools.PassengerMockup;


public class PassengerEditInfoFragment extends Fragment {


    public PassengerEditInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.edit_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_edit_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fillViews();
        setOnClickListeners();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setOnClickListeners() {
        ((Button) getActivity().findViewById(R.id.btnChangePassengerAvatar)).setOnClickListener(null);
        ((Button) getActivity().findViewById(R.id.btnCancelEditPassenger)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        ((Button) getActivity().findViewById(R.id.btnAcceptEditPassenger)).setOnClickListener(null);

    }



    private void fillViews() {

        Passenger passenger = PassengerMockup.getPassengers().get(0);

        ((EditText) getActivity().findViewById(R.id.txtPassengerEditFirstName)).setText(passenger.getName());
        ((EditText) getActivity().findViewById(R.id.txtPassengerEditLastName)).setText(passenger.getLastName());
        ((EditText) getActivity().findViewById(R.id.txtPassengerEditPhoneNumber)).setText(passenger.getPhoneNumber());

        ((EditText) getActivity().findViewById(R.id.txtHomeAddressEdit)).setText(passenger.getAddress());

    }

}