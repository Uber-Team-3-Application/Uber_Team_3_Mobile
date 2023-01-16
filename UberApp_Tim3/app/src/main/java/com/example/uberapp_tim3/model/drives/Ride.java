package com.example.uberapp_tim3.model.drives;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.uberapp_tim3.model.enums.DriveStatus;
import com.example.uberapp_tim3.model.users.Driver;
import com.example.uberapp_tim3.model.users.Passenger;
import com.example.uberapp_tim3.model.vehicle.TypeOfVehicle;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;

public class Ride implements Parcelable  {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
    private LocalTime approximateTime;
    private boolean babyTransport;
    private boolean petTransport;
    private Driver driver;
    private TypeOfVehicle typeOfVehicle;
    private DriveStatus driveStatus;
    private Set<Passenger> passengers;
    private PanicButton panicButton;
    private Rejection rejections;
    private Set<Route> routes;
    private Set<Review> reviews;
    private LocalTime totalTime;


    public Ride(LocalDateTime startTime, LocalDateTime endTime, double price, LocalTime approximateTime, boolean babyTransport, boolean petTransport, Driver driver, TypeOfVehicle typeOfVehicle, DriveStatus driveStatus, Set<Passenger> passengers, PanicButton panicButton, Rejection rejections, Set<Route> routes, Set<Review> reviews, LocalTime totalTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.approximateTime = approximateTime;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.driver = driver;
        this.typeOfVehicle = typeOfVehicle;
        this.driveStatus = driveStatus;
        this.passengers = passengers;
        this.panicButton = panicButton;
        this.rejections = rejections;
        this.routes = routes;
        this.reviews = reviews;
        this.totalTime = totalTime;
    }

    public Ride(Long id, LocalDateTime startTime, LocalDateTime endTime, double price, LocalTime approximateTime, boolean babyTransport, boolean petTransport, Driver driver, TypeOfVehicle typeOfVehicle, DriveStatus driveStatus, Set<Passenger> passengers, PanicButton panicButton, Rejection rejections, Set<Route> routes, Set<Review> reviews, LocalTime totalTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.approximateTime = approximateTime;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.driver = driver;
        this.typeOfVehicle = typeOfVehicle;
        this.driveStatus = driveStatus;
        this.passengers = passengers;
        this.panicButton = panicButton;
        this.rejections = rejections;
        this.routes = routes;
        this.reviews = reviews;
        this.totalTime = totalTime;
    }

    public LocalTime getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(LocalTime totalTime) {
        this.totalTime = totalTime;
    }

    public Ride() {
        this.passengers = new HashSet<>();
        this.routes = new HashSet<>();
        this.reviews = new HashSet<>();
    }



    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    protected Ride(Parcel in) {
        id = Long.parseLong(Integer.toString(in.readInt()));
        price = in.readDouble();
        babyTransport = in.readByte() != 0;
        petTransport = in.readByte() != 0;
    }

    public static final Creator<Ride> CREATOR = new Creator<Ride>() {
        @Override
        public Ride createFromParcel(Parcel in) {
            return new Ride(in);
        }

        @Override
        public Ride[] newArray(int size) {
            return new Ride[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id.intValue());
        parcel.writeDouble(price);
        parcel.writeByte((byte) (babyTransport ? 1 : 0));
        parcel.writeByte((byte) (petTransport ? 1 : 0));
    }

    public int getId() {
        return id.intValue();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalTime getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(LocalTime approximateTime) {
        this.approximateTime = approximateTime;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public TypeOfVehicle getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(TypeOfVehicle typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public DriveStatus getDriveStatus() {
        return driveStatus;
    }

    public void setDriveStatus(DriveStatus driveStatus) {
        this.driveStatus = driveStatus;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Rejection getRejections() {
        return rejections;
    }

    public void setRejections(Rejection rejections) {
        this.rejections = rejections;
    }

    public PanicButton getPanicButton() {
        return panicButton;
    }

    public void setPanicButton(PanicButton panicButton) {
        this.panicButton = panicButton;
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}

