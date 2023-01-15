package com.example.uberapp_tim3.fragments.passenger;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.example.uberapp_tim3.model.mockup.Passenger;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.PassengerMockup;
import com.google.android.gms.maps.model.Circle;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerEditInfoFragment extends Fragment {

    private Button btnChangeAvatar;
    private SharedPreferences sharedPreferences;
    private CircleImageView imgAvatar;
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvEmail;
    private TextView tvAddress;
    private TextView tvNumber;
    private String avatarBase64;
    public PassengerEditInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.edit_info);
    }

    public void getPassenger(Long id){

        Call<PassengerDTO> call = ServiceUtils.passengerService.getPassenger(id);
        call.enqueue(new Callback<PassengerDTO>() {
            @Override
            public void onResponse(Call<PassengerDTO> call, Response<PassengerDTO> response) {
                if(!response.isSuccessful()){
                    Log.e("Response Error", "ERROR");
                    return;
                }
                PassengerDTO passenger = response.body();

                assert passenger != null;
                tvName = getActivity().findViewById(R.id.txtPassengerEditFirstName);
                tvName.setText(passenger.getName());

                tvSurname = getActivity().findViewById(R.id.txtPassengerEditLastName);
                tvSurname.setText(passenger.getSurname());

                tvNumber = getActivity().findViewById(R.id.txtPassengerEditPhoneNumber);
                tvNumber.setText(passenger.getTelephoneNumber());

                tvEmail = getActivity().findViewById(R.id.txtPassengerEmailEdit);
                tvEmail.setText(passenger.getEmail());

                tvAddress = getActivity().findViewById(R.id.txtHomeAddressEdit);
                tvAddress.setText(passenger.getAddress());

                if(!passenger.getProfilePicture().contains(",")){ avatarBase64 = "profilna";return;}

                avatarBase64 = passenger.getProfilePicture();
                String base64Image = passenger.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imgAvatar.setImageBitmap(decodedByte);
            }

            @Override
            public void onFailure(Call<PassengerDTO> call, Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_edit_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        imgAvatar = getActivity().findViewById(R.id.imgEditPassengerAvatar);
        btnChangeAvatar = getActivity().findViewById(R.id.btnChangePassengerAvatar);
        setOnClickListeners();
        this.getPassenger(this.sharedPreferences.getLong("pref_id", 0));

        super.onViewCreated(view, savedInstanceState);
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
                PassengerDTO passenger = new PassengerDTO();
                passenger.setConfirmedMail(true);
                passenger.setAddress(tvAddress.getText().toString());
                passenger.setTelephoneNumber(tvNumber.getText().toString());
                passenger.setEmail(tvEmail.getText().toString());
                passenger.setName(tvName.getText().toString());
                passenger.setSurname(tvSurname.getText().toString());
                passenger.setId(sharedPreferences.getLong("pref_id", 102));

                imgAvatar.buildDrawingCache();
                Bitmap bmap = imgAvatar.getDrawingCache();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                byte[] bb = bos.toByteArray();
                String image = Base64.encodeToString(bb, 100);
                Log.d("Avatar", image);
                passenger.setProfilePicture("data:image/jpeg;base64," + image);
                Call<PassengerDTO> call = ServiceUtils.passengerService.updatePassenger(passenger.getId(), passenger);
                call.enqueue(new Callback<PassengerDTO>() {
                    @Override
                    public void onResponse(Call<PassengerDTO> call, Response<PassengerDTO> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(null, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                        PassengerDTO passengerDTO = response.body();
                        assert passengerDTO != null;
                        TextView tvName = getActivity().findViewById(R.id.passengerNameNavigation);
                        String fullName = passengerDTO.getName() + " " + passengerDTO.getSurname();
                        tvName.setText(fullName);

                        TextView tvPhoneNumber = getActivity().findViewById(R.id.passengerPhoneNavigation);
                        tvPhoneNumber.setText(passengerDTO.getTelephoneNumber());
                        CircleImageView cv = getActivity().findViewById(R.id.passengerProfilePictureNavigation);
                        String base64Image = passengerDTO.getProfilePicture().split(",")[1];
                        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        cv.setImageBitmap(decodedByte);

                        getActivity().getSupportFragmentManager().popBackStackImmediate();

                    }

                    @Override
                    public void onFailure(Call<PassengerDTO> call, Throwable t) {
                        Toast.makeText(null, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}