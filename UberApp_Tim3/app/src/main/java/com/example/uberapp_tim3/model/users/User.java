package com.example.uberapp_tim3.model.users;

import android.media.Image;

import com.example.uberapp_tim3.model.items.Message;
import com.example.uberapp_tim3.model.items.PanicButton;
import com.example.uberapp_tim3.model.drives.Rejection;

import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private String lastName;
    private Image profilePicture;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private boolean isBlocked;
    private String address;
    private ArrayList<Message> receiverInbox;
    private ArrayList<Message> senderInbox;
    private ArrayList<PanicButton> panicButtons;



    public User() {
        this.id  = 0;
        this.senderInbox = new ArrayList<>();
        this.receiverInbox = new ArrayList<>();
        this.senderInbox = new ArrayList<>();
        this.isBlocked = false;
    }

    public User(String name, String lastName, Image profilePicture, String phoneNumber,
                String emailAddress, String password, boolean isBlocked, String address,
                ArrayList<Message> receiverInbox, ArrayList<Message> senderInbox,
                ArrayList<PanicButton> panicButtons) {
        this.id = 0;
        this.name = name;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isBlocked = isBlocked;
        this.address = address;
        this.receiverInbox = receiverInbox;
        this.senderInbox = senderInbox;
        this.panicButtons = panicButtons;
    }

    public User(int id, String name, String lastName, Image profilePicture, String phoneNumber,
                String emailAddress, String password, boolean isBlocked, String address,
                ArrayList<Message> receiverInbox, ArrayList<Message> senderInbox,
                ArrayList<PanicButton> panicButtons) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isBlocked = isBlocked;
        this.address = address;
        this.receiverInbox = receiverInbox;
        this.senderInbox = senderInbox;
        this.panicButtons = panicButtons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Message> getReceiverInbox() {
        return receiverInbox;
    }

    public void setReceiverInbox(ArrayList<Message> receiverInbox) {
        this.receiverInbox = receiverInbox;
    }

    public ArrayList<Message> getSenderInbox() {
        return senderInbox;
    }

    public void setSenderInbox(ArrayList<Message> senderInbox) {
        this.senderInbox = senderInbox;
    }

    public ArrayList<PanicButton> getPanicButtons() {
        return panicButtons;
    }

    public void setPanicButtons(ArrayList<PanicButton> panicButtons) {
        this.panicButtons = panicButtons;
    }


}

