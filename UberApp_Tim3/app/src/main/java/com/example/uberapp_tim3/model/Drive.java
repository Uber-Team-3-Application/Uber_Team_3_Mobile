package com.example.uberapp_tim3.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Drive {

    private int rate;
    private String comment;
    private String startDrive;
    private String endDrive;
    private int numberOfPassengers;
    private String relation;
    private double km;
    private double price;

    public Drive(int rate, String comment, String startDrive, String endDrive, int numberOfPassengers,
                 String relation, double km, double price) {
        this.rate = rate;
        this.comment = comment;
        this.startDrive = startDrive;
        this.endDrive = endDrive;
        this.numberOfPassengers = numberOfPassengers;
        this.relation = relation;
        this.km = km;
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStartDrive() {
        return startDrive;
    }

    public void setStartDrive(String startDrive) {
        this.startDrive = startDrive;
    }

    public String getEndDrive() {
        return endDrive;
    }

    public void setEndDrive(String endDrive) {
        this.endDrive = endDrive;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getrelation() {
        return relation;
    }

    public void setrelation(String relation) {
        this.relation = relation;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
