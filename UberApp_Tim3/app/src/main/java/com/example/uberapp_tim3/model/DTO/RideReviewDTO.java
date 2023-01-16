package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class RideReviewDTO implements Parcelable {

    private ReviewWithPassengerDTO vehicleReview;
    private ReviewWithPassengerDTO driverReview;

    public RideReviewDTO(ReviewWithPassengerDTO vehicleReview, ReviewWithPassengerDTO driverReview) {
        this.vehicleReview = vehicleReview;
        this.driverReview = driverReview;
    }

    protected RideReviewDTO(Parcel in) {
        vehicleReview = in.readParcelable(ReviewWithPassengerDTO.class.getClassLoader());
        driverReview = in.readParcelable(ReviewWithPassengerDTO.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(vehicleReview, flags);
        dest.writeParcelable(driverReview, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RideReviewDTO> CREATOR = new Creator<RideReviewDTO>() {
        @Override
        public RideReviewDTO createFromParcel(Parcel in) {
            return new RideReviewDTO(in);
        }

        @Override
        public RideReviewDTO[] newArray(int size) {
            return new RideReviewDTO[size];
        }
    };

    public ReviewWithPassengerDTO getVehicleReview() {
        return vehicleReview;
    }

    public void setVehicleReview(ReviewWithPassengerDTO vehicleReview) {
        this.vehicleReview = vehicleReview;
    }

    public ReviewWithPassengerDTO getDriverReview() {
        return driverReview;
    }

    public void setDriverReview(ReviewWithPassengerDTO driverReview) {
        this.driverReview = driverReview;
    }
}
