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
                        "Svetosavka 86 Kikinda - Atarska 7 Sombor",
                        128.0,
                        6500.0,
                        comments,
                        PassengerMockup.getPassengers()
                )
        );

        drives.add(
                new Drive(4,
                        "26.10.2022 12:34",
                        "26.10.2022 13:00",
                        2,
                        "Svetosavka 86 Kikinda - Atarska 7 Sombor",
                        128.0,
                        6500.0,
                        comments,
                        PassengerMockup.getPassengers()
                )
        );
        drives.add(
                new Drive(4,
                        "26.10.2022 12:34",
                        "26.10.2022 13:00",
                        2,
                        "Svetosavka 86 Kikinda - Atarska 7 Sombor",
                        128.0,
                        6500.0,
                        comments,
                        PassengerMockup.getPassengers()
                )
        );
        return drives;

    }
}
