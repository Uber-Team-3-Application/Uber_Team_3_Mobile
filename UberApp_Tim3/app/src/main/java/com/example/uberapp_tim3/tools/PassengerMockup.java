package com.example.uberapp_tim3.tools;

import com.example.uberapp_tim3.model.Passenger;

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
                false
        ));

        passengers.add(new Passenger(
                "Nemanja",
                "Malesevic",
                null,
                "064123123",
                "nemanjamalesevic@gmail.com",
                "nemanja123",
                false
        ));
        passengers.add(new Passenger(
                "Nikola",
                "Jokic",
                null,
                "064123123",
                "jokicsmokic@gmail.com",
                "jokic123",
                false
        ));
        return passengers;
    }
}
