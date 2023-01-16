package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.users.User;

import java.time.LocalDateTime;

public class Rejection {
    private Long id;
    private User user;
    private String description;
    private LocalDateTime time;
    private Ride drive;

    public Rejection( ) {
        this.description = "";

    }

    public Rejection(Long id, User user, String description, LocalDateTime time, Ride drive) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.time = time;
        this.drive = drive;
    }

    public Rejection(User user, String description, LocalDateTime time, Ride drive) {
        this.user = user;
        this.description = description;
        this.time = time;
        this.drive = drive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Ride getDrive() {
        return drive;
    }

    public void setDrive(Ride drive) {
        this.drive = drive;
    }
}
