package com.example.uberapp_tim3.model.mockup;

import android.media.Image;

public class User {

    private int id;
    private String name;
    private String lastName;
    private Image profilePicture;
    private String phoneNumber;
    private String emailAddress;
    private String password;

    private boolean isBlocked;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(int id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isBlocked = isBlocked;
        this.address = address;
    }

    public User(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String address) {
        this.name = name;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isBlocked = isBlocked;
        this.id = 0;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}