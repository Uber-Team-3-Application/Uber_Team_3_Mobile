package com.example.uberapp_tim3.model.drives;


public class Route {
    private Long id;
    private Location departure;
    private Location destination;
    private double totalKilometers;
    private double price;
    private int orderNumber;

    public Route() {
    }

    public Route(Long id, Location departure, Location destination, double totalKilometers, double price, int orderNumber) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.totalKilometers = totalKilometers;
        this.price = price;
        this.orderNumber = orderNumber;
    }

    public double getTotalKilometers() {
        return totalKilometers;
    }

    public void setTotalKilometers(double totalKilometers) {
        this.totalKilometers = totalKilometers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }
}
