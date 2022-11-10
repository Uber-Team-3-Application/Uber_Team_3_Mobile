package com.example.uberapp_tim3.model;


import android.media.Image;

public class Passenger extends User{


    public Passenger(int id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked);
    }

    public Passenger(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked);
    }


}
