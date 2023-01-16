package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.users.Passenger;

public class Review {
    private Long id;
    private double rateDriver;
    private double rateVehicle;
    private String comment;
    private Passenger passenger;
    private Ride drive;

    public Review() {
        this.rateDriver = 0.0;
        this.rateVehicle = 0.0;
        this.comment = "";
    }

    public Review(Long id, double rateDriver, double rateVehicle, String comment, Passenger passenger, Ride drive) {
        this.id = id;
        this.rateDriver = rateDriver;
        this.rateVehicle = rateVehicle;
        this.rateVehicle = rateVehicle;	
        this.comment = comment;
        this.passenger = passenger;
        this.drive = drive;
    }

    public Review(double rateDriver, double rateVehicle, String comment, Passenger passenger, Ride drive) {
        this.rateDriver = rateDriver;
        this.rateVehicle = rateVehicle;
        this.comment = comment;
        this.passenger = passenger;
        this.drive = drive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRateDriver() {
        return rateDriver;
    }

    public void setRateDriver(double rate) {
        this.rateDriver = rate;
    }

    public double getRateVehicle() {
        return rateVehicle;
    }

    public void setRateVehicle(double rate) {
        this.rateVehicle = rate;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Ride getDrive() {
        return drive;
    }

    public void setDrive(Ride drive) {
        this.drive = drive;
    }


}
