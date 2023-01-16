package com.example.uberapp_tim3.services;


import com.example.uberapp_tim3.model.DTO.TokenDTO;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        TokenDTO tokenDTO = TokenDTO.getInstance();
        Request request = chain.request()
                .newBuilder()
                .addHeader("X-Auth-Token", tokenDTO.getToken())
                .addHeader("refreshToken", tokenDTO.getRefreshToken())
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}
