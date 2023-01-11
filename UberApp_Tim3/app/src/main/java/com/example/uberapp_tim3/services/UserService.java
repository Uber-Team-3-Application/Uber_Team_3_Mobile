package com.example.uberapp_tim3.services;

import android.util.Log;

import com.auth0.android.jwt.DecodeException;
import com.auth0.android.jwt.JWT;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.users.User;
import com.example.uberapp_tim3.model.DTO.LoginDTO;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    private UserDTO userDTO;

    public UserDTO login(String email, String password){
        LoginDTO loginDTO = new LoginDTO(email, password);
        Call<LoginResponseDTO> call = ServiceUtils.userService.login(loginDTO);
        call.enqueue(new Callback<LoginResponseDTO>() {
            @Override
            public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                if(!response.isSuccessful()) return;
                LoginResponseDTO loginResponse = response.body();
                String userRole = "";
                JWT jwt = new JWT(loginResponse.getToken());
                List<HashMap> role =
                        jwt.getClaim("role").asList(HashMap.class);
                for (Object values: role.get(0).values()){
                    userRole = values.toString();
                    break;
                }
                String email = jwt.getClaim("sub").asString();
                Long id = jwt.getClaim("id").asLong();
                userDTO = new UserDTO(id, email, userRole);
            }

            @Override
            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                Log.d("Login Failed", t.getMessage());
            }
        });

        return userDTO;
    }

}
