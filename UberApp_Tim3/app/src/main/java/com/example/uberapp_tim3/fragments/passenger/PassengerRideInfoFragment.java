package com.example.uberapp_tim3.fragments.passenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.dialogs.FavouriteDialog;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.fragments.driver.DriverInfoProfile;
import com.example.uberapp_tim3.model.DTO.CreateFavouriteRideDTO;
import com.example.uberapp_tim3.model.DTO.FavouriteRideDTO;
import com.example.uberapp_tim3.model.DTO.LocationDTO;
import com.example.uberapp_tim3.model.DTO.PassengerRideDTO;
import com.example.uberapp_tim3.model.DTO.ReviewDTO;
import com.example.uberapp_tim3.model.DTO.ReviewWithPassengerDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RideReviewDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DrivesMockUp;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerRideInfoFragment extends Fragment {

    private PassengerRideDTO rideDTO;
    private RatingBar passengerRideInfoDriverRating;
    private RatingBar passengerRideInfoCarRating;
    private TextView  passVehicleComment;
    private TextView passDriverComment;
    private TextView txtStartStation;
    private TextView txtEndStation;
    private TextView txtStartTime;
    private TextView txtEndTime;
    private TextView txtKilometers;
    private TextView txtPrice;
    private Button driverCommentOkButton;
    private Button vehicleCommentOkButton;
    private ImageView heart;
    private boolean isRideFavourite;

    public PassengerRideInfoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passenger_ride_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null)
            rideDTO = bundle.getParcelable("ride");

        passengerRideInfoDriverRating = requireActivity().findViewById(R.id.passengerRideInfoDriverRating);
        passengerRideInfoCarRating = requireActivity().findViewById(R.id.passengerRideInfoCarRating);
        passVehicleComment = requireActivity().findViewById(R.id.passVehicleComment);
        passDriverComment = requireActivity().findViewById(R.id.passDriverComment);
        txtStartTime = requireActivity().findViewById(R.id.txtStartTime);
        txtEndTime = requireActivity().findViewById(R.id.txtEndTime);
        txtKilometers = requireActivity().findViewById(R.id.txtKilometers);
        txtEndStation = requireActivity().findViewById(R.id.txtEndStation);
        txtPrice = requireActivity().findViewById(R.id.txtPrice);
        txtStartStation = requireActivity().findViewById(R.id.txtStartStation);
        heart = requireActivity().findViewById(R.id.likedHeart);

        isRideFavourite = false;
        SetReviews();
        checkReviewsAreDisabled();
        SetRideInfo();
        setOnClickListeners();
        if (rideDTO.getPassengers().size() > 1)
            fillOtherPassengers();
    }



    private void checkReviewsAreDisabled() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -3);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(rideDTO.getEndTime());
        vehicleCommentOkButton = requireActivity().findViewById(R.id.vehicleCommentOkButton);
        driverCommentOkButton = requireActivity().findViewById(R.id.driverCommentOkButton);


        if (endCal.before(cal)) {
            passengerRideInfoDriverRating.setIsIndicator(true);
            passengerRideInfoCarRating.setIsIndicator(true);
            passDriverComment.setEnabled(false);
            passVehicleComment.setEnabled(false);
            vehicleCommentOkButton.setEnabled(false);
            driverCommentOkButton.setEnabled(false);
        } else {
            setDriverCommentButtonListener();
            setVehicleCommentButtonListener();
        }
    }

    private void setVehicleCommentButtonListener() {
        vehicleCommentOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewDTO dto = new ReviewDTO((int)passengerRideInfoCarRating.getRating(), passVehicleComment.getText().toString());
                ServiceUtils.reviewService.createReviewAboutVehicle(rideDTO.getId().intValue(), dto).enqueue(new Callback<ReviewWithPassengerDTO>() {
                    @Override
                    public void onResponse(Call<ReviewWithPassengerDTO> call, Response<ReviewWithPassengerDTO> response) {

                    }

                    @Override
                    public void onFailure(Call<ReviewWithPassengerDTO> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void setDriverCommentButtonListener() {
        driverCommentOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewDTO dto = new ReviewDTO((int)passengerRideInfoDriverRating.getRating(), passDriverComment.getText().toString());
                ServiceUtils.reviewService.leaveReviewForTheDriver(rideDTO.getId(), dto).enqueue(new Callback<ReviewWithPassengerDTO>() {
                    @Override
                    public void onResponse(Call<ReviewWithPassengerDTO> call, Response<ReviewWithPassengerDTO> response) {

                    }
                    @Override
                    public void onFailure(Call<ReviewWithPassengerDTO> call, Throwable t) {

                    }
                });

            }
        });
    }


    private void SetRideInfo() {
        txtStartStation.setText(rideDTO.getLocations()
                .get(0).getDeparture().getAddress());
        txtEndStation.setText(rideDTO.getLocations()
                .get(rideDTO.getLocations().size() - 1).getDestination().getAddress());

        if(rideDTO.getEndTime() != null && rideDTO.getEndTime() != null) {
            Date startTime = rideDTO.getStartTime();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String start = sdf.format(startTime);
            txtStartTime.setText(start);
            Date endTime = rideDTO.getEndTime();
            String end = sdf.format(endTime);
            txtEndTime.setText(end);
        }

        double totalDistanceInKm = calculateDistance(rideDTO.getLocations().get(0).getDeparture(),
                rideDTO.getLocations().get(rideDTO.getLocations().size() - 1).getDestination());
        String totalDistance = totalDistanceInKm + " KM";
        txtKilometers.setText(totalDistance);
        String cost = rideDTO.getTotalCost() + " RSD";
        txtPrice.setText(cost);
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

    @SuppressLint("SetTextI18n")
    private void SetReviews() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);


        Call<List<RideReviewDTO>> call = ServiceUtils.reviewService.getReviews(rideDTO.getId());
        call.enqueue(new Callback<List<RideReviewDTO>>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(@NonNull Call<List<RideReviewDTO>> call, @NonNull Response<List<RideReviewDTO>> response) {
                if (response.body() != null)
                for (RideReviewDTO reviewDTO : response.body()) {
                    if (reviewDTO.getDriverReview().getPassenger().getId() == sharedPreferences.getLong("pref_id", 0L)) {
                        passengerRideInfoDriverRating.setRating(reviewDTO.getDriverReview().getRating());
                        passDriverComment.setText(reviewDTO.getDriverReview().getComment());
                        passengerRideInfoCarRating.setRating(reviewDTO.getVehicleReview().getRating());
                        passVehicleComment.setText(reviewDTO.getVehicleReview().getComment());
                        break;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RideReviewDTO>> call, @NonNull Throwable t) {

            }
        });
//        requireActivity().getSupportFragmentManager().beginTransaction().replace(
//                R.id.map_route_fragment_container,new DrawRouteFragment(new RideDTO(rideDTO))
//        ).commit();

    }


    private void setOnClickListeners(){

        setHeartListener();

        ImageView imgInbox = requireActivity().findViewById(R.id.imgInbox);

        ImageView imgDriver = requireActivity().findViewById(R.id.imgDriver);

        imgInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container, new PassengerInboxFragment()).addToBackStack(null).commit();
            }
        });
        imgDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putLong("driverId", rideDTO.getDriver().getId());
                DriverInfoProfile profile = new DriverInfoProfile();
                profile.setArguments(args);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profile).addToBackStack(null).commit();
            }
        });
    }

    private void setHeartListener() {
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isRideFavourite) {
                    heart.setImageResource(R.drawable.heart_liked);
                    isRideFavourite = true;
                    FavouriteDialog favouriteDialog = new FavouriteDialog(getContext(), rideDTO);
                    favouriteDialog.show();
                }
                else {
                    heart.setImageResource(R.drawable.heart_disliked);
                    ServiceUtils.rideService.deleteFavouriteRide(rideDTO.getId()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                    isRideFavourite = false;

                }
            }
        });
    }



    @SuppressLint("SetTextI18n")
    private void fillOtherPassengers(){
        LinearLayout ly = requireActivity().findViewById(R.id.lyOtherPassengers);
        LayoutInflater inflater = (LayoutInflater) requireView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SharedPreferences sharedPreferences =  requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        for(int i =0;i<rideDTO.getPassengers().size();i++){
            if (rideDTO.getPassengers().get(i).getId() == sharedPreferences.getLong("pref_id", 0L))
                continue;
            View passenger = inflater.inflate(R.layout.other_passenger_item, (ViewGroup) getView(), false);
            TextView txtPassengerFullName = passenger.findViewById(R.id.txtPassengerFullName);
            txtPassengerFullName.setText(rideDTO.getPassengers().get(i).getEmail());
            ly.addView(passenger);
        }

    }

}