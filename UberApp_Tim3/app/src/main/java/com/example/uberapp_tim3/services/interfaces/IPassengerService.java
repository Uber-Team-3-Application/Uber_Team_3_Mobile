package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.DTO.PassengerRideDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPassengerService {

    @Headers(
            {
                    "User-Agent: Mobile-Android",
                    "Content-Type:application/json"
            }
    )
    @GET(ServiceUtils.passenger + "/{id}")
    Call<PassengerDTO> getPassenger(@Path("id") Long id);

    @GET(ServiceUtils.passenger + "/{id}/ride")
    Call<Paginated<PassengerRideDTO>> getRides(@Path("id") Long id,
                                               @Query("page") int page,
                                               @Query("size") int size,
                                               @Query("sort") String sort);

    @PUT(ServiceUtils.passenger + "/{id}")
    Call<PassengerDTO> updatePassenger(@Path("id") Long id, @Body PassengerDTO passenger);

    @POST(ServiceUtils.passenger)
    Call<PassengerDTO> createPassenger(@Body PassengerDTO passengerDTO);
}
