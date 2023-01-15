package com.example.uberapp_tim3.model.DTO;

public class PassengerEmailDTO {
    private Long id;
    private String email;

    public PassengerEmailDTO(Long id, String email) {
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
