package com.example.uberapp_tim3.dialogs;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.uberapp_tim3.activities.NewRideNotificationActivity;
import com.example.uberapp_tim3.model.DTO.ReasonDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanicDialog  extends AlertDialog.Builder{

    RideDTO ride;

    public PanicDialog(@NonNull Context context) {
        super(context);
    }

    public PanicDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public PanicDialog(Context context, RideDTO rideDTO) {
        super(context);
        setUpDialog();
        this.ride = rideDTO;
    }

    private void setUpDialog() {
        setTitle("Input reason of panic: ");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        setView(input);

        setPositiveButton("OK", (dialog, id) -> {
            Call<RideDTO> call = ServiceUtils.rideService.panicRide(ride.getId(), new ReasonDTO(input.getText().toString()));
            call.enqueue(new Callback<RideDTO>() {
                @Override
                public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<RideDTO> call, Throwable t) {
                    dialog.dismiss();
                    Log.d("ERROR", t.getMessage());
                }
            });


        });
        setNegativeButton("CANCEL", (dialog, id) -> dialog.cancel());

    }


}
