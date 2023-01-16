package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewWithPassengerDTO implements Parcelable {
    private Long id;
    private int rating;
    private String comment;
    private RideUserDTO passenger;

    public ReviewWithPassengerDTO() {}

    public ReviewWithPassengerDTO(Long id, int rating, String comment, RideUserDTO passenger) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.passenger = passenger;
    }

    protected ReviewWithPassengerDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        rating = in.readInt();
        comment = in.readString();
        passenger = in.readParcelable(RideUserDTO.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeInt(rating);
        dest.writeString(comment);
        dest.writeParcelable(passenger, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReviewWithPassengerDTO> CREATOR = new Creator<ReviewWithPassengerDTO>() {
        @Override
        public ReviewWithPassengerDTO createFromParcel(Parcel in) {
            return new ReviewWithPassengerDTO(in);
        }

        @Override
        public ReviewWithPassengerDTO[] newArray(int size) {
            return new ReviewWithPassengerDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RideUserDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(RideUserDTO passenger) {
        this.passenger = passenger;
    }

}
