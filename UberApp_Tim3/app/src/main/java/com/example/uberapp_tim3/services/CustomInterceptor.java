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
                .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                .build();
        Response response = chain.proceed(request);
        return response;
    }
}
