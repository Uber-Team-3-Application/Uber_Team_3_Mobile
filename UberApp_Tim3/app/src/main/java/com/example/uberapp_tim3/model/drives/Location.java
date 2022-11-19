package com.example.uberapp_tim3.model.drives;



import com.example.uberapp_tim3.model.vehicle.Vehicle;

import java.util.ArrayList;

public class Location {
    private int id;
    private double latitude;
    private double longitude;
    ArrayList<Route> departureRoutes; // rute gde je this polaziste
    ArrayList<Route> destinationRoutes; // rute gde je this odrediste
    private Vehicle vehicle;

    public Location() {
        super();
        this.id = 0;
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.departureRoutes = new ArrayList<>();
        this.destinationRoutes = new ArrayList<>();
    }


    public Location(int id, double latitude, double longitude, ArrayList<Route> departureRoutes,
                    ArrayList<Route> destinationRoutes, Vehicle vehicle) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.departureRoutes = departureRoutes;
        this.destinationRoutes = destinationRoutes;
        this.vehicle = vehicle;
    }

    public Location(double latitude, double longitude, ArrayList<Route> departureRoutes,
                    ArrayList<Route> destinationRoutes, Vehicle vehicle) {
        this.id = 0;
        this.latitude = latitude;
        this.longitude = longitude;
        this.departureRoutes = departureRoutes;
        this.destinationRoutes = destinationRoutes;
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Route> getDepartureRoutes() {
        return departureRoutes;
    }

    public void setDepartureRoutes(ArrayList<Route> departureRoutes) {
        this.departureRoutes = departureRoutes;
    }

    public ArrayList<Route> getDestinationRoutes() {
        return destinationRoutes;
    }

    public void setDestinationRoutes(ArrayList<Route> destinationRoutes) {
        this.destinationRoutes = destinationRoutes;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
