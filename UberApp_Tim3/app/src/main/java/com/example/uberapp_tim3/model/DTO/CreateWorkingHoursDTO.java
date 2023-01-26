package com.example.uberapp_tim3.model.DTO;

import java.util.Date;

public class CreateWorkingHoursDTO {
    private Date start;

    public CreateWorkingHoursDTO() {
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public CreateWorkingHoursDTO(Date start) {
        this.start = start;
    }
}
