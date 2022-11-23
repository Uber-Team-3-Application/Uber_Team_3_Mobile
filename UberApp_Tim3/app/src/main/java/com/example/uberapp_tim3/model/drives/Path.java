package com.example.uberapp_tim3.model.drives;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Path {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalTime approximateTime;
    private double kilometers;
    private double price;
    private int ordinal;
    private Route route;

    public Path() {
        super();
        this.id = 0;
        this.kilometers = 0.0;
        this.price = 0.0;
        this.ordinal = 0;
    }

    public Path(LocalDateTime startTime, LocalDateTime endTime, LocalTime approximateTime,
                double kilometers, double price, int ordinal, Route route) {
        this.id = 0;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approximateTime = approximateTime;
        this.kilometers = kilometers;
        this.price = price;
        this.ordinal = ordinal;
        this.route = route;
    }

    public Path(int id, LocalDateTime startTime, LocalDateTime endTime, LocalTime approximateTime,
                double kilometers, double price, int ordinal, Route route) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approximateTime = approximateTime;
        this.kilometers = kilometers;
        this.price = price;
        this.ordinal = ordinal;
        this.route = route;
    }

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

    public LocalTime getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(LocalTime approximateTime) {
        this.approximateTime = approximateTime;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
