package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.drives.Ride;
import com.example.uberapp_tim3.model.users.User;

import java.time.LocalDateTime;

public class PanicButton {
    private Long id;
    private User user;
    private LocalDateTime time;
    private String description;
    private Ride drive;

    public PanicButton() {
        this.description = "";
    }

    public PanicButton(Long id, User user, LocalDateTime time, String description, Ride drive) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.description = description;
        this.drive = drive;
    }

    public PanicButton(User user, LocalDateTime time, String description, Ride drive) {
        this.user = user;
        this.time = time;
        this.description = description;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ride getDrive() {
        return drive;
    }

    public void setDrive(Ride drive) {
        this.drive = drive;
    }
}
