package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uberapp_tim3.R;


public class EditCarInformationFragment extends Fragment {


    public EditCarInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_car_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setOnCLickListeners();
        setSpinners();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setSpinners(){
        Spinner spBodyType = getView().findViewById(R.id.spinnerBodyType);
        String[] items = new String[]{"STANDARD", "LUXURY", "VAN"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spBodyType.setAdapter(adapter);


        Spinner spNumberOfSeats = getView().findViewById(R.id.spinnerNumberOfSeats);
        items = new String[]{"1", "2", "3", "4"};
        adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spNumberOfSeats.setAdapter(adapter);


    }


    private void setOnCLickListeners() {
        ((Button) getActivity().findViewById(R.id.btnCancelEditCar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        ((Button) getActivity().findViewById(R.id.btnSaveEditCar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getView().getContext(), "Successfully saved password!", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStackImmediate();

            }
        });
    }
}