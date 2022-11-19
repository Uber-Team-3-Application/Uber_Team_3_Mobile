package com.example.uberapp_tim3.model.drives;
import java.util.ArrayList;

public class Location {
    private int id;
    private double latitude;
    private double longitude;


    public Location() {
        super();
        this.id = 0;
        this.latitude = 0.0;
        this.longitude = 0.0;
    }


    public Location(int id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public Location(double latitude, double longitude) {
        this.id = 0;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }




}
