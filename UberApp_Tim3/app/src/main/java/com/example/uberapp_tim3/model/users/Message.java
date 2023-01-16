package com.example.uberapp_tim3.model.users;

import com.example.uberapp_tim3.model.enums.MessageType;
import com.example.uberapp_tim3.model.drives.Ride;
import com.example.uberapp_tim3.model.users.User;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private String text;
    private LocalDateTime timeOfSending;
    private MessageType messageType;
    private User receiver;
    private User sender;
    private Ride drive;

    public Message() {
        this.text = "";

    }

    public Message(Long id, String text, LocalDateTime timeOfSending, MessageType messageType, User receiver, User sender, Ride drive) {
        this.id = id;
        this.text = text;
        this.timeOfSending = timeOfSending;
        this.messageType = messageType;
        this.receiver = receiver;
        this.sender = sender;
        this.drive = drive;
    }

    public Message(String text, LocalDateTime timeOfSending, MessageType messageType, User receiver, User sender, Ride drive) {

        this.text = text;
        this.timeOfSending = timeOfSending;
        this.messageType = messageType;
        this.receiver = receiver;
        this.sender = sender;
        this.drive = drive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeOfSending() {
        return timeOfSending;
    }

    public void setTimeOfSending(LocalDateTime timeOfSending) {
        this.timeOfSending = timeOfSending;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Ride getDrive() {
        return drive;
    }

    public void setDrive(Ride drive) {
        this.drive = drive;
    }
}
