package com.example.uberapp_tim3.model.DTO;

import java.util.Date;
import java.util.Map;

public class ReportSumAverageDTO {

    Map<Date, Double> result;
    double sum;
    double average;

    public ReportSumAverageDTO() {
    }

    public Map<Date, Double> getResult() {
        return result;
    }

    public void setResult(Map<Date, Double> result) {
        this.result = result;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public ReportSumAverageDTO(Map<Date, Double> result, double sum, double average) {
        this.result = result;
        this.sum = sum;
        this.average = average;
    }
}
