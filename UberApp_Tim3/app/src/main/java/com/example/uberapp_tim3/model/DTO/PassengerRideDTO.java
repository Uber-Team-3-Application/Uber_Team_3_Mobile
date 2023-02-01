package com.example.uberapp_tim3.model.DTO;



import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;


public class PassengerRideDTO implements Parcelable {
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

    PassengerRideDTO() {}

    public PassengerRideDTO(Long id, Date startTime, Date endTime, double totalCost, UserDTO driver, List<UserDTO> passengers, double estimatedTimeInMinutes, String vehicleType, boolean babyTransport, boolean petTransport, DeductionDTO rejection, List<RouteDTO> locations) {
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

    protected PassengerRideDTO(Parcel in) {
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
        rejection = in.readParcelable(DeductionDTO.class.getClassLoader());
        locations = in.createTypedArrayList(RouteDTO.CREATOR);
    }

    public static final Creator<PassengerRideDTO> CREATOR = new Creator<PassengerRideDTO>() {
        @Override
        public PassengerRideDTO createFromParcel(Parcel in) {
            return new PassengerRideDTO(in);
        }

        @Override
        public PassengerRideDTO[] newArray(int size) {
            return new PassengerRideDTO[size];
        }
    };

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

    public List<UserDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<UserDTO> passengers) {
        this.passengers = passengers;
    }

    public List<RouteDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<RouteDTO> locations) {
        this.locations = locations;
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
        parcel.writeParcelable(rejection, i);
        parcel.writeTypedList(locations);
    }

    @Override
    public String toString() {
        return "PassengerRideDTO{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalCost=" + totalCost +
                ", driver=" + driver +
                ", passengers=" + passengers +
                ", estimatedTimeInMinutes=" + estimatedTimeInMinutes +
                ", vehicleType='" + vehicleType + '\'' +
                ", babyTransport=" + babyTransport +
                ", petTransport=" + petTransport +
                ", rejection=" + rejection +
                ", locations=" + locations +
                '}';
    }
}
