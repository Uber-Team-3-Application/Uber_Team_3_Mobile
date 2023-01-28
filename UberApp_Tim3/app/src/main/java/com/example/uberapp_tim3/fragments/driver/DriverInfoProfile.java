package com.example.uberapp_tim3.fragments.driver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverInfoProfile extends Fragment {

    public DriverInfoProfile() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_driver_info_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle  = this.getArguments();

        assert bundle != null;
        Long driverId= bundle.getLong("driverId");
        Call<UserDTO> call = ServiceUtils.userService.getDriver(driverId);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Can't get driver info.", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDTO driver = response.body();
                assert driver != null;
                setTextViews(driver);

            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.d("FAIL", "Something went wrong!");
            }
        });

    }

    private void setTextViews(UserDTO driver){

        TextView txtFullName = requireView().findViewById(R.id.txtdriverFullName);
        txtFullName.setText(String.format("%s %s", driver.getName(), driver.getSurname()));

        TextView txtPhoneNumber = requireView().findViewById(R.id.txtdriverPhoneNumber);
        txtPhoneNumber.setText(driver.getTelephoneNumber());

        TextView txtEmailAddress = requireView().findViewById(R.id.txtdriverEmail);
        txtEmailAddress.setText(driver.getEmail());

        TextView txtAddress = requireView().findViewById(R.id.txtDriverHomeAddress);
        txtAddress.setText(driver.getAddress());

        TextView tvBlocked = requireView().findViewById(R.id.txtDriverBlocked);
        tvBlocked.setText("");
        ImageView imgdriverInfoAvatar = requireView().findViewById(R.id.imgDriverInfoAvatar);

        if(!driver.getProfilePicture().contains(",")){return;}
        String base64Image = driver.getProfilePicture().split(",")[1];
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgdriverInfoAvatar.setImageBitmap(decodedByte);

        ImageView call = requireView().findViewById(R.id.callPhone);
        call.setOnClickListener(view -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" +driver.getTelephoneNumber()));
            startActivity(dialIntent);
        });
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        requireActivity().setTitle("Driver profile");
    }
}