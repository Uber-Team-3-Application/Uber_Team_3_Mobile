package com.example.uberapp_tim3.dialogs;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import com.example.uberapp_tim3.activities.NewRideNotificationActivity;
import com.example.uberapp_tim3.model.DTO.ReasonDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RejectionDialog extends AlertDialog.Builder {

    private RideDTO ride;
    public RejectionDialog(Context context, NewRideNotificationActivity newRideNotificationActivity, RideDTO rideDTO) {
        super(context);
        setUpDialog(newRideNotificationActivity);
        this.ride = rideDTO;
    }

    private void setUpDialog(NewRideNotificationActivity newRideNotificationActivity) {
        setTitle("Input reason of rejection");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        setView(input);

        setPositiveButton("OK", (dialog, id) -> {
            Call<RideDTO> call = ServiceUtils.rideService.cancelRide(ride.getId(), new ReasonDTO(input.getText().toString()));
            call.enqueue(new Callback<RideDTO>() {
                @Override
                public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {

                    dialog.dismiss();
                    newRideNotificationActivity.finish();
                }

                @Override
                public void onFailure(Call<RideDTO> call, Throwable t) {
                    dialog.dismiss();
                    newRideNotificationActivity.finish();
                    Log.d("FATALNO", "ERROR");
                }
            });


        });

        setNegativeButton("CANCEL", (dialog, id) -> dialog.cancel());
    }


}
