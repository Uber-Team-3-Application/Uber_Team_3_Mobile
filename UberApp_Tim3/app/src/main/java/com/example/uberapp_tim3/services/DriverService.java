package com.example.uberapp_tim3.services;

import android.util.Log;

import com.example.uberapp_tim3.model.DTO.DriverDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverService {

    private DriverDTO driver;

    public DriverDTO getDriver(Long id){

        Call<DriverDTO> call = ServiceUtils.driverService.getDriver(id);
        call.enqueue(new Callback<DriverDTO>() {
            @Override
            public void onResponse(Call<DriverDTO> call, Response<DriverDTO> response) {
                if(!response.isSuccessful()) return;
                driver = response.body();
            }

            @Override
            public void onFailure(Call<DriverDTO> call, Throwable t) {
                Log.d("FAIIIL", t.getMessage());
                Log.d("FAIIIL", "BLATRUC");
            }
        });
        return this.driver;
    }


}
