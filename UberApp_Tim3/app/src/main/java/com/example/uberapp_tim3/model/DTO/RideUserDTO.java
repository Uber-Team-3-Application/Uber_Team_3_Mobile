package com.example.uberapp_tim3.model.DTO;

import com.example.uberapp_tim3.model.users.User;

public class RideUserDTO {

    private Long id;
    private String email;

    public RideUserDTO(){

    }
    public RideUserDTO(String email) {
        this.email = email;

    }

    public RideUserDTO(Long id, String email) {
        this.id = id;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
