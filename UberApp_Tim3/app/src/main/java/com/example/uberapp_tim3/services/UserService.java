package com.example.uberapp_tim3.services;

import android.util.Log;

import com.auth0.android.jwt.JWT;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.users.User;
import com.example.uberapp_tim3.model.DTO.LoginDTO;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    public UserDTO login(String email, String password){
        LoginDTO loginDTO = new LoginDTO(email, password);
        Call<LoginResponseDTO> call = ServiceUtils.userService.login(loginDTO);
        
        call.enqueue(new Callback<LoginResponseDTO>() {
            @Override
            public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                if(!response.isSuccessful()) return;
                LoginResponseDTO loginResponse = response.body();
                JWT jwt = new JWT(loginResponse.getToken());
                // TODO set token and refresh token in shared pref
                // TODO get role
                // TODO get id
                // TODO get email
            }

            @Override
            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                Log.d("Login Failed", t.getMessage());
            }
        });
        return null;
    }

}
