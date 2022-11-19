package com.example.uberapp_tim3.model.users;

import android.media.Image;

import com.example.uberapp_tim3.model.drives.Drive;
import com.example.uberapp_tim3.model.items.Message;
import com.example.uberapp_tim3.model.items.PanicButton;
import com.example.uberapp_tim3.model.drives.Rejection;
import com.example.uberapp_tim3.model.vehicle.Vehicle;
import com.example.uberapp_tim3.model.items.WorkingHour;

import java.util.ArrayList;

public class Driver extends User {

    private String driversLicenseNumber;
    private String vehicleRegistrationNumber;
    private boolean isActive;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Drive> drives;

    public Driver(int id, String name, String lastName, Image profilePicture, String phoneNumber,
                  String emailAddress, String password, boolean isBlocked, String address,
                  ArrayList<Message> receiverInbox, ArrayList<Message> senderInbox,
                  ArrayList<PanicButton> panicButtons, ArrayList<Rejection> rejections,
                  String driversLicenseNumber, String vehicleRegistrationNumber, boolean isActive,
                  ArrayList<Vehicle> vehicles, ArrayList<Drive> drives) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons, rejections);
        this.driversLicenseNumber = driversLicenseNumber;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.isActive = isActive;
        this.vehicles = vehicles;
        this.drives = drives;
    }

    public Driver(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress,
                  String password, boolean isBlocked, String address, ArrayList<Message> receiverInbox,
                  ArrayList<Message> senderInbox, ArrayList<PanicButton> panicButtons,
                  ArrayList<Rejection> rejections, String driversLicenseNumber, String vehicleRegistrationNumber,
                  boolean isActive, ArrayList<Vehicle> vehicles,
                  ArrayList<Drive> drives) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons, rejections);
        this.driversLicenseNumber = driversLicenseNumber;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.isActive = isActive;
        this.vehicles = vehicles;
        this.drives = drives;
    }

    public Driver() {
        super();
        this.isActive = false;
        this.vehicles = new ArrayList<>();
        this.drives = new ArrayList<>();
    }

    public ArrayList<Drive> getDrives() {
        return drives;
    }

    public void setDrives(ArrayList<Drive> drives) {
        this.drives = drives;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
