package com.example.uberapp_tim3.model.DTO;

public class VehicleLocationWithAvailabilityDTO {

    private Long id;
    private boolean isAvailable;
    private String address;
    private double latitude;
    private double longitude;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public VehicleLocationWithAvailabilityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleLocationWithAvailabilityDTO(Long id, boolean isAvailable, String address, double latitude, double longitude) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
