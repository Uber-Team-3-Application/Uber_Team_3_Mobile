package com.example.uberapp_tim3.model.vehicle;

import com.example.uberapp_tim3.model.drives.Location;
import com.example.uberapp_tim3.model.users.Driver;

import java.util.ArrayList;

public class Vehicle
{
    private int id;
    private String model;
    private String licencePlate;
    private int numberOfPlaces;
    private boolean babyTransport;
    private boolean petTransport;
    private TypeOfVehicle typeOfVehicle;
    private Driver driver;
    private ArrayList<Location> locations;

    public Vehicle() {
        this.id = 0;
        this.model = "";
        this.licencePlate = "";
        this.numberOfPlaces = 0;
        this.babyTransport = false;
        this.petTransport = false;
        this.locations = new ArrayList<>();
    }

    public Vehicle(int id, String model, String licencePlate, int numberOfPlaces, boolean babyTransport,
                   boolean petTransport, TypeOfVehicle typeOfVehicle, Driver driver, ArrayList<Location> locations) {
        this.id = id;
        this.model = model;
        this.licencePlate = licencePlate;
        this.numberOfPlaces = numberOfPlaces;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.typeOfVehicle = typeOfVehicle;
        this.driver = driver;
        this.locations = locations;
    }

    public Vehicle(String model, String licencePlate, int numberOfPlaces, boolean babyTransport,
                   boolean petTransport, TypeOfVehicle typeOfVehicle, Driver driver, ArrayList<Location> locations) {
        this.id = 0;
        this.model = model;
        this.licencePlate = licencePlate;
        this.numberOfPlaces = numberOfPlaces;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.typeOfVehicle = typeOfVehicle;
        this.driver = driver;
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(TypeOfVehicle typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
