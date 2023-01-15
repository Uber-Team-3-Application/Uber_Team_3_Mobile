package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.ChangePasswordDTO;
import com.example.uberapp_tim3.model.DTO.LoginDTO;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;
import com.example.uberapp_tim3.model.users.User;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserService {

    @POST(ServiceUtils.user + "/login")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);

    @PUT(ServiceUtils.user + "/{id}/changePassword")
    Call<String> changePassword(@Path("id") Long id, @Body ChangePasswordDTO changePasswordDTO);

    @GET(ServiceUtils.user + "/email")
    User findByEmail(@Body String email);
}
