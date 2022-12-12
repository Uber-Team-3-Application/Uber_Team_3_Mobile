package com.example.uberapp_tim3.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.uberapp_tim3.model.DTO.DriverDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getDriver(){
        Call<DriverDTO> driver = ServiceUtils.driverService.getDriver(Long.parseLong("1"));

        driver.enqueue(new Callback<DriverDTO>() {
            @Override
            public void onResponse(Call<DriverDTO> call, Response<DriverDTO> response) {
                if(!response.isSuccessful()) return;

            }

            @Override
            public void onFailure(Call<DriverDTO> call, Throwable t) {

            }
        });


    }
}
