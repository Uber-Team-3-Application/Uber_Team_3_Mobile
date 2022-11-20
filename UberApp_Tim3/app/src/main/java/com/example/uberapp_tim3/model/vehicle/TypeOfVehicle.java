package com.example.uberapp_tim3.model.vehicle;

import com.example.uberapp_tim3.model.enums.VehicleName;

import java.util.ArrayList;

public class TypeOfVehicle {
    private int id;
    private VehicleName vehicleName;
    private double pricePerKilometer;

    public TypeOfVehicle() {
        this.id  =0 ;
        this.pricePerKilometer = 0.0;
    }

    public TypeOfVehicle(int id, VehicleName vehicleName, double pricePerKilometer) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.pricePerKilometer = pricePerKilometer;
    }

    public TypeOfVehicle(VehicleName vehicleName, double pricePerKilometer) {
        this.id = 0;
        this.vehicleName = vehicleName;
        this.pricePerKilometer = pricePerKilometer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleName getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(VehicleName vehicleName) {
        this.vehicleName = vehicleName;
    }

    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    public void setPricePerKilometer(double pricePerKilometer) {
        this.pricePerKilometer = pricePerKilometer;
    }
}
