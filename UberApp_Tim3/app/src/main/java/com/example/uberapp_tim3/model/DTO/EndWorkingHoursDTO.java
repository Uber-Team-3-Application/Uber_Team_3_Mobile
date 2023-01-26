package com.example.uberapp_tim3.model.DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class EndWorkingHoursDTO {
    private LocalDateTime end;

    public EndWorkingHoursDTO() {
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public EndWorkingHoursDTO(LocalDateTime end) {
        this.end = end;
    }
}
