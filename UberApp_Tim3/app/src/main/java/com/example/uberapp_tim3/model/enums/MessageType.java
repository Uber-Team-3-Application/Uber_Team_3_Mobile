package com.example.uberapp_tim3.model.enums;

import com.example.uberapp_tim3.model.items.Message;

import java.util.ArrayList;

public enum MessageType {
    SUPPORT,
    DRIVE,
    PANIC;

    private ArrayList<Message> messages;

    MessageType() {
        this.messages = new ArrayList<>();
    }

    MessageType(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
