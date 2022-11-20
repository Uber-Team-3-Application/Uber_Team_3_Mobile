package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.users.Passenger;

public class Review {
    private int id;
    private double rateDriver;
    private double rateVehicle;
    private String comment;
    private Passenger passenger;
    private Drive drive;

    public Review() {
        this.id = 0;
        this.rate = 0.0;
        this.comment = "";
    }

    public Review(int id, double rateDriver, double rateVehicle, String comment, Passenger passenger, Drive drive) {
        this.id = id;
        this.rate = rate;
        this.rateVehicle = rateVehicle;	
        this.comment = comment;
        this.passenger = passenger;
        this.drive = drive;
    }

    public Review(double rateDriver, double rateVehicle, String comment, Passenger passenger, Drive drive) {
        this.id = 0;
        this.rate = rate;
        this.rateVehicle = rateVehicle;
        this.comment = comment;
        this.passenger = passenger;
        this.drive = drive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}
