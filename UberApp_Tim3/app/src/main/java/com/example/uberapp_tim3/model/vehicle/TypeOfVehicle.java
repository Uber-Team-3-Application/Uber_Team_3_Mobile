package com.example.uberapp_tim3.model.vehicle;

import com.example.uberapp_tim3.model.enums.VehicleName;
import com.example.uberapp_tim3.model.drives.Drive;

import java.util.ArrayList;

public class TypeOfVehicle {
    private int id;
    private VehicleName vehicleName;
    private double pricePerKilometer;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Drive> drives;

    public TypeOfVehicle() {
        this.id  =0 ;
        this.pricePerKilometer = 0.0;
        this.vehicles = new ArrayList<>();
        this.drives = new ArrayList<>();
    }

    public TypeOfVehicle(int id, VehicleName vehicleName, double pricePerKilometer, ArrayList<Vehicle> vehicles, ArrayList<Drive> drives) {
        this.id = id;
        this.vehicleName = vehicleName;
        this.pricePerKilometer = pricePerKilometer;
        this.vehicles = vehicles;
        this.drives = drives;
    }

    public TypeOfVehicle(VehicleName vehicleName, double pricePerKilometer, ArrayList<Vehicle> vehicles, ArrayList<Drive> drives) {
        this.id = 0;
        this.vehicleName = vehicleName;
        this.pricePerKilometer = pricePerKilometer;
        this.vehicles = vehicles;
        this.drives = drives;
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

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Drive> getDrives() {
        return drives;
    }

    public void setDrives(ArrayList<Drive> drives) {
        this.drives = drives;
    }
}
