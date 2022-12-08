package com.example.uberapp_tim3.model.users;

import com.example.uberapp_tim3.model.users.User;

public class Remark {

    private Long id;
    private String message;
    private User user;

    public Remark(Long id, String message, User user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
