package com.example.uberapp_tim3.model.users;


import android.media.Image;

import com.example.uberapp_tim3.model.drives.Ride;
import com.example.uberapp_tim3.model.drives.FavouriteRoute;

import java.util.HashSet;
import java.util.Set;

public class Passenger extends User {

    private Set<FavouriteRoute> favouriteRoutes;
    private Set<Ride> rides;

    public Passenger() {
        super();
        this.favouriteRoutes = new HashSet<>();
        this.rides = new HashSet<>();
    }


    public Passenger(Set<FavouriteRoute> favouriteRoutes, Set<Ride> rides) {
        this.favouriteRoutes = favouriteRoutes;
        this.rides = rides;
    }

    public Passenger(Long id, String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress, String password, boolean isBlocked, boolean isActive, String address, Set<FavouriteRoute> favouriteRoutes, Set<Ride> rides) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, isActive, address);
        this.favouriteRoutes = favouriteRoutes;
        this.rides = rides;
    }

    public Set<FavouriteRoute> getFavouriteRoutes() {
        return favouriteRoutes;
    }

    public void setFavouriteRoutes(Set<FavouriteRoute> favouriteRoutes) {
        this.favouriteRoutes = favouriteRoutes;
    }

    public Set<Ride> getRides() {
        return rides;
    }

    public void setRides(Set<Ride> rides) {
        this.rides = rides;
    }
}
