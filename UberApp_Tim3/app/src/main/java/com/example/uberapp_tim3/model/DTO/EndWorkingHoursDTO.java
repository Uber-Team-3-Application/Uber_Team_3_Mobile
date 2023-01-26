package com.example.uberapp_tim3.model.DTO;

import java.util.Date;

public class EndWorkingHoursDTO {
    private Date end;

    public EndWorkingHoursDTO() {
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public EndWorkingHoursDTO(Date end) {
        this.end = end;
    }
}
