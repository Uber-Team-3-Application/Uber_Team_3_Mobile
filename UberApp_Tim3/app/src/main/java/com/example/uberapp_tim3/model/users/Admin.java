package com.example.uberapp_tim3.model.users;

import android.media.Image;

import com.example.uberapp_tim3.model.items.Message;
import com.example.uberapp_tim3.model.items.PanicButton;
import com.example.uberapp_tim3.model.drives.Rejection;

import java.util.ArrayList;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String name, String lastName, Image profilePicture, String phoneNumber,
                 String emailAddress, String password, boolean isBlocked, String address,
                 ArrayList<Message> receiverInbox, ArrayList<Message> senderInbox,
                 ArrayList<PanicButton> panicButtons, ArrayList<Rejection> rejections) {
        super(name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons, rejections);
    }

    public Admin(int id, String name, String lastName, Image profilePicture, String phoneNumber,
                 String emailAddress, String password, boolean isBlocked, String address,
                 ArrayList<Message> receiverInbox, ArrayList<Message> senderInbox,
                 ArrayList<PanicButton> panicButtons, ArrayList<Rejection> rejections) {
        super(id, name, lastName, profilePicture, phoneNumber, emailAddress, password, isBlocked, address, receiverInbox, senderInbox, panicButtons, rejections);
    }

    @Override
    public void setBlocked(boolean blocked) {
        super.setBlocked(false);
    }
}
