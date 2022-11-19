package com.example.uberapp_tim3.model.users;


import android.media.Image;

import com.example.uberapp_tim3.model.drives.Drive;
import com.example.uberapp_tim3.model.drives.FavouriteRoute;
import com.example.uberapp_tim3.model.items.Message;
import com.example.uberapp_tim3.model.items.PanicButton;
import com.example.uberapp_tim3.model.drives.Payment;
import com.example.uberapp_tim3.model.drives.Rejection;
import com.example.uberapp_tim3.model.drives.Review;

import java.util.ArrayList;

public class Passenger extends User {

    private ArrayList<FavouriteRoute> favouriteRoutes;
    private ArrayList<Payment> payments;
    private ArrayList<Review> reviews;
    private ArrayList<Drive> drives;

    public Passenger() {
        super();
        this.favouriteRoutes = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.drives = new ArrayList<>();
    }

    public Passenger(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress,
                     String password, boolean isBlocked, String address, ArrayList<Message> receiverInbox,
                     ArrayList<Message> senderInbox, ArrayList<PanicButton> panicButtons,
                     ArrayList<Rejection> rejections, ArrayList<FavouriteRoute> favouriteRoutes,
                     ArrayList<Payment> payments, ArrayList<Review> reviews, ArrayList<Drive> drives) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons, rejections);
        this.favouriteRoutes = favouriteRoutes;
        this.payments = payments;
        this.reviews = reviews;
        this.drives = drives;
    }

    public Passenger(int id, String name, String lastName, Image profilePicture, String phoneNumber,
                     String emailAddress, String password, boolean isBlocked, String address,
                     ArrayList<Message> receiverInbox, ArrayList<Message> senderInbox,
                     ArrayList<PanicButton> panicButtons, ArrayList<Rejection> rejections,
                     ArrayList<FavouriteRoute> favouriteRoutes, ArrayList<Payment> payments,
                     ArrayList<Review> reviews, ArrayList<Drive> drives) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons, rejections);
        this.favouriteRoutes = favouriteRoutes;
        this.payments = payments;
        this.reviews = reviews;
        this.drives = drives;
    }

    public ArrayList<FavouriteRoute> getFavouriteRoutes() {
        return favouriteRoutes;
    }

    public void setFavouriteRoutes(ArrayList<FavouriteRoute> favouriteRoutes) {
        this.favouriteRoutes = favouriteRoutes;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Drive> getDrives() {
        return drives;
    }

    public void setDrives(ArrayList<Drive> drives) {
        this.drives = drives;
    }
}
