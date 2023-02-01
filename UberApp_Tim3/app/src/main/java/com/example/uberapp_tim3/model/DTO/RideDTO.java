package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RideDTO implements Parcelable {
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

    public static final Creator<RideDTO> CREATOR = new Creator<RideDTO>() {
        @Override
        public RideDTO createFromParcel(Parcel in) {
            try {
                return new RideDTO(in);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public RideDTO[] newArray(int size) {
            return new RideDTO[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    public RideDTO(PassengerRideDTO passengerRide) {
        this.id = passengerRide.getId();
        this.babyTransport = passengerRide.isBabyTransport();
        this.driver = new RideUserDTO(passengerRide.getDriver());
        this.petTransport = passengerRide.isPetTransport();
        this.locations = passengerRide.getLocations();
        this.startTime = passengerRide.getStartTime();
        this.endTime = passengerRide.getEndTime();
        this.passengers = new ArrayList<>();
        for (UserDTO pass : passengerRide.getPassengers()) {
            this.passengers.add(new RideUserDTO(pass));
        }
        this.vehicleType = passengerRide.getVehicleType();
        this.totalCost = passengerRide.getTotalCost();
        this.scheduledTime = passengerRide.getStartTime();
        this.estimatedTimeInMinutes = passengerRide.getEstimatedTimeInMinutes();
        this.rejection = passengerRide.getRejection();




    }

    protected RideDTO(Parcel in) throws ParseException {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        locations = in.createTypedArrayList(RouteDTO.CREATOR);
        rejection = in.readParcelable(DeductionDTO.class.getClassLoader());
        long dateValue = in.readLong();
        if(dateValue != -1) {
            startTime = new Date(dateValue);
        } else {
            startTime = null;
        }
        dateValue = in.readLong();
        if(dateValue != -1) {
            endTime = new Date(dateValue);
        } else {
            endTime = null;
        }

        totalCost = in.readDouble();
        driver = in.readParcelable(RideUserDTO.class.getClassLoader());
        passengers = in.createTypedArrayList(RideUserDTO.CREATOR);
        estimatedTimeInMinutes = in.readDouble();
        vehicleType = in.readString();
        babyTransport = in.readByte() != 0;
        petTransport = in.readByte() != 0;
        status = in.readString();
        dateValue = in.readLong();
        if(dateValue != -1) {
            scheduledTime = new Date(dateValue);
        } else {
            scheduledTime = null;
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }

        parcel.writeTypedList(locations);
        parcel.writeParcelable(rejection, i);
        if(startTime != null) {
            parcel.writeLong(startTime.getTime());
        } else {
            parcel.writeLong(-1);
        }
        if(endTime != null) {
            parcel.writeLong(endTime.getTime());
        } else {
            parcel.writeLong(-1);
        }

        parcel.writeDouble(totalCost);
        parcel.writeParcelable(driver, i);
        parcel.writeTypedList(passengers);
        parcel.writeDouble(estimatedTimeInMinutes);
        parcel.writeString(vehicleType);
        parcel.writeByte((byte) (babyTransport ? 1 : 0));
        parcel.writeByte((byte) (petTransport ? 1 : 0));
        parcel.writeString(status);
        if(scheduledTime != null) {
            parcel.writeLong(scheduledTime.getTime());
        } else {
            parcel.writeLong(-1);
        }

    }

    @Override
    public String toString() {
        return "RideDTO{" +
                "id=" + id +
                ", locations=" + locations +
                ", rejection=" + rejection +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalCost=" + totalCost +
                ", driver=" + driver +
                ", passengers=" + passengers +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", vehicleType='" + vehicleType + '\'' +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                ", status='" + status + '\'' +
                ", scheduledTime=" + scheduledTime +
                '}';
    }
}
