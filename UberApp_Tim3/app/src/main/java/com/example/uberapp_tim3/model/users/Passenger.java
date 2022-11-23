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
    private ArrayList<Drive> drives;

    public Passenger() {
        super();
        this.favouriteRoutes = new ArrayList<>();
        this.drives = new ArrayList<>();
    }

    public Passenger(String name, String lastName, Image profilePicture, String phoneNumber, String emailAddress,
                     String password, boolean isBlocked, String address, ArrayList<Message> receiverInbox,
                     ArrayList<Message> senderInbox, ArrayList<PanicButton> panicButtons,
                     ArrayList<FavouriteRoute> favouriteRoutes,
                     ArrayList<Drive> drives) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons);
        this.favouriteRoutes = favouriteRoutes;
        this.drives = drives;
    }

    public Passenger(int id, String name, String lastName, Image profilePicture, String phoneNumber,
                     String emailAddress, String password, boolean isBlocked, String address,
                     ArrayList<Message> receiverInbox, ArrayList<Message> senderInbox,
                     ArrayList<PanicButton> panicButtons,
                     ArrayList<FavouriteRoute> favouriteRoutes,
                     ArrayList<Drive> drives) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons);
        this.favouriteRoutes = favouriteRoutes;
        this.drives = drives;
    }

    public ArrayList<FavouriteRoute> getFavouriteRoutes() {
        return favouriteRoutes;
    }

    public void setFavouriteRoutes(ArrayList<FavouriteRoute> favouriteRoutes) {
        this.favouriteRoutes = favouriteRoutes;
    }


    public ArrayList<Drive> getDrives() {
        return drives;
    }

    public void setDrives(ArrayList<Drive> drives) {
        this.drives = drives;
    }
}
