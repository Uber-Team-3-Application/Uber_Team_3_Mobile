package com.example.uberapp_tim3.model;


import android.media.Image;

public class Passenger extends User{

    private String driversLicenseNumber;
    private String vehicleRegistrationNumber;
    private boolean isActive;

    public Passenger(int id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String driversLicenseNumber, String vehicleRegistrationNumber, boolean isActive) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked);
        this.driversLicenseNumber = driversLicenseNumber;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.isActive = isActive;
    }

    public Passenger(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, String driversLicenseNumber, String vehicleRegistrationNumber, boolean isActive) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked);
        this.driversLicenseNumber = driversLicenseNumber;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.isActive = isActive;
    }



    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
