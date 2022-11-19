package com.example.uberapp_tim3.model.mockup;

import android.media.Image;

public class Passenger extends User {


    public Passenger(int id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String address) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address);
    }

    public Passenger(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String address) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address);
    }


}