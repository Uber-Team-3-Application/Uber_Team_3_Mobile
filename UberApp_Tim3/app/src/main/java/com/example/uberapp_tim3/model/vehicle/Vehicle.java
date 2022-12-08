package com.example.uberapp_tim3.model.vehicle;

import com.example.uberapp_tim3.model.drives.Location;
import com.example.uberapp_tim3.model.users.Driver;

public class Vehicle
{
    private Long id;
    private String model;
    private String licencePlate;
    private int numberOfSeats;
    private boolean babyTransport;
    private boolean petTransport;
    private TypeOfVehicle typeOfVehicle;
    private Driver driver;
    private Location currentLocation;

    public Vehicle() {
        this.model = "";
        this.licencePlate = "";
        this.numberOfSeats = 0;
        this.babyTransport = false;
        this.petTransport = false;
    }

    public Vehicle(Long id, String model, String licencePlate, int numberOfPlaces, boolean babyTransport,
                   boolean petTransport, TypeOfVehicle typeOfVehicle, Driver driver, Location location) {
        this.id = id;
        this.model = model;
        this.licencePlate = licencePlate;
        this.numberOfSeats = numberOfPlaces;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.typeOfVehicle = typeOfVehicle;
        this.driver = driver;
        this.currentLocation = location;
    }

    public Vehicle(String model, String licencePlate, int numberOfPlaces, boolean babyTransport,
                   boolean petTransport, TypeOfVehicle typeOfVehicle, Driver driver, Location location) {
        this.model = model;
        this.licencePlate = licencePlate;
        this.numberOfSeats = numberOfPlaces;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.typeOfVehicle = typeOfVehicle;
        this.driver = driver;
        this.currentLocation = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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

    public Location getLocations() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
