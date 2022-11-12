package com.example.uberapp_tim3.tools;

import android.annotation.SuppressLint;

import com.example.uberapp_tim3.model.Drive;
import com.example.uberapp_tim3.model.Driver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DrivesMockUp {

    public static List<Drive> getDrives()  {
        ArrayList<Drive> drives = new ArrayList<>();

        String date_string = "26-09-1989";
        //Instantiating the SimpleDateFormat class
        //Parsing the given String to Date object

        drives.add(
                new Drive(4,
                        "Voznja je bila odlicna",
                        "26.10.2022 12:34",
                        "26.10.2022 13:00",
                        2,
                        "Kikinda - Sombor",
                        30,
                        2500.0)
        );

        drives.add(
                new Drive(4,
                        "Voznja je bila odlicna",
                        "26.10.2022 12:34",
                        "26.10.2022 13:00",
                        2,
                        "Kikinda - Sombor",
                        30,
                        2500.0)
        );
        drives.add(
                new Drive(4,
                        "Voznja je bila odlicna",
                        "26.10.2022 12:34",
                        "26.10.2022 13:00",
                        2,
                        "Kikinda - Sombor",
                        30,
                        2500.0)
        );

        return drives;

    }
}
