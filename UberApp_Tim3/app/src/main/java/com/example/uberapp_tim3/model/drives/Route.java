package com.example.uberapp_tim3.model.drives;

import java.util.ArrayList;

public class Route {
    private int id;
    private Location startingPoint;
    private Location endPoint;


    public Route() {
        this.id = 0;
    }

    public Route(int id, Location startingPoint, Location endPoint) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
    }

    public Route(Location startingPoint, Location endPoint) {
        this.id = 0;
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
    }
}
