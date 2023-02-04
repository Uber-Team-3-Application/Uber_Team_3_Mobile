package com.example.uberapp_tim3.model.DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class WorkingHoursDTO {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;

    public WorkingHoursDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public WorkingHoursDTO(Long id, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}
