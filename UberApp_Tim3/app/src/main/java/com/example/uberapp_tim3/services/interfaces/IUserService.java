package com.example.uberapp_tim3.services.interfaces;

import com.example.uberapp_tim3.model.DTO.ChangePasswordDTO;
import com.example.uberapp_tim3.model.DTO.EmailDTO;
import com.example.uberapp_tim3.model.DTO.LoginDTO;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;
import com.example.uberapp_tim3.model.DTO.ResetPasswordDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.users.User;
import com.example.uberapp_tim3.model.DTO.MessageFullDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.SendMessageDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUserService {

    @Headers(
            {
                    "User-Agent: Mobile-Android",
                    "Content-Type:application/json"
            }
    )

    @POST(ServiceUtils.user + "/login")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);

    @GET(ServiceUtils.user + "/{id}/resetPassword")
    Call<String> resetPassword(@Path("id") Long id);

    @PUT(ServiceUtils.user + "/{id}/resetPassword")
    Call<String> resetPasswordWithCode(@Path("id") Long id, @Body ResetPasswordDTO dto);

    @PUT(ServiceUtils.user + "/{id}/changePassword")
    Call<String> changePassword(@Path("id") Long id, @Body ChangePasswordDTO changePasswordDTO);

    @GET(ServiceUtils.user + "/email")
    Call<UserDTO> findByEmail(@Query("email") String email);

    @POST(ServiceUtils.user + "/mail")
    Call<String> sendEmail(@Body EmailDTO dto);

    @GET(ServiceUtils.user + "/{id}/user")
    Call<UserDTO> findById(@Path("id") Long id);

    @POST(ServiceUtils.user + "/{id}/message")
    Call<MessageFullDTO> sendMessage(@Path("id") Long id, @Body SendMessageDTO sendMessageDTO);

    @GET(ServiceUtils.user + "/{id}/message")
    Call<Paginated<MessageFullDTO>> getMessages(@Path("id") Long id);

    @GET(ServiceUtils.user + "/admin-user")
    Call<Long> getAdminId();

    @GET(ServiceUtils.user + "/{id}/user")
    Call<UserDTO> getDriver(@Path("id") Long id);

}
