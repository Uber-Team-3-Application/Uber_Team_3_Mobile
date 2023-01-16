package com.example.uberapp_tim3.model.DTO;

import org.jetbrains.annotations.NotNull;

public class DriverActivityDTO {
    @NotNull
    private boolean active;

    public DriverActivityDTO(boolean active) {
        this.active = active;
    }

    public DriverActivityDTO() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
