package com.example.uberapp_tim3.model.DTO;

import java.util.Date;
import java.util.List;

public class RideDTO {
    private Long id;
    private List<RouteDTO> locations;
    private DeductionDTO rejection;
    private Date startTime;
    private Date endTime;
    private double totalCost;
    private RideUserDTO driver;
    private List<RideUserDTO> passengers;
    private double estimatedTimeInMinutes;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private String status;
    private Date scheduledTime;

    public RideDTO() {
    }

    public RideDTO(Long id, List<RouteDTO> locations, DeductionDTO rejection, Date startTime, Date endTime, double totalCost, RideUserDTO driver, List<RideUserDTO> passengers, double estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport, String status, Date scheduledTime) {
        this.id = id;
        this.locations = locations;
        this.rejection = rejection;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengers;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.status = status;
        this.scheduledTime = scheduledTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RouteDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RouteDTO> locations) {
        this.locations = locations;
    }

    public DeductionDTO getRejection() {
        return rejection;
    }

    public void setRejection(DeductionDTO rejection) {
        this.rejection = rejection;
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

    public RideUserDTO getDriver() {
        return driver;
    }

    public void setDriver(RideUserDTO driver) {
        this.driver = driver;
    }

    public List<RideUserDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<RideUserDTO> passengers) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
