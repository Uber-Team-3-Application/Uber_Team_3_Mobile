package com.example.uberapp_tim3.model.enums;

import com.example.uberapp_tim3.model.drives.Drive;

import java.util.ArrayList;

public enum DriveStatus {
    ON_HOLD,
    ACCEPTED,
    REJECTED,
    ACTIVE,
    FINISHED;

    private ArrayList<Drive> drives;

    DriveStatus() {
        this.drives = new ArrayList<>();
    }

    public ArrayList<Drive> getDrives() {
        return drives;
    }

    public void setDrives(ArrayList<Drive> drives) {
        this.drives = drives;
    }

    DriveStatus(ArrayList<Drive> drives) {
        this.drives = drives;
    }
}
