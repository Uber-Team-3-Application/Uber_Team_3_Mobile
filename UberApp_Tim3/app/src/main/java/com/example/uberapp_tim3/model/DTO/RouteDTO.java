package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class RouteDTO implements Parcelable {

    private Long id;
    private LocationDTO departure;
    private LocationDTO destination;

    public RouteDTO() {

    }

    public RouteDTO(Long id, LocationDTO departure, LocationDTO destination) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
    }
    public RouteDTO(LocationDTO departure, LocationDTO destination) {
        this.departure = departure;
        this.destination = destination;
    }

    protected RouteDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        departure = in.readParcelable(LocationDTO.class.getClassLoader());
        destination = in.readParcelable(LocationDTO.class.getClassLoader());
    }

    public static final Creator<RouteDTO> CREATOR = new Creator<RouteDTO>() {
        @Override
        public RouteDTO createFromParcel(Parcel in) {
            return new RouteDTO(in);
        }

        @Override
        public RouteDTO[] newArray(int size) {
            return new RouteDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocationDTO getDeparture() {
        return departure;
    }

    public void setDeparture(LocationDTO departure) {
        this.departure = departure;
    }

    public LocationDTO getDestination() {
        return destination;
    }

    public void setDestination(LocationDTO destination) {
        this.destination = destination;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeParcelable(departure, i);
        parcel.writeParcelable(destination, i);
    }
}
