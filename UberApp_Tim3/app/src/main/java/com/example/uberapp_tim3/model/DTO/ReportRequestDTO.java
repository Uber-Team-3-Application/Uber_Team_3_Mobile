package com.example.uberapp_tim3.model.DTO;

import java.util.Date;

public class ReportRequestDTO {

    private Long driverId;
    private String role;
    private String typeOfReport;
    private Date from;
    private Date to;

    public ReportRequestDTO() {
    }

    public ReportRequestDTO(Long driverId, String role, String typeOfReport, Date from, Date to) {
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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
