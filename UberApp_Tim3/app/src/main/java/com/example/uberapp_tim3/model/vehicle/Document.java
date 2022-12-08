package com.example.uberapp_tim3.model.vehicle;

import com.example.uberapp_tim3.model.users.Driver;

public class Document {
    private Long id;
    private String name;
    private String picture;
    private Driver driver;

    public Document(){

    }
    public Document(Long id, String name, String picture, Driver driver) {
        this();
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
