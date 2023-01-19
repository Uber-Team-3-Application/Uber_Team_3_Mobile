package com.example.uberapp_tim3.model.DTO;

import java.util.Date;

public class MessageDisplayDTO {
    private Long id;
    private Long receiverId;
    private String contactName;
    private String contactPicture;
    private Date lastMessageTime;
    private String lastMessage;
    private String messageType;
    private Long rideId;

    public MessageDisplayDTO() {
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPicture() {
        return contactPicture;
    }

    public void setContactPicture(String contactPicture) {
        this.contactPicture = contactPicture;
    }

    public Date getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Date lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageDisplayDTO(Long id, Long receiverId, String contactName, String contactPicture, Date lastMessageTime, String lastMessage, String messageType, Long rideId) {
        this.id = id;
        this.receiverId = receiverId;
        this.contactName = contactName;
        this.contactPicture = contactPicture;
        this.lastMessageTime = lastMessageTime;
        this.lastMessage = lastMessage;
        this.messageType = messageType;
        this.rideId = rideId;
    }

    public MessageDisplayDTO(Long receiverId, String contactName, String contactPicture, Date lastMessageTime, String lastMessage, String messageType, Long rideId) {
        this.receiverId = receiverId;
        this.contactName = contactName;
        this.contactPicture = contactPicture;
        this.lastMessageTime = lastMessageTime;
        this.lastMessage = lastMessage;
        this.messageType = messageType;
        this.rideId = rideId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "MessageDisplayDTO{" +
                "receiverId=" + receiverId +
                ", contactName='" + contactName + '\'' +
                ", contactPicture='" + contactPicture + '\'' +
                ", lastMessageTime=" + lastMessageTime +
                ", lastMessage='" + lastMessage + '\'' +
                ", messageType='" + messageType + '\'' +
                ", rideId=" + rideId +
                '}';
    }
}
