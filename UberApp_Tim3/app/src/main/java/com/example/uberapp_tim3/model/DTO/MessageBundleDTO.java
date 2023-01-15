package com.example.uberapp_tim3.model.DTO;

public class MessageBundleDTO {
    private Long senderId;
    private Long receiverId;
    private Long rideId;
    private String messageType;


    public MessageBundleDTO() {
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public MessageBundleDTO(Long senderId, Long receiverId, Long rideId, String messageType) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.rideId = rideId;
        this.messageType = messageType;
    }
}
