package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.driver.DriverInboxFragment;
import com.example.uberapp_tim3.fragments.passenger.ProfilesOfPassengersOnDrive;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.tools.FragmentTransition;

public class DriveItemDetailFragment extends Fragment {

    public DriveItemDetailFragment() {
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
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Drive details");
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

        TextView txtStartDriving = view.findViewById(R.id.txtStartDriving);
        txtStartDriving.setText(drive.getStartDrive());

        TextView txtEndDriving = view.findViewById(R.id.txtEndDriving);
        txtEndDriving.setText(drive.getEndDrive());

        TextView txtPassengerNum = view.findViewById(R.id.txtPassengerNum);
        txtPassengerNum.setText(String.valueOf(drive.getNumberOfPassengers()));

        TextView txtKmNum = view.findViewById(R.id.txtKilometersNum);
        txtKmNum.setText(String.valueOf(drive.getKm()));

        RatingBar simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
        simpleRatingBar.setEnabled(false);
        simpleRatingBar.setRating(drive.getRate());

        TextView priceView = view.findViewById(R.id.txtPriceOfDrive);
        priceView.setText(String.valueOf(drive.getPrice()));

        ImageView imgComments = view.findViewById(R.id.imgComments);
        setListenerForComments(imgComments, drive);


        ImageView imgProfiles = view.findViewById(R.id.imgProfiles);
        setListenerForProfiles(imgProfiles, drive);

        ImageView imgInbox = view.findViewById(R.id.imgInbox);
        setListenerForInbox(imgInbox, drive);


    }

    private void setListenerForInbox(ImageView imgInbox, Drive drive) {
        imgInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverInboxFragment(), getActivity(), true);

            }
        });
    }

    private void setListenerForProfiles(ImageView imgProfiles, Drive drive) {

        imgProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putParcelable("driveInfo", drive);
                ProfilesOfPassengersOnDrive profiles = new ProfilesOfPassengersOnDrive();
                profiles.setArguments(args);
                FragmentTransition.to(profiles, getActivity(), true);
            }
        });

    }

    private void setListenerForComments(ImageView imgComments, Drive drive) {
        imgComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle args = new Bundle();
                args.putParcelable("driveInfo", drive);
                CommentsFragment commentsFragment = new CommentsFragment();
                commentsFragment.setArguments(args);
                FragmentTransition.to(commentsFragment, getActivity(), true);

            }
        });
    }

}