package com.example.uberapp_tim3.fragments.driver;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.DTO.UpdateDriverDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.mockup.Driver;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DriverMockup;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverEditInfoFragment extends Fragment {

    private Button btnChangeAvatar;
    private SharedPreferences sharedPreferences;
    private CircleImageView imgAvatar;
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvEmail;
    private TextView tvAddress;
    private TextView tvNumber;
    private String avatarBase64;


    public DriverEditInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passenger_edit_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        imgAvatar = getActivity().findViewById(R.id.imgEditPassengerAvatar);
        btnChangeAvatar = getActivity().findViewById(R.id.btnChangePassengerAvatar);
        setOnClickListeners();
        this.getDriver(this.sharedPreferences.getLong("pref_id", 0));

        super.onViewCreated(view, savedInstanceState);

    }

    public void getDriver(Long id){

        Call<UserDTO> call = ServiceUtils.driverService.getDriver(id);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if(!response.isSuccessful()){
                    Log.e("Response Error", "ERROR");
                    return;
                }
                UserDTO driver = response.body();

                assert driver != null;
                tvName = getActivity().findViewById(R.id.txtPassengerEditFirstName);
                tvName.setText(driver.getName());

                tvSurname = getActivity().findViewById(R.id.txtPassengerEditLastName);
                tvSurname.setText(driver.getSurname());

                tvNumber = getActivity().findViewById(R.id.txtPassengerEditPhoneNumber);
                tvNumber.setText(driver.getTelephoneNumber());

                tvEmail = getActivity().findViewById(R.id.txtPassengerEmailEdit);
                tvEmail.setText(driver.getEmail());

                tvAddress = getActivity().findViewById(R.id.txtHomeAddressEdit);
                tvAddress.setText(driver.getAddress());

                if(!driver.getProfilePicture().contains(",")){ avatarBase64 = "profilna";return;}

                avatarBase64 = driver.getProfilePicture();
                String base64Image = driver.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imgAvatar.setImageBitmap(decodedByte);
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });

    }

    private void getImageFromAlbum(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 100){
            assert data != null;
            Uri imageUri = data.getData();
            Glide.with(requireContext()).load(imageUri).into(imgAvatar);

        }

    }

    private void setOnClickListeners() {
        btnChangeAvatar = getActivity().findViewById(R.id.btnChangePassengerAvatar);
        btnChangeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromAlbum();
            }
        });

        ((Button) getActivity().findViewById(R.id.btnCancelEditPassenger)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        ((Button) getActivity().findViewById(R.id.btnAcceptEditPassenger)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateDriverDTO driver = new UpdateDriverDTO();
                driver.setAddress(tvAddress.getText().toString());
                driver.setTelephoneNumber(tvNumber.getText().toString());
                driver.setEmail(tvEmail.getText().toString());
                driver.setName(tvName.getText().toString());
                driver.setSurname(tvSurname.getText().toString());

                imgAvatar.buildDrawingCache();
                Bitmap bmap = imgAvatar.getDrawingCache();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                byte[] bb = bos.toByteArray();
                String image = Base64.encodeToString(bb, 100);
                Log.d("Avatar", image);
                driver.setProfilePicture("data:image/jpeg;base64," + image);
                Call<UpdateDriverDTO> call = ServiceUtils.driverService.updateDriver(sharedPreferences.getLong("pref_id", 0), driver);
                call.enqueue(new Callback<UpdateDriverDTO>() {
                    @Override
                    public void onResponse(Call<UpdateDriverDTO> call, Response<UpdateDriverDTO> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(null, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                        getActivity().getSupportFragmentManager().popBackStackImmediate();

                    }

                    @Override
                    public void onFailure(Call<UpdateDriverDTO> call, Throwable t) {
                        Toast.makeText(null, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}