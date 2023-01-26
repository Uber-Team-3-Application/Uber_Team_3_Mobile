package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.CreateRideDTO;
import com.example.uberapp_tim3.model.DTO.CreatedRideDTO;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.FavouriteRideDTO;
import com.example.uberapp_tim3.model.DTO.ReasonDTO;
import com.example.uberapp_tim3.model.DTO.ReportRequestDTO;
import com.example.uberapp_tim3.model.DTO.ReportSumAverageDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.drives.Ride;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IRideService {

    @GET(ServiceUtils.ride + "/{id}")
    Call<DriverRideDTO> getRide(@Path("id") Long id);

    @POST(ServiceUtils.ride)
    Call<CreatedRideDTO> createARide(@Body CreateRideDTO createdRideDTO);
    @PUT(ServiceUtils.ride + "/{id}/end")
    Call<DriverRideDTO> endRide(@Path("id") Long id);

    @GET(ServiceUtils.ride + "/favorites")
    Call<List<FavouriteRideDTO>> getFavouriteRides();

    @DELETE(ServiceUtils.ride + "/favorites/{id}")
    Call<ResponseBody> deleteFavouriteRide(@Path("id") Long id);
    @POST(ServiceUtils.ride + "/rides-report")
    Call<ReportSumAverageDTO> getReport(@Body ReportRequestDTO reportRequestDTO);

    @PUT(ServiceUtils.ride + "/{id}/cancel")
    Call<RideDTO> cancelRide(@Path("id") Long id, @Body ReasonDTO reasonDTO);

    @PUT(ServiceUtils.ride + "/{id}/panic")
    Call<RideDTO> panicRide(@Path("id") Long id, @Body ReasonDTO reasonDTO);
}
