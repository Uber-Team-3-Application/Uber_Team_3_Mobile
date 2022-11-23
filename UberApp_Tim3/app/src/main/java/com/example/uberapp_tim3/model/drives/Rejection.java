package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.users.User;

import java.time.LocalDateTime;

public class Rejection {
    private int id;
    private User user;
    private String description;
    private LocalDateTime time;
    private Drive drive;

    public Rejection( ) {
        this.id = 0;
        this.description = "";

    }

    public Rejection(int id, User user, String description, LocalDateTime time, Drive drive) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.time = time;
        this.drive = drive;
    }

    public Rejection(User user, String description, LocalDateTime time, Drive drive) {
        this.id = 0;
        this.user = user;
        this.description = description;
        this.time = time;
        this.drive = drive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}
