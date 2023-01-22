package com.example.uberapp_tim3.model.DTO;

import java.util.Date;

public class ReportRequestDTO {

    private Long driverId;
    private String role;
    private String typeOfReport;
    private String from;
    private String to;

    public ReportRequestDTO() {
    }

    public ReportRequestDTO(Long driverId, String role, String typeOfReport, String from, String to) {
        this.driverId = driverId;
        this.role = role;
        this.typeOfReport = typeOfReport;
        this.from = from;
        this.to = to;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTypeOfReport() {
        return typeOfReport;
    }

    public void setTypeOfReport(String typeOfReport) {
        this.typeOfReport = typeOfReport;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
