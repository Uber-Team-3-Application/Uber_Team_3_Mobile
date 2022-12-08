package com.example.uberapp_tim3.model.users;

import com.example.uberapp_tim3.model.users.Driver;

import java.time.LocalTime;

public class WorkingHours {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private Driver driver;

    public WorkingHours(Long id, LocalTime startTime, LocalTime endTime, Driver driver) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driver = driver;
    }


    public WorkingHours(LocalTime startTime, LocalTime endTime, Driver driver) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.driver = driver;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
