package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.ReviewWithPassengerDTO;
import com.example.uberapp_tim3.model.DTO.RideReviewDTO;
import com.example.uberapp_tim3.model.drives.Review;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IReviewService {

    @GET(ServiceUtils.review + "/{id}")
    Call<List<RideReviewDTO>> getReviews(@Path("id") Long id);
}
