package com.example.uberapp_tim3.model.DTO;

import java.util.Date;

public class WorkingHoursDTO {
    private Long id;
    private Date start;
    private Date end;

    public WorkingHoursDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public WorkingHoursDTO(Long id, Date start, Date end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }
}
