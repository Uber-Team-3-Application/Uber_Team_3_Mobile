package com.example.uberapp_tim3.tools;

import android.annotation.SuppressLint;

import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.model.mockup.Driver;
import com.example.uberapp_tim3.model.mockup.Passenger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DrivesMockUp {

    public static List<Drive> getDrives()  {
        ArrayList<Drive> drives = new ArrayList<>();
        HashMap<Passenger, String> comments = new HashMap<>();
        for (Passenger passenger : PassengerMockup.getPassengers()) {
            comments.put(passenger, "Voznja je bila solidna");
        }

        drives.add(
                new Drive(4,
                        "26.10.2022 12:34",
                        "26.10.2022 13:00",
                        2,
                        "Rajfazenova 12, Novi Sad - Radnicka 3, Novi Sad",
                        128.0,
                        6500.0,
                        comments,
                        PassengerMockup.getPassengers()
                )
        );

        drives.add(
                new Drive(4,
                        "27.10.2022 12:12",
                        "27.10.2022 13:00",
                        2,
                        "Svetosavka 86 Kikinda - Atarska 7 Sombor",
                        128.0,
                        3000,
                        comments,
                        PassengerMockup.getPassengers()
                )
        );
        drives.add(
                new Drive(4,
                        "26.10.2022 17:34",
                        "24.10.2022 18:00",
                        2,
                        "Laze Teleckog 4, Novi Sad - Joko Ono 3, Novi Sad",
                        128.0,
                        12000,
                        comments,
                        PassengerMockup.getPassengers()
                )
        );
        return drives;

    }
}
