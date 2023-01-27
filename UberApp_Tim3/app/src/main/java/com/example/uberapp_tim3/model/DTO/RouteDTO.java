package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

public class RouteDTO implements Parcelable {


    private LocationDTO departure;
    private LocationDTO destination;

    public RouteDTO() {

    }

    public RouteDTO(Long id, LocationDTO departure, LocationDTO destination) {

        this.departure = departure;
        this.destination = destination;
    }
    public RouteDTO(LocationDTO departure, LocationDTO destination) {
        this.departure = departure;
        this.destination = destination;
    }

    protected RouteDTO(Parcel in) {
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
        parcel.writeParcelable(departure, i);
        parcel.writeParcelable(destination, i);
    }
}
