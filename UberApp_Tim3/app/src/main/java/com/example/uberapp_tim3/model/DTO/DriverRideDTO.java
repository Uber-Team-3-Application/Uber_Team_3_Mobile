package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class DriverRideDTO {

    private  Long id;
    private Date startTime;
    private  Date endTime;
    private  double totalCost;
    private UserDTO driver;
    private List<UserDTO> passengers;
    private  double estimatedTimeInMinutes;
    private  String vehicleType;
    private  boolean babyTransport;
    private  boolean petTransport;
    private  DeductionDTO rejection;
    private  List<RouteDTO> locations;
    private List<RideReviewDTO> reviews;
    public DriverRideDTO() {
    }

    public DriverRideDTO(Long id, Date startTime, Date endTime, double totalCost, UserDTO driver, List<UserDTO> passengers, double estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport, DeductionDTO rejection, List<RouteDTO> locations) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengers;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.rejection = rejection;
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public UserDTO getDriver() {
        return driver;
    }

    public void setDriver(UserDTO driver) {
        this.driver = driver;
    }

    public List<UserDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<UserDTO> passengers) {
        this.passengers = passengers;
    }

    public double getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(double estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public DeductionDTO getRejection() {
        return rejection;
    }

    public void setRejection(DeductionDTO rejection) {
        this.rejection = rejection;
    }

    public List<RouteDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RouteDTO> locations) {
        this.locations = locations;
    }


}
