package com.example.uberapp_tim3.model.drives;

import com.example.uberapp_tim3.model.users.Passenger;

public class FavouriteRoute {
    private int id;
    private Route route;

    public FavouriteRoute() {
        super();
        this.id = 0;

    }

    public FavouriteRoute(Route route) {
        this.id = 0;
        this.route = route;
    }

    public FavouriteRoute(int id, Route route) {
        this.id = id;
        this.route = route;
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

}
