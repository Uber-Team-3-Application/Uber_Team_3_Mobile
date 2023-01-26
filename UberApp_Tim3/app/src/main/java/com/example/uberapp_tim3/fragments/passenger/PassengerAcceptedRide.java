package com.example.uberapp_tim3.fragments.passenger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.CreatedRideDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.DTO.VehicleDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerAcceptedRide extends Fragment {

    public PassengerAcceptedRide() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ride_accepted, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        RideDTO rideDTO = null;
        if (bundle != null){
            rideDTO = bundle.getParcelable("ride");
            Call<UserDTO> call = ServiceUtils.driverService.getDriver(rideDTO.getDriver().getId());
            call.enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    if(!response.isSuccessful()) return;
                    UserDTO driver = response.body();
                    TextView txtFullName = getView().findViewById(R.id.txtdriverFullName);
                    txtFullName.setText(String.format("%s %s", driver.getName(), driver.getSurname()));

                    TextView txtPhoneNumber = getView().findViewById(R.id.txtdriverPhoneNumber);
                    txtPhoneNumber.setText(driver.getTelephoneNumber());

                    TextView txtEmailAddress = getView().findViewById(R.id.txtdriverEmail);
                    txtEmailAddress.setText(driver.getEmail());

                    ImageView imgPassengerInfoAvatar = getActivity().findViewById(R.id.imgPassengerInfoAvatar);
                    if(!driver.getProfilePicture().contains(",")){return;}
                    String base64Image = driver.getProfilePicture().split(",")[1];
                    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imgPassengerInfoAvatar.setImageBitmap(decodedByte);
                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {

                }
            });
            Call<VehicleDTO> call2 = ServiceUtils.driverService.getVehicle(rideDTO.getDriver().getId());
            call2.enqueue(new Callback<VehicleDTO>() {
                @Override
                public void onResponse(Call<VehicleDTO> call, Response<VehicleDTO> response) {
                    if(!response.isSuccessful()) return;
                    VehicleDTO vehicle = response.body();
                    TextView txtDriverLicencse = getView().findViewById(R.id.txtDriverLicense);
                    txtDriverLicencse.setText(vehicle.getLicenseNumber());
                    TextView txtVehicleType = getView().findViewById(R.id.vehicleType);
                    txtVehicleType.setText(vehicle.getVehicleType());
                    TextView txtModel = getView().findViewById(R.id.model);
                    txtModel.setText(vehicle.getModel());
                }

                @Override
                public void onFailure(Call<VehicleDTO> call, Throwable t) {

                }
            });
            CountDownTimer timer = new CountDownTimer((long) (rideDTO.getEstimatedTimeInMinutes()*60000), 1000) {
                public void onTick(long millisUntilFinished) {
                    TextView timer = getView().findViewById(R.id.timer_text_view);
                    timer.setText(Math.toIntExact(millisUntilFinished));
                }

                public void onFinish() {
                    // Code to execute when the timer finishes
                }
            }.start();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
