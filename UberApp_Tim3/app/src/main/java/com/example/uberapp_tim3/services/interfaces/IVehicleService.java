package com.example.uberapp_tim3.services.interfaces;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IVehicleService {

    @PUT(ServiceUtils.vehicle + "/simulation/{id}")
    Call<String> startSimulation(@Path("id") Long id);
}
