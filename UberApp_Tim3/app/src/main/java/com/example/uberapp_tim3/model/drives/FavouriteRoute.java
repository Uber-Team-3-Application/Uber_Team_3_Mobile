package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.users.Passenger;

public class FavouriteRoute {
    private int id;
    private Route route;
    private Passenger passenger;

    public FavouriteRoute() {
        super();
        this.id = 0;

    }

    public FavouriteRoute(Route route, Passenger passenger) {
        this.id = 0;
        this.route = route;
        this.passenger = passenger;
    }

    public FavouriteRoute(int id, Route route, Passenger passenger) {
        this.id = id;
        this.route = route;
        this.passenger = passenger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
