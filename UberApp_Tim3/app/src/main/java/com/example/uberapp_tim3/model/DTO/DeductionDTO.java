package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.uberapp_tim3.model.drives.Rejection;

import java.time.LocalDateTime;

public class DeductionDTO implements Parcelable {

    private String reason;
    private String timeOfRejection;

    public DeductionDTO() {

    }
    public DeductionDTO(String reason, String timeOfRejection){
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }

    protected DeductionDTO(Parcel in) {
        reason = in.readString();
    }

    public static final Creator<DeductionDTO> CREATOR = new Creator<DeductionDTO>() {
        @Override
        public DeductionDTO createFromParcel(Parcel in) {
            return new DeductionDTO(in);
        }

        @Override
        public DeductionDTO[] newArray(int size) {
            return new DeductionDTO[size];
        }
    };

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTimeOfRejection() {
        return this.timeOfRejection;
    }

    public void setTimeOfRejection(String deductionTime) {
        this.timeOfRejection = deductionTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(reason);
    }
}
