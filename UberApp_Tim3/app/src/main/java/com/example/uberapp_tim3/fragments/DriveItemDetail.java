package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.Drive;

import java.util.Objects;

public class DriveItemDetail extends Fragment {

    public DriveItemDetail() {
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
        return inflater.inflate(R.layout.fragment_ride_item_detail, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();

        assert bundle != null;
        Drive drive = bundle.getParcelable("driveInfo");

        TextView viewStartStation = view.findViewById(R.id.txtStartDrivingStation);
        viewStartStation.setText(drive.getrelation().split("-")[0]);

        TextView txtEndStation = view.findViewById(R.id.txtEndDrivingStation);
        txtEndStation.setText(drive.getrelation().split("-")[1]);

        TextView textView = view.findViewById(R.id.txtRouteFullName);
        textView.setText(drive.getrelation());

        TextView txtStartDriving = view.findViewById(R.id.txtStartDriving);
        txtStartDriving.setText(drive.getStartDrive());

        TextView txtEndDriving = view.findViewById(R.id.txtEndDriving);
        txtEndDriving.setText(drive.getEndDrive());

        TextView txtPassengerNum = view.findViewById(R.id.txtPassengerNum);
        txtPassengerNum.setText(String.valueOf(drive.getNumberOfPassengers()));

        TextView txtKmNum = view.findViewById(R.id.txtKilometersNum);
        txtKmNum.setText(String.valueOf(drive.getKm()));

        TextView txtComments = view.findViewById(R.id.txtComments);
        txtComments.setText(String.valueOf(drive.getComment()));

        RatingBar simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
        simpleRatingBar.setEnabled(false);
        simpleRatingBar.setRating(drive.getRate());

        TextView priceView = view.findViewById(R.id.txtPriceOfDrive);
        priceView.setText(String.valueOf(drive.getPrice()));
    }



}