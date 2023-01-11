package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.LoginDTO;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserService {

    @POST(ServiceUtils.user + "/login")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);



}
