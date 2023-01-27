package com.example.uberapp_tim3.model.DTO;

public class DriversStatisticsDTO {
    int type;
    int accepted;
    int rejected;
    double income;
    int hours;

    public DriversStatisticsDTO() {
    }

    public DriversStatisticsDTO(int type, int accepted, int rejected, double income, int hours) {
        this.type = type;
        this.accepted = accepted;
        this.rejected = rejected;
        this.income = income;
        this.hours = hours;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getRejected() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}