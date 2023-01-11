package com.example.uberapp_tim3.model.DTO;

import com.google.gson.annotations.SerializedName;

public class LoginResponseDTO {

    @SerializedName("token")
    String token;
    @SerializedName("refreshToken")
    String refreshToken;

    public LoginResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LoginResponseDTO(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
