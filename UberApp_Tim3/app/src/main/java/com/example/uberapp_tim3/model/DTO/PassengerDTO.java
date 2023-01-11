package com.example.uberapp_tim3.model.DTO;

import com.example.uberapp_tim3.model.users.Driver;
import com.example.uberapp_tim3.model.users.Passenger;

public class PassengerDTO {

    private Long id;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;
    private String password;
    private boolean isConfirmedMail;
    private double ammountOfMoney;


    public PassengerDTO(){}

    public PassengerDTO(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email, String address, String password, boolean isConfirmedMail, double ammountOfMoney) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
        this.isConfirmedMail = isConfirmedMail;
        this.ammountOfMoney = ammountOfMoney;
    }

    public PassengerDTO(Passenger passenger){
        this.id = passenger.getId();
        this.name = passenger.getName();
        this.surname = passenger.getLastName();
        this.profilePicture = passenger.getProfilePicture();
        this.telephoneNumber = passenger.getPhoneNumber();
        this.email = passenger.getEmailAddress();
        this.address = passenger.getAddress();
        this.password = passenger.getPassword();

    }

    public boolean isConfirmedMail() {
        return isConfirmedMail;
    }

    public void setConfirmedMail(boolean confirmedMail) {
        isConfirmedMail = confirmedMail;
    }

    public double getAmmountOfMoney() {
        return ammountOfMoney;
    }

    public void setAmmountOfMoney(double ammountOfMoney) {
        this.ammountOfMoney = ammountOfMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
