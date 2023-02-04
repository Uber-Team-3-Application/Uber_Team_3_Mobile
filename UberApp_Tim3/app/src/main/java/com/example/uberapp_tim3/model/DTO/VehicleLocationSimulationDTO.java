package com.example.uberapp_tim3.model.DTO;

public class VehicleLocationSimulationDTO {
    private Long id;
    private String licensePlateNumber;
    private double latitude;
    private double longitude;

    public VehicleLocationSimulationDTO() {
    }

    public VehicleLocationSimulationDTO(Long id, String licensePlateNumber, double latitude, double longitude) {
        this.id = id;
        this.licensePlateNumber = licensePlateNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "VehicleLocationSimulationDTO{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
