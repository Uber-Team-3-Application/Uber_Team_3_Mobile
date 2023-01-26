package com.example.uberapp_tim3.services.interfaces;

import android.app.Service;

import com.example.uberapp_tim3.model.DTO.CreateWorkingHoursDTO;
import com.example.uberapp_tim3.model.DTO.DriverActivityDTO;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.EndWorkingHoursDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.DTO.VehicleDTO;
import com.example.uberapp_tim3.model.DTO.WorkingHoursDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IDriverService {

    @Headers(
            {"User-Agent: Mobile-Android",
                "Content-Type:application/json"}
    )
    @GET(ServiceUtils.driver + "/{id}")
    Call<UserDTO> getDriver(@Path("id") Long id);

    @GET(ServiceUtils.driver + "/{id}/ride")
    Call<Paginated<DriverRideDTO>> getRides(@Path("id") Long id,
                                            @Query("page") int page,
                                            @Query("size") int size,
                                            @Query("sort") String sort);

    @POST(ServiceUtils.driver + "/{id}/activity")
    Call<String> changeActivity(@Path("id") Long id, @Body DriverActivityDTO driverActivityDTO);

    @GET(ServiceUtils.driver + "/{id}/vehicle")
    Call<VehicleDTO> getVehicle(@Path("id") Long id);
    @POST(ServiceUtils.driver + "/{id}/working-hour")
    Call<WorkingHoursDTO> createWorkingHours(@Path("id") Long id,
                                             @Body CreateWorkingHoursDTO workingHoursDTO);


    @PUT(ServiceUtils.driver + "/working-hour/{working-hour-id}")
    Call<WorkingHoursDTO> changeWorkingHours(@Path("working-hour-id") Long id,
                                             @Body EndWorkingHoursDTO workingHoursDTO);
}
