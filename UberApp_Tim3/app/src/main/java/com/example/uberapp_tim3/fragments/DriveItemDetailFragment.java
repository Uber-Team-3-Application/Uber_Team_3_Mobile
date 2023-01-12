package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.driver.DriverInboxFragment;
import com.example.uberapp_tim3.fragments.passenger.ProfilesOfPassengersOnDrive;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriveItemDetailFragment extends Fragment {
    private TextView viewStartStation;
    private TextView txtEndStation;
    private TextView txtStartDriving;
    private TextView txtEndDriving;
    private TextView txtPassengerNum;
    private TextView txtKmNum;
    private TextView priceView;
    private RatingBar simpleRatingBar;
    private  ImageView imgComments;
    private  ImageView imgProfiles;
    private  ImageView imgInbox;

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
         viewStartStation = view.findViewById(R.id.txtStartDrivingStation);
        //viewStartStation.setText(drive.getrelation().split("-")[0]);

        txtEndStation = view.findViewById(R.id.txtEndDrivingStation);
        //txtEndStation.setText(drive.getrelation().split("-")[1]);

        txtStartDriving = view.findViewById(R.id.txtStartDriving);
        //txtStartDriving.setText(drive.getStartDrive());

        txtEndDriving = view.findViewById(R.id.txtEndDriving);
        //txtEndDriving.setText(drive.getEndDrive());

        txtPassengerNum = view.findViewById(R.id.txtPassengerNum);
        //txtPassengerNum.setText(String.valueOf(drive.getNumberOfPassengers()));

        txtKmNum = view.findViewById(R.id.txtKilometersNum);
        //txtKmNum.setText(String.valueOf(drive.getKm()));

        simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
        //simpleRatingBar.setEnabled(false);
        //simpleRatingBar.setRating(drive.getRate());

        priceView = view.findViewById(R.id.txtPriceOfDrive);
        //priceView.setText(String.valueOf(drive.getPrice()));

        imgComments = view.findViewById(R.id.imgComments);
        //setListenerForComments(imgComments, drive);


        imgProfiles = view.findViewById(R.id.imgProfiles);
        //setListenerForProfiles(imgProfiles, drive);

        imgInbox = view.findViewById(R.id.imgInbox);
        //setListenerForInbox(imgInbox, drive);
        getRideInfo(bundle);


    }

    private void getRideInfo(Bundle bundle) {
        Long driveId = bundle.getParcelable("driveId");
        Call<DriverRideDTO> call = ServiceUtils.rideService.getRide(driveId);
        call.enqueue(new Callback<DriverRideDTO>() {
            @Override
            public void onResponse(Call<DriverRideDTO> call, Response<DriverRideDTO> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Cant fetch ride!", Toast.LENGTH_SHORT).show();
                    return;
                }


            }

            @Override
            public void onFailure(Call<DriverRideDTO> call, Throwable t) {
                Log.d("FAIL", "FAIL");
                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
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