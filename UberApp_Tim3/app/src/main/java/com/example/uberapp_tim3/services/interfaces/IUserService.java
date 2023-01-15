package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.ChangePasswordDTO;
import com.example.uberapp_tim3.model.DTO.LoginDTO;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;
import com.example.uberapp_tim3.model.users.User;
import com.example.uberapp_tim3.model.DTO.MessageFullDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.SendMessageDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserService {

    @POST(ServiceUtils.user + "/login")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);

    @PUT(ServiceUtils.user + "/{id}/changePassword")
    Call<String> changePassword(@Path("id") Long id, @Body ChangePasswordDTO changePasswordDTO);

    @GET(ServiceUtils.user + "/email")
    User findByEmail(@Body String email);
    @POST(ServiceUtils.user + "/{id}/message")
    Call<MessageFullDTO> sendMessage(@Path("id") Long id, @Body SendMessageDTO sendMessageDTO);

    @GET(ServiceUtils.user + "/{id}/message")
    Call<Paginated<MessageFullDTO>> getMessages(@Path("id") Long id);

    @GET(ServiceUtils.user + "/admin-user")
    Call<Long> getAdminId();

}
