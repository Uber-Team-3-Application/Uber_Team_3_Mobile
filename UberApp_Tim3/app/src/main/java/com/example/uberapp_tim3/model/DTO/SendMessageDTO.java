package com.example.uberapp_tim3.model.DTO;

public class SendMessageDTO {

    private String message;
    private String type;
    private Long rideId;

    public SendMessageDTO() {
    }

    public SendMessageDTO(String message, String type, Long rideId) {
        this.message = message;
        this.type = type;
        this.rideId = rideId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
}
