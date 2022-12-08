package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.users.Passenger;

public class FavouriteRoute {
    private Long id;
    private Location departure;
    private Location destination;

    public FavouriteRoute() {

    }

    public FavouriteRoute(Long id, Location departure, Location destination) {
        this();
        this.id = id;
        this.departure = departure;
        this.destination = destination;
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
