package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.DriverDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface IDriverService {

    @Headers(
            {
                    "User-Agent: Mobile-Android",
                    "Content-Type:application/json"
            }
    )
    @GET(ServiceUtils.driver + "/{id}")
    Call<DriverDTO> getDriver(@Path("id") Long id);


}
