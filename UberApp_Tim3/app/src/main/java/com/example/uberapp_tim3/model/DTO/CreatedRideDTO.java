package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class CreatedRideDTO implements Parcelable {

    private  Long id;
    private Date startTime;
    private  Date endTime;
    private  double totalCost;
    private RideUserDTO driver;
    private List<RideUserDTO> passengers;
    private  double estimatedTimeInMinutes;
    private  String vehicleType;
    private  boolean babyTransport;
    private  boolean petTransport;
    private  DeductionDTO rejection;
    private  List<RouteDTO> locations;
    private String status;

    protected CreatedRideDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        totalCost = in.readDouble();
        estimatedTimeInMinutes = in.readDouble();
        vehicleType = in.readString();
        babyTransport = in.readByte() != 0;
        petTransport = in.readByte() != 0;
    }

    public static final Creator<DriverRideDTO> CREATOR = new Creator<DriverRideDTO>() {
        @Override
        public DriverRideDTO createFromParcel(Parcel in) {
            return new DriverRideDTO(in);
        }

        @Override
        public DriverRideDTO[] newArray(int size) {
            return new DriverRideDTO[size];
        }
    };

    public CreatedRideDTO() {
    }

    public CreatedRideDTO(Long id, Date startTime, Date endTime, double totalCost, RideUserDTO driver, List<RideUserDTO> passengers, double estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport, DeductionDTO rejection, List<RouteDTO> locations, String status) {
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
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeDouble(totalCost);
        parcel.writeDouble(estimatedTimeInMinutes);
        parcel.writeString(vehicleType);
        parcel.writeByte((byte) (babyTransport ? 1 : 0));
        parcel.writeByte((byte) (petTransport ? 1 : 0));
    }
}
