package com.example.uberapp_tim3.model.DTO;

import java.util.List;

public class FavouriteRideDTO {

    private Long id;
    private String favoriteName;
    private List<LocationDTO> locations;
    private List<RideUserDTO> passengers;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;


    public FavouriteRideDTO() {
    }

    public FavouriteRideDTO(Long id, String favoriteName, List<LocationDTO> locations, List<RideUserDTO> passengers, String vehicleType, boolean babyTransport, boolean petTransport) {
        this.id = id;
        this.favoriteName = favoriteName;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }

    public List<RideUserDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<RideUserDTO> passengers) {
        this.passengers = passengers;
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
}
