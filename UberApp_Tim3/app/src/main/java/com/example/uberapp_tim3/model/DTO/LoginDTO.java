package com.example.uberapp_tim3.model.DTO;

import com.google.gson.annotations.SerializedName;

public class LoginDTO {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public LoginDTO() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
