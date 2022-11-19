package com.example.uberapp_tim3.model;

import android.media.Image;

public class Admin extends User{

    public Admin(int id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String address) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address);
    }

    public Admin(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String address) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address);
    }

    @Override
    public void setBlocked(boolean blocked) {
        super.setBlocked(false);
    }
}
