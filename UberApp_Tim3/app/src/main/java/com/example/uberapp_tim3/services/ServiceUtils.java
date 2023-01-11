package com.example.uberapp_tim3.services;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.uberapp_tim3.BuildConfig;
import com.example.uberapp_tim3.services.interfaces.IDriverService;
import com.example.uberapp_tim3.services.interfaces.IUserService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {
    public static final String SERVICE_API_PATH = BuildConfig.LOCALHOST;
    public static final String driver = "driver";
    public static final String passenger = "passenger";
    public static final String vehicle = "vehicle";
    public static final String review = "review";
    public static final String user = "user";
    public static final String ride = "ride";
    public static final String panic = "panic";
    public static final String unregisteredUser = "unregisteredUser";
    public static OkHttpClient test(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                        .addInterceptor(new CustomInterceptor()).
                build();

        return client;
    }
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(test())
            .build();

    public static IDriverService driverService = retrofit.create(IDriverService.class);
    public static IUserService userService = retrofit.create(IUserService.class);

}
