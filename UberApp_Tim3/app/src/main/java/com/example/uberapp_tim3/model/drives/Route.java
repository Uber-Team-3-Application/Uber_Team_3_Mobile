package com.example.uberapp_tim3.model.drives;

import java.util.ArrayList;

public class Route {
    private int id;
    private ArrayList<FavouriteRoute> favouriteRoutes;
    private ArrayList<Path> paths;
    private Location startingPoint;
    private Location endPoint;


    public Route() {
        this.id = 0;
        this.favouriteRoutes = new ArrayList<>();
        this.paths = new ArrayList<>();
    }

    public Route(int id, ArrayList<FavouriteRoute> favouriteRoutes, ArrayList<Path> paths, Location startingPoint, Location endPoint) {
        this.id = id;
        this.favouriteRoutes = favouriteRoutes;
        this.paths = paths;
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
    }

    public Route(ArrayList<FavouriteRoute> favouriteRoutes, ArrayList<Path> paths, Location startingPoint, Location endPoint) {
        this.id = 0;
        this.favouriteRoutes = favouriteRoutes;
        this.paths = paths;
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<FavouriteRoute> getFavouriteRoutes() {
        return favouriteRoutes;
    }

    public void setFavouriteRoutes(ArrayList<FavouriteRoute> favouriteRoutes) {
        this.favouriteRoutes = favouriteRoutes;
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
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
