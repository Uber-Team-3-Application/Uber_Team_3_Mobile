package com.example.uberapp_tim3.model.users;

import android.media.Image;

import com.example.uberapp_tim3.model.drives.Ride;
import com.example.uberapp_tim3.model.vehicle.Document;
import com.example.uberapp_tim3.model.vehicle.Vehicle;

import java.util.HashSet;
import java.util.Set;

public class Driver extends User {

    private Set<Document> documents;
    private String vehicleRegistrationNumber;
    private Vehicle vehicle;
    private Set<Ride> rides;



    public Driver() {
        super();
        this.documents = new HashSet<>();
        this.rides = new HashSet<>();
    }

    public Driver(Set<Document> documents, String vehicleRegistrationNumber, Vehicle vehicle, Set<Ride> rides) {
        this.documents = documents;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.vehicle = vehicle;
        this.rides = rides;
    }

    public Driver(Long id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, boolean isActive, String address, Set<Document> documents, String vehicleRegistrationNumber, Vehicle vehicle, Set<Ride> rides) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, isActive, address);
        this.documents = documents;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
        this.vehicle = vehicle;
        this.rides = rides;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public Vehicle getVehicles() {
        return vehicle;
    }

    public void getVeicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }
}
