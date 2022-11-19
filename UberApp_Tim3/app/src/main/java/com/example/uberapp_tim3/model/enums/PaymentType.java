package com.example.uberapp_tim3.model.enums;

import com.example.uberapp_tim3.model.drives.Payment;

import java.util.ArrayList;

public enum PaymentType {
    CARD,
    PAYPAL,
    BITCOIN;

    private ArrayList<Payment> payments;

    PaymentType() {
        this.payments = new ArrayList<>();
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    PaymentType(ArrayList<Payment> payments) {
        this.payments = payments;
    }
}
