package com.example.uberapp_tim3.model.DTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class CreateRideDTO {

    private Set<PassengerEmailDTO> passengers;
    private LinkedHashSet<RouteDTO> locations;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private String scheduledTime;

    public CreateRideDTO() {
        super();
    }

    public CreateRideDTO(Set<PassengerEmailDTO> passengers, LinkedHashSet<RouteDTO> locations, String vehicleType, boolean babyTransport, boolean petTransport, String scheduledTime) {
        this.passengers = passengers;
        this.locations = locations;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.scheduledTime = scheduledTime;
    }

    public LinkedHashSet<RouteDTO> getLocations() {
        return locations;
    }
    public void setLocations(LinkedHashSet<RouteDTO> locations) {
        this.locations = locations;
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
    public Set<PassengerEmailDTO> getPassengers() {
        return passengers;
    }
    public void setPassengers(Set<PassengerEmailDTO> passengers) {
        this.passengers = passengers;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
