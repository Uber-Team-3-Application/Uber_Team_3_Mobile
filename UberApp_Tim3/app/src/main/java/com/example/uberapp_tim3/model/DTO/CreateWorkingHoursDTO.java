package com.example.uberapp_tim3.model.DTO;

import java.time.LocalDateTime;

public class CreateWorkingHoursDTO {

    private LocalDateTime start;

    public CreateWorkingHoursDTO() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public CreateWorkingHoursDTO(LocalDateTime start) {
        this.start = start;
    }
}
