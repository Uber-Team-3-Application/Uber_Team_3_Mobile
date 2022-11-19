package com.example.uberapp_tim3.tools;

import com.example.uberapp_tim3.model.mockup.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerMockup {

    public static List<Passenger> getPassengers(){

        ArrayList<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(
                "Marko",
                "Markovic",
                null,
                "064123123",
                "markomarkovic@gmail.com",
                "markoni123",
                false,
                "Lazina 2, Novi Sad"
        ));

        passengers.add(new Passenger(
                "Nemanja",
                "Malesevic",
                null,
                "064123123",
                "nemanjamalesevic@gmail.com",
                "nemanja123",
                false,
                "Lazina 4, Novi Sad"
        ));
        passengers.add(new Passenger(
                "Nikola",
                "Jokic",
                null,
                "064123123",
                "jokicsmokic@gmail.com",
                "jokic123",
                false,
                "Aurelija 14, Novi Sad"
        ));
        return passengers;
    }
}