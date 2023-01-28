package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.ReviewDTO;
import com.example.uberapp_tim3.model.DTO.ReviewWithPassengerDTO;
import com.example.uberapp_tim3.model.DTO.RideReviewDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IReviewService {

    @GET(ServiceUtils.review + "/{id}")
    Call<List<RideReviewDTO>> getReviews(@Path("id") Long id);

    @POST(ServiceUtils.review + "/{rideId}/vehicle")
    Call<ReviewWithPassengerDTO> createReviewAboutVehicle(@Path("rideId") int rideId, @Body ReviewDTO r);

    @POST(ServiceUtils.review + "/{rideId}/driver")
    Call<ReviewWithPassengerDTO> leaveReviewForTheDriver(@Path("rideId") Long rideId, @Body ReviewDTO r);
}
