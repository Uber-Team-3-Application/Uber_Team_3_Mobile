package com.example.uberapp_tim3.enums;

import com.example.uberapp_tim3.model.vehicle.TypeOfVehicle;

import java.util.ArrayList;

public enum VehicleName {
    STANDARD,
    LUXURY,
    VAN;

    private ArrayList<TypeOfVehicle> typeOfVehicles;

    public ArrayList<TypeOfVehicle> getTypeOfVehicles() {
        return typeOfVehicles;
    }

    public void setTypeOfVehicles(ArrayList<TypeOfVehicle> typeOfVehicles) {
        this.typeOfVehicles = typeOfVehicles;
    }

    VehicleName(ArrayList<TypeOfVehicle> typeOfVehicles) {
        this.typeOfVehicles = typeOfVehicles;
    }

    VehicleName() {
        this.typeOfVehicles = new ArrayList<>();
    }
}
