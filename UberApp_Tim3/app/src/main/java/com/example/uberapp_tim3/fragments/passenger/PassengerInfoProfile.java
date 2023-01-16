package com.example.uberapp_tim3.fragments.passenger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.mockup.Passenger;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.PassengerMockup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerInfoProfile extends Fragment {


    public PassengerInfoProfile() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fpassenger_info_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle  = this.getArguments();

        assert bundle != null;
        Long passengerID= bundle.getLong("passengerId");
        Log.d("Passenger ID", Long.toString(passengerID));
        Call<PassengerDTO> call = ServiceUtils.passengerService.getPassenger(passengerID);
        call.enqueue(new Callback<PassengerDTO>() {
            @Override
            public void onResponse(Call<PassengerDTO> call, Response<PassengerDTO> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Can't get passenger info.", Toast.LENGTH_SHORT).show();
                    return;
                }
                PassengerDTO passenger = response.body();
                assert passenger != null;
                setTextViews(passenger);

            }

            @Override
            public void onFailure(Call<PassengerDTO> call, Throwable t) {
                Log.d("FAIL", "Something went wrong!");
            }
        });

    }

    private void setTextViews(PassengerDTO passenger){

        TextView txtFullName = getView().findViewById(R.id.txtdriverFullName);
        txtFullName.setText(String.format("%s %s", passenger.getName(), passenger.getSurname()));

        TextView txtPhoneNumber = getView().findViewById(R.id.txtdriverPhoneNumber);
        txtPhoneNumber.setText(passenger.getTelephoneNumber());

        TextView txtEmailAddress = getView().findViewById(R.id.txtdriverEmail);
        txtEmailAddress.setText(passenger.getEmail());

        TextView txtAddress = getView().findViewById(R.id.txtDriverHomeAddress);
        txtAddress.setText(passenger.getAddress());

        ImageView imgPassengerInfoAvatar = getActivity().findViewById(R.id.imgPassengerInfoAvatar);
        if(!passenger.getProfilePicture().contains(",")){return;}
        String base64Image = passenger.getProfilePicture().split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgPassengerInfoAvatar.setImageBitmap(decodedByte);
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
        requireActivity().setTitle("Passenger profile");
    }

}