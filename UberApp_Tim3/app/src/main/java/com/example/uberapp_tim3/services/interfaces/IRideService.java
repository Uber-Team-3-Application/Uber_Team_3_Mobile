package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IRideService {

    @GET(ServiceUtils.ride + "/{id}")
    Call<DriverRideDTO> getRide(@Path("id") Long id);

    @PUT(ServiceUtils.ride + "/{id}/end")
    Call<DriverRideDTO> endRide(@Path("id") Long id);
}
