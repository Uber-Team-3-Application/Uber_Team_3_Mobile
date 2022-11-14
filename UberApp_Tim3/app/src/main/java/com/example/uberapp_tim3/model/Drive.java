package com.example.uberapp_tim3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Drive implements Parcelable  {

    private int rate;
    private List<Passenger> passengerList;
    private HashMap<Passenger, String> comments;
    private String startDrive;
    private String endDrive;
    private int numberOfPassengers;
    private String relation;
    private double km;
    private double price;

    protected Drive(Parcel in) {
        rate = in.readInt();
        startDrive = in.readString();
        endDrive = in.readString();
        numberOfPassengers = in.readInt();
        relation = in.readString();
        km = in.readDouble();
        price = in.readDouble();
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

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(ArrayList<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public HashMap<Passenger, String> getComments() {
        return comments;
    }

    public void setComments(HashMap<Passenger, String> comments) {
        this.comments = comments;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Drive(int rate, String startDrive, String endDrive, int numberOfPassengers,
                 String relation, double km, double price, HashMap<Passenger, String> comments,
                 List<Passenger> passengerList) {
        this.rate = rate;
        this.startDrive = startDrive;
        this.endDrive = endDrive;
        this.comments = comments;
        this.numberOfPassengers = numberOfPassengers;
        this.relation = relation;
        this.km = km;
        this.passengerList = passengerList;
        this.price = price;
    }


    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


    public String getStartDrive() {
        return startDrive;
    }

    public void setStartDrive(String startDrive) {
        this.startDrive = startDrive;
    }

    public String getEndDrive() {
        return endDrive;
    }

    public void setEndDrive(String endDrive) {
        this.endDrive = endDrive;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getrelation() {
        return relation;
    }

    public void setrelation(String relation) {
        this.relation = relation;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(rate);
        parcel.writeString(startDrive);
        parcel.writeString(endDrive);
        parcel.writeInt(numberOfPassengers);
        parcel.writeString(relation);
        parcel.writeDouble(km);
        parcel.writeDouble(price);
    }
}
