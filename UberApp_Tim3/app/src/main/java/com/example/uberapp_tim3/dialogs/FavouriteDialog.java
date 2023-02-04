package com.example.uberapp_tim3.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uberapp_tim3.model.DTO.CreateFavouriteRideDTO;
import com.example.uberapp_tim3.model.DTO.FavouriteRideDTO;
import com.example.uberapp_tim3.model.DTO.PassengerRideDTO;
import com.example.uberapp_tim3.model.DTO.ReasonDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RouteDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.HashSet;
import java.util.LinkedHashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteDialog extends AlertDialog.Builder {

    private PassengerRideDTO rideDTO;

    public FavouriteDialog(Context context, PassengerRideDTO rideDTO) {
        super(context);
        this.rideDTO = rideDTO;
        setUpDialog();
    }

    @SuppressLint("SetTextI18n")
    private void setUpDialog() {
        setTitle("Input name of favourite ride");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        setView(input);

        setPositiveButton("OK", (dialog, id) -> {
            if (input.getText().toString().equals(""))
                input.setText("Favourite ride");
            CreateFavouriteRideDTO favouriteRideDTO = new CreateFavouriteRideDTO();
            favouriteRideDTO.setFavoriteName(input.getText().toString());
            favouriteRideDTO.setBabyTransport(rideDTO.isBabyTransport());
            favouriteRideDTO.setPetTransport(rideDTO.isPetTransport());
            HashSet<UserDTO> passengers = new HashSet<>(rideDTO.getPassengers());
            favouriteRideDTO.setPassengers(passengers);
            favouriteRideDTO.setVehicleType(rideDTO.getVehicleType());
            LinkedHashSet<RouteDTO> linkedHashSet
                    = new LinkedHashSet<RouteDTO>(rideDTO.getLocations());
            favouriteRideDTO.setLocations(linkedHashSet);
            ServiceUtils.rideService.addFavouriteRide(favouriteRideDTO).enqueue(new Callback<FavouriteRideDTO>() {
                @Override
                public void onResponse(Call<FavouriteRideDTO> call, Response<FavouriteRideDTO> response) {
                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<FavouriteRideDTO> call, Throwable t) {
                    System.out.println("FAILURE: " + t);
                }
            });

        });
        setNegativeButton("CANCEL", (dialog, id) -> dialog.cancel());

    }



}
