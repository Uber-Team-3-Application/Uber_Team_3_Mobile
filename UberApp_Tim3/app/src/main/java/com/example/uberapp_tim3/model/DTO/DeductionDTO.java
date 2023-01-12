package com.example.uberapp_tim3.model.DTO;

import com.example.uberapp_tim3.model.drives.Rejection;

import java.time.LocalDateTime;

public class DeductionDTO {

    private String reason;
    private LocalDateTime timeOfRejection;

    public DeductionDTO() {

    }
    public DeductionDTO(String reason, LocalDateTime timeOfRejection){
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTimeOfRejection() {
        return this.timeOfRejection;
    }

    public void setTimeOfRejection(LocalDateTime deductionTime) {
        this.timeOfRejection = deductionTime;
    }
}
