package com.example.uberapp_tim3.model.DTO;

import com.example.uberapp_tim3.model.users.Driver;

public class CreatedDriverDTO {

    private Long id;
    private String name;
    private String surname;
    private String profilePicture;
    private String telephoneNumber;
    private String email;
    private String address;


    public CreatedDriverDTO(Driver driver){
        this.id = driver.getId();
        this.name = driver.getName();
        this.surname = driver.getLastName();
        this.profilePicture = driver.getProfilePicture();
        this.telephoneNumber = driver.getPhoneNumber();
        this.email = driver.getEmailAddress();
        this.address = driver.getAddress();

    }

    public CreatedDriverDTO(Long id, String name, String surname, String profilePicture, String telephoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
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
}
