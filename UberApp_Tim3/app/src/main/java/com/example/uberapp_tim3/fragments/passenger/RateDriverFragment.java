package com.example.uberapp_tim3.fragments.passenger;

import android.media.Rating;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.MapFragment;
import com.example.uberapp_tim3.model.DTO.ReviewDTO;
import com.example.uberapp_tim3.model.DTO.ReviewWithPassengerDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RideReviewDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateDriverFragment extends Fragment {

    private RideDTO rideDTO;
    TextView txtDriverComment;
    RatingBar driverRatingBar;
    RatingBar vehicleRatingBar;
    TextView txtVehicleComment;

    public RateDriverFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rate_driver, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        assert bundle != null;
        rideDTO = bundle.getParcelable("ride");
        setViews();
        setListeners();


    }

    private void setListeners() {
        setSendBtnListener();
        laterBtnListener();
    }

    private void laterBtnListener() {
        Button laterBtn = requireActivity().findViewById(R.id.btnMaybeLater);
        laterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).addToBackStack(null).commit();


            }
        });
    }

    private void setSendBtnListener() {
        Button sendBtn = requireActivity().findViewById(R.id.btnSend);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtDriverComment.getText().length() > 1 && driverRatingBar.getRating() != 0) {

                    ServiceUtils.reviewService.leaveReviewForTheDriver((long)rideDTO.getId().intValue(),
                            new ReviewDTO((int)driverRatingBar.getRating(), txtDriverComment.getText().toString())).enqueue(new Callback<ReviewWithPassengerDTO>() {
                        @Override
                        public void onResponse(Call<ReviewWithPassengerDTO> call, Response<ReviewWithPassengerDTO> response) {
                            checkVehicleIsRated(rideDTO);

                        }

                        @Override
                        public void onFailure(Call<ReviewWithPassengerDTO> call, Throwable t) {

                        }
                    });
                } else {
                    checkVehicleIsRated(rideDTO);
                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).addToBackStack(null).commit();

            }
        });
    }

    private void checkVehicleIsRated(RideDTO rideDTO) {
        if (txtVehicleComment.getText().length() > 1 && vehicleRatingBar.getRating() != 0) {

            ServiceUtils.reviewService.createReviewAboutVehicle(rideDTO.getId().intValue(),
                    new ReviewDTO((int)vehicleRatingBar.getRating(), txtVehicleComment.getText().toString())).enqueue(new Callback<ReviewWithPassengerDTO>() {
                @Override
                public void onResponse(Call<ReviewWithPassengerDTO> call, Response<ReviewWithPassengerDTO> response) {

                }

                @Override
                public void onFailure(Call<ReviewWithPassengerDTO> call, Throwable t) {

                }
            });
        }
    }

    private void setViews() {
        txtDriverComment = requireActivity().findViewById(R.id.txtDriverCommentRating);
        txtVehicleComment = requireActivity().findViewById(R.id.txtVehicleCommentRating);
        driverRatingBar = requireActivity().findViewById(R.id.driverRatingBar);
        vehicleRatingBar = requireActivity().findViewById(R.id.vehicleRatingBar);

    }
}