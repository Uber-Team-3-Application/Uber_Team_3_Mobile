package com.example.uberapp_tim3.model.DTO;

import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;

public class MessageBundleDTO implements Parcelable {
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

    protected MessageBundleDTO(Parcel in){
        senderId = in.readLong();
        receiverId = in.readLong();
        rideId = in.readLong();
        messageType = in.readString();
    }
    public MessageBundleDTO(Long senderId, Long receiverId, Long rideId, String messageType) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.rideId = rideId;
        this.messageType = messageType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(senderId);
        parcel.writeLong(receiverId);
        parcel.writeLong(rideId);
        parcel.writeString(messageType);
    }
}
