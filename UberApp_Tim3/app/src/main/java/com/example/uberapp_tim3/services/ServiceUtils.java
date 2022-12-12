package com.example.uberapp_tim3.services;

import com.example.uberapp_tim3.services.interfaces.IDriverService;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {
    public static final String SERVICE_API_PATH = "http://192.168.56.1:8082/api/";
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
                .addInterceptor(interceptor).build();

        return client;
    }
    public static Retrofit retrofit = new Retrofit.Builder().baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .client(test())
            .build();

    public static IDriverService driverService = retrofit.create(IDriverService.class);

}
