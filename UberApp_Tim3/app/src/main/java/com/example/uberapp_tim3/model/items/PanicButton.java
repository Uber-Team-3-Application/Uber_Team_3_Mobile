package com.example.uberapp_tim3.model.items;

import com.example.uberapp_tim3.model.drives.Drive;
import com.example.uberapp_tim3.model.users.User;

import java.time.LocalDateTime;

public class PanicButton {
    private int id;
    private User user;
    private LocalDateTime time;
    private String description;
    private Drive drive;

    public PanicButton() {
        this.id = 0;
        this.description = "";
    }

    public PanicButton(int id, User user, LocalDateTime time, String description, Drive drive) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.description = description;
        this.drive = drive;
    }

    public PanicButton(User user, LocalDateTime time, String description, Drive drive) {
        this.id = 0;
        this.user = user;
        this.time = time;
        this.description = description;
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

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}
