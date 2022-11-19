package com.example.uberapp_tim3.model.items;

import com.example.uberapp_tim3.enums.MessageType;
import com.example.uberapp_tim3.model.drives.Drive;
import com.example.uberapp_tim3.model.users.User;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String text;
    private LocalDateTime timeOfSending;
    private MessageType messageType;
    private User receiver;
    private User sender;
    private Drive drive;

    public Message() {
        this.id = 0;
        this.text = "";

    }

    public Message(int id, String text, LocalDateTime timeOfSending, MessageType messageType, User receiver, User sender, Drive drive) {
        this.id = id;
        this.text = text;
        this.timeOfSending = timeOfSending;
        this.messageType = messageType;
        this.receiver = receiver;
        this.sender = sender;
        this.drive = drive;
    }

    public Message(String text, LocalDateTime timeOfSending, MessageType messageType, User receiver, User sender, Drive drive) {
        this.id = 0;
        this.text = text;
        this.timeOfSending = timeOfSending;
        this.messageType = messageType;
        this.receiver = receiver;
        this.sender = sender;
        this.drive = drive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}
