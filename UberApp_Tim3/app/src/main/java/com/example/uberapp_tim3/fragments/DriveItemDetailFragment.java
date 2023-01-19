package com.example.uberapp_tim3.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.driver.DriverInboxFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerInfoProfile;
import com.example.uberapp_tim3.fragments.passenger.ProfilesOfPassengersOnDrive;
import com.example.uberapp_tim3.model.DTO.DeductionDTO;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.LocationDTO;
import com.example.uberapp_tim3.model.DTO.ReviewWithPassengerDTO;
import com.example.uberapp_tim3.model.DTO.RideReviewDTO;
import com.example.uberapp_tim3.model.DTO.RideUserDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriveItemDetailFragment extends Fragment {
    private TextView txtStartStation;
    private TextView txtEndStation;
    private TextView txtStartDriving;
    private TextView txtEndDriving;
    private TextView txtPassengerNum;
    private TextView txtKmNum;
    private TextView txtPrice;
    private RatingBar simpleRatingBar;
    private  ImageView imgInbox;
    private DriverRideDTO rideDTO;

    public DriveItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        txtStartStation = view.findViewById(R.id.txtStartDrivingStation);
        txtEndStation = view.findViewById(R.id.txtEndDrivingStation);
        txtStartDriving = view.findViewById(R.id.txtStartDriving);
        txtEndDriving = view.findViewById(R.id.txtEndDriving);
        txtPassengerNum = view.findViewById(R.id.txtPassengerNum);
        txtKmNum = view.findViewById(R.id.txtKilometersNum);
        simpleRatingBar = view.findViewById(R.id.simpleRatingBar);
        simpleRatingBar.setEnabled(false);
        txtPrice = view.findViewById(R.id.txtPriceOfDrive);

        imgInbox = view.findViewById(R.id.imgInbox);
        this.setListenerForInbox(imgInbox);
        this.getRideInfo(bundle);

    }

    private void getRideInfo(Bundle bundle) {
        Long driveId = bundle.getLong("driveId");
        Call<DriverRideDTO> call = ServiceUtils.rideService.getRide(driveId);
        call.enqueue(new Callback<DriverRideDTO>() {
            @Override
            public void onResponse(@NonNull Call<DriverRideDTO> call, @NonNull Response<DriverRideDTO> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Cant fetch ride!", Toast.LENGTH_SHORT).show();
                    return;
                }
                rideDTO = response.body();
                assert rideDTO != null;
                setBasicRideInfo(rideDTO);
                setPassengers(rideDTO.getPassengers());
                Call<List<RideReviewDTO>> reviewCall = ServiceUtils.reviewService.getReviews(rideDTO.getId());
                reviewCall.enqueue(new Callback<List<RideReviewDTO>>() {
                    @Override
                    public void onResponse(Call<List<RideReviewDTO>> call, Response<List<RideReviewDTO>> response) {
                        if(!response.isSuccessful()) return;
                        assert response.body() != null;
                        List<RideReviewDTO> reviews = response.body();
                        float rating =  getRating(rideDTO, reviews);
                        simpleRatingBar.setRating(rating);
                        setPassengerReviews(reviews);
                    }

                    @Override
                    public void onFailure(Call<List<RideReviewDTO>> call, Throwable t) {

                    }
                });



            }

            @Override
            public void onFailure(@NonNull Call<DriverRideDTO> call, @NonNull Throwable t) {
                Log.d("FAIL", "FAIL");
                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPassengers(List<RideUserDTO> passengers) {
        //lyRidePassengers
        LinearLayout linearLayout = (LinearLayout) requireView().findViewById(R.id.lyRidePassengers);
        LayoutInflater inflater = (LayoutInflater)requireView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int order = 1;
        for(RideUserDTO passenger: passengers){
            View passengerView = inflater.inflate(R.layout.passenger_item, (ViewGroup) requireView(), false);
            TextView twOrderNumber = passengerView.findViewById(R.id.txtRidePassengerOrder);
            String orderText = order + ".";
            twOrderNumber.setText(orderText);
            setListenerForProfiles(passengerView, passenger.getId());
            TextView twEmail = passengerView.findViewById(R.id.txtRidePassengerEmail);
            twEmail.setText(passenger.getEmail());
            order++;
            linearLayout.addView(passengerView);
        }

    }

    @SuppressLint("SetTextI18n")
    private void setPassengerReviews(List<RideReviewDTO> reviews) {
        LinearLayout linearLayout = (LinearLayout) requireView().findViewById(R.id.lyRideReviews);
        LayoutInflater inflater = (LayoutInflater)requireView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(RideReviewDTO review: reviews){
            if(review.getDriverReview() != null) {
                ReviewWithPassengerDTO driverReview = review.getDriverReview();
                View driverRev;
                driverRev = inflater.inflate(R.layout.review, (ViewGroup) requireView(), false);
                TextView emailDriverAddress = driverRev.findViewById(R.id.txtRideReviewPassengerEmail);
                emailDriverAddress.setText(driverReview.getPassenger().getEmail());
                RatingBar rbDriver = driverRev.findViewById(R.id.rbRideReviewRating);
                rbDriver.setRating(driverReview.getRating());
                rbDriver.setEnabled(false);
                TextView txtComment = driverRev.findViewById(R.id.txtRideReviewPassengerComment);
                txtComment.setText(driverReview.getComment());
                TextView typeOfReview = driverRev.findViewById(R.id.txtTypeOfReview);
                typeOfReview.setText("Driver review:");
                linearLayout.addView(driverRev);
            }
            if(review.getVehicleReview() != null) {
                ReviewWithPassengerDTO vehicleReview = review.getVehicleReview();
                View vehicleRev;
                vehicleRev = inflater.inflate(R.layout.review, (ViewGroup) getView(), false);
                TextView emailVehicleAddress = vehicleRev.findViewById(R.id.txtRideReviewPassengerEmail);
                emailVehicleAddress.setText(vehicleReview.getPassenger().getEmail());
                RatingBar rbVehicle = vehicleRev.findViewById(R.id.rbRideReviewRating);
                rbVehicle.setRating(vehicleReview.getRating());
                rbVehicle.setEnabled(false);
                TextView txtComment = vehicleRev.findViewById(R.id.txtRideReviewPassengerComment);
                txtComment.setText(vehicleReview.getComment());
                TextView typeOfReview = vehicleRev.findViewById(R.id.txtTypeOfReview);
                typeOfReview.setText("Vehicle review:");
                linearLayout.addView(vehicleRev);
            }
        }
    }

    private float getRating(DriverRideDTO rideDTO, List<RideReviewDTO> reviews) {
        float rating = 0;
        if(reviews == null) return 0;
        if(reviews.size() == 0)
            return 0 ;

        int totalNumOfReviews = reviews.size() * 2;
        int totalReviewScore = 0;
        for(int i =0;i<reviews.size();i++){
            ReviewWithPassengerDTO driverReview = reviews.get(i).getDriverReview();
            ReviewWithPassengerDTO vehicleReview = reviews.get(i).getVehicleReview();
            totalReviewScore += vehicleReview.getRating();
            totalReviewScore += driverReview.getRating();
        }
        rating = totalReviewScore/totalNumOfReviews;
        return rating;
    }

    private void setBasicRideInfo(DriverRideDTO rideDTO) {
        txtStartStation.setText(rideDTO.getLocations()
                .get(0).getDeparture().getAddress());
        txtEndStation.setText(rideDTO.getLocations()
                .get(rideDTO.getLocations().size() - 1).getDestination().getAddress());

        Date startTime = rideDTO.getStartTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String start = sdf.format(startTime);
        txtStartDriving.setText(start);
        Date endTime = rideDTO.getEndTime();
        String end = sdf.format(endTime);
        txtEndDriving.setText(end);
        double totalDistanceInKm = calculateDistance(rideDTO.getLocations().get(0).getDeparture(),
                                                    rideDTO.getLocations().get(rideDTO.getLocations().size() - 1).getDestination());
        String totalDistance = totalDistanceInKm + " KM";
        txtKmNum.setText(totalDistance);
        String cost = rideDTO.getTotalCost() + " RSD";
        txtPrice.setText(cost);
        String totalPassengers = Integer.toString(rideDTO.getPassengers().size());
        txtPassengerNum.setText(totalPassengers);
    }

    private double calculateDistance(LocationDTO departure, LocationDTO destination){
        double theta = departure.getLongitude() - destination.getLongitude();
        double dist = Math.sin(Math.toRadians(departure.getLatitude())) * Math.sin(Math.toRadians(destination.getLatitude()))
                + Math.cos(Math.toRadians(departure.getLatitude())) * Math.cos(Math.toRadians(destination.getLatitude())) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        double scale = Math.pow(10, 2);
        return Math.round(dist * scale) / scale;
    }

    private void setListenerForInbox(ImageView imgInbox) {
        imgInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverInboxFragment(), requireActivity(), true);

            }
        });
    }

    private void setListenerForProfiles(View profileView, Long passengerId) {

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putLong("passengerId", passengerId);

                PassengerInfoProfile profile = new PassengerInfoProfile();
                profile.setArguments(args);
                FragmentTransition.to(profile, requireActivity(), true);
            }
        });

    }


}