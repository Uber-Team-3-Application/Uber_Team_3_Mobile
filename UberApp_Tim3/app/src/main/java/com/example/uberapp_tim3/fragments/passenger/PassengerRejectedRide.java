package com.example.uberapp_tim3.fragments.passenger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.MapFragment;

public class PassengerRejectedRide extends Fragment {

    public PassengerRejectedRide() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rejected_ride, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button btnBack = getView().findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).addToBackStack(null).commit();
    }
    });
}
}