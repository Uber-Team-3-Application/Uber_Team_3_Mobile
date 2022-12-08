package com.example.uberapp_tim3.model.vehicle;

import com.example.uberapp_tim3.model.enums.VehicleName;

import java.util.ArrayList;

public class TypeOfVehicle {
    private Long id;
    private VehicleName vehicleName;
    private double pricePerKilometer;

    public TypeOfVehicle() {
        this.pricePerKilometer = 0.0;
    }

    public TypeOfVehicle(Long id, VehicleName vehicleName, double pricePerKilometer) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.pricePerKilometer = pricePerKilometer;
    }

    public TypeOfVehicle(VehicleName vehicleName, double pricePerKilometer) {
        this.vehicleName = vehicleName;
        this.pricePerKilometer = pricePerKilometer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
