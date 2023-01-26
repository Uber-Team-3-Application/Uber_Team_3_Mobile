package com.example.uberapp_tim3.fragments.driver;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.EditCarInformationFragment;
import com.example.uberapp_tim3.fragments.EditPasswordFragment;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.DTO.VehicleDTO;
import com.example.uberapp_tim3.model.mockup.Driver;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DriverMockup;
import com.example.uberapp_tim3.tools.FragmentTransition;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverAccountFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences sharedPreferences;

    public static DriverAccountFragment newInstance() {
        return new DriverAccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_driver_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Long driverId= sharedPreferences.getLong("pref_id", 0);
        Call<UserDTO> call = ServiceUtils.driverService.getDriver(driverId);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Can't get driver info.", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDTO driver = response.body();
                assert driver != null;
                TextView txtFullName = getView().findViewById(R.id.txtdriverFullName);
                txtFullName.setText(String.format("%s %s", driver.getName(), driver.getSurname()));

                TextView txtPhoneNumber = getView().findViewById(R.id.txtdriverPhoneNumber);
                txtPhoneNumber.setText(driver.getTelephoneNumber());

                TextView txtEmailAddress = getView().findViewById(R.id.txtdriverEmail);
                txtEmailAddress.setText(driver.getEmail());
                
                if(driver.getProfilePicture().contains(",")){

                String base64Image = driver.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                CircleImageView cv = getActivity().findViewById(R.id.imgAvatar);
                cv.setImageBitmap(decodedByte);
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

            }
        });
        Call<VehicleDTO> call2 = ServiceUtils.driverService.getVehicle(driverId);
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

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.driver_profile);
    }

    private void setOnClickListeners() {
        ((Button)getActivity().findViewById(R.id.btnEditDriverInfo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverEditInfoFragment(), getActivity(), true);
            }
        });

        ((Button)getActivity().findViewById(R.id.btnEditPassword)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new EditPasswordFragment(), getActivity(), true);
            }
        });
        ((Button)getActivity().findViewById(R.id.btnDriverReports)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverReportFragment(), getActivity(), true);
            }
        });
        ((Button)getActivity().findViewById(R.id.btnDriverStatistics)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransition.to(new DriverStatisticsFragment(), getActivity(), true);
            }
        });

    }


    @Override
    public void onClick(View view) {

    }
}