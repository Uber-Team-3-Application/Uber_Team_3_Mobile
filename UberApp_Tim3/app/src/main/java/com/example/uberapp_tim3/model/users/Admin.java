package com.example.uberapp_tim3.model.users;

import android.media.Image;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(Long id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, boolean isActive, String address) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, isActive, address);
    }

    public Admin(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, boolean isActive, String address) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, isActive, address);
    }

    @Override
    public void setBlocked(boolean blocked) {
        super.setBlocked(false);
    }
}
