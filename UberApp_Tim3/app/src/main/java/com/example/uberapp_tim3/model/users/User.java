package com.example.uberapp_tim3.model.users;


public class User {

    private Long id;
    private String name;
    private String lastName;
    private String profilePicture;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private boolean isBlocked;
    private boolean isActive;
    private String address;



    public User() {
        this.isBlocked = false;
    }
    public User(String name, String lastName, String profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, boolean isActive, String address) {
        this.name = name;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isActive = isActive;
        this.address = address;
    }

    public User(Long id, String name, String lastName, String profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, boolean isActive, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isActive = isActive;
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}

