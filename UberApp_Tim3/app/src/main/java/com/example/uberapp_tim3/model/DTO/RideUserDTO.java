package com.example.uberapp_tim3.model.DTO;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.uberapp_tim3.model.users.User;

public class RideUserDTO implements Parcelable {

    private Long id;
    private String email;

    public RideUserDTO(){

    }
    public RideUserDTO(String email) {
        this.email = email;

    }

    public RideUserDTO(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.email = userDTO.getEmail();
    }

    public RideUserDTO(Long id, String email) {
        this.id = id;
        this.email = email;

    }

    protected RideUserDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        email = in.readString();
    }

    public static final Creator<RideUserDTO> CREATOR = new Creator<RideUserDTO>() {
        @Override
        public RideUserDTO createFromParcel(Parcel in) {
            return new RideUserDTO(in);
        }

        @Override
        public RideUserDTO[] newArray(int size) {
            return new RideUserDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        parcel.writeString(email);
    }
}
