package com.example.uberapp_tim3.model.DTO;

public class TokenDTO {

    private static TokenDTO instance;

    private String token;
    private String refreshToken;

    private TokenDTO() {
    }

    public static TokenDTO getInstance() {
        if (instance == null) {
            instance = new TokenDTO();
        }
        return instance;
    }

    public String getToken() {
        if(token == null) return "";

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {

        if(refreshToken == null) return "";
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
