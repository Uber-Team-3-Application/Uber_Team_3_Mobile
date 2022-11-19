package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.enums.PaymentType;
import com.example.uberapp_tim3.model.users.Passenger;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private LocalDateTime timeOfPayment;
    private double price;
    private PaymentType paymentType;
    private Passenger passenger;
    private Drive drive;

    public Payment() {
        this.id = 0;
        this.price = 0.0;

    }

    public Payment(int id, LocalDateTime timeOfPayment, double price, PaymentType paymentType, Passenger passenger, Drive drive) {
        this.id = id;
        this.timeOfPayment = timeOfPayment;
        this.price = price;
        this.paymentType = paymentType;
        this.passenger = passenger;
        this.drive = drive;
    }

    public Payment(LocalDateTime timeOfPayment, double price, PaymentType paymentType, Passenger passenger, Drive drive) {
        this.id = 0;
        this.timeOfPayment = timeOfPayment;
        this.price = price;
        this.paymentType = paymentType;
        this.passenger = passenger;
        this.drive = drive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(LocalDateTime timeOfPayment) {
        this.timeOfPayment = timeOfPayment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }
}
