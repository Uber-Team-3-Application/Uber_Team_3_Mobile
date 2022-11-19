package com.example.uberapp_tim3.model.drives;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.uberapp_tim3.enums.DriveStatus;
import com.example.uberapp_tim3.model.items.Message;
import com.example.uberapp_tim3.model.items.PanicButton;
import com.example.uberapp_tim3.model.users.Driver;
import com.example.uberapp_tim3.model.users.Passenger;
import com.example.uberapp_tim3.model.vehicle.TypeOfVehicle;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Drive implements Parcelable  {

    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
    private LocalTime approximateTime;
    private boolean babyTransport;
    private boolean petTransport;
    private boolean splitFare;
    private ArrayList<Driver> drivers;
    private TypeOfVehicle typeOfVehicle;
    private DriveStatus driveStatus;
    private ArrayList<Passenger> passengers;
    private ArrayList<Message> messages;
    private PanicButton panicButton;
    private ArrayList<Rejection> rejections;
    private ArrayList<Path> paths;
    private ArrayList<Payment> payments;
    private ArrayList<Review> reviews;

    public Drive() {
        this.passengers = new ArrayList<>();
        this.drivers = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.rejections = new ArrayList<>();
        this.paths = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Drive(int id, LocalDateTime startTime, LocalDateTime endTime, double price,
                 LocalTime approximateTime, boolean babyTransport, boolean petTransport,
                 boolean splitFare, ArrayList<Driver> drivers, TypeOfVehicle typeOfVehicle,
                 DriveStatus driveStatus, ArrayList<Passenger> passengers, ArrayList<Message> messages,
                 PanicButton panicButton, ArrayList<Rejection> rejections, ArrayList<Path> paths,
                 ArrayList<Payment> payments, ArrayList<Review> reviews) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.approximateTime = approximateTime;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.splitFare = splitFare;
        this.drivers = drivers;
        this.typeOfVehicle = typeOfVehicle;
        this.driveStatus = driveStatus;
        this.passengers = passengers;
        this.messages = messages;
        this.panicButton = panicButton;
        this.rejections = rejections;
        this.paths = paths;
        this.payments = payments;
        this.reviews = reviews;
    }

    public Drive(LocalDateTime startTime, LocalDateTime endTime, double price,
                 LocalTime approximateTime, boolean babyTransport, boolean petTransport,
                 boolean splitFare, ArrayList<Driver> drivers, TypeOfVehicle typeOfVehicle,
                 DriveStatus driveStatus, ArrayList<Passenger> passengers, ArrayList<Message> messages,
                 PanicButton panicButton, ArrayList<Rejection> rejections, ArrayList<Path> paths,
                 ArrayList<Payment> payments, ArrayList<Review> reviews) {
        this.id = 0;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.approximateTime = approximateTime;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.splitFare = splitFare;
        this.drivers = drivers;
        this.typeOfVehicle = typeOfVehicle;
        this.driveStatus = driveStatus;
        this.passengers = passengers;
        this.messages = messages;
        this.panicButton = panicButton;
        this.rejections = rejections;
        this.paths = paths;
        this.payments = payments;
        this.reviews = reviews;
    }


    protected Drive(Parcel in) {
        id = in.readInt();
        price = in.readDouble();
        babyTransport = in.readByte() != 0;
        petTransport = in.readByte() != 0;
        splitFare = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(price);
        dest.writeByte((byte) (babyTransport ? 1 : 0));
        dest.writeByte((byte) (petTransport ? 1 : 0));
        dest.writeByte((byte) (splitFare ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Drive> CREATOR = new Creator<Drive>() {
        @Override
        public Drive createFromParcel(Parcel in) {
            return new Drive(in);
        }

        @Override
        public Drive[] newArray(int size) {
            return new Drive[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isSplitFare() {
        return splitFare;
    }

    public void setSplitFare(boolean splitFare) {
        this.splitFare = splitFare;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
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

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public PanicButton getPanicButton() {
        return panicButton;
    }

    public void setPanicButton(PanicButton panicButton) {
        this.panicButton = panicButton;
    }

    public ArrayList<Rejection> getRejections() {
        return rejections;
    }

    public void setRejections(ArrayList<Rejection> rejections) {
        this.rejections = rejections;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }
}
