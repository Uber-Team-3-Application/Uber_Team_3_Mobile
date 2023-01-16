package com.example.uberapp_tim3.fragments.passenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.EditPasswordFragment;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.mockup.Passenger;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.PassengerMockup;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerAccountFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passenger_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        setTextViews();
        setOnClickListeners();
    }


    public void getPassenger(Long id){

        Call<PassengerDTO> call = ServiceUtils.passengerService.getPassenger(id);
        call.enqueue(new Callback<PassengerDTO>() {
            @Override
            public void onResponse(Call<PassengerDTO> call, Response<PassengerDTO> response) {
                if(!response.isSuccessful()) return;

                PassengerDTO passenger = response.body();

                TextView tvName = getActivity().findViewById(R.id.txtPassengerFullName);
                String fullName = passenger.getName() + " " + passenger.getSurname();
                tvName.setText(fullName);

                TextView tvPhoneNumber = getActivity().findViewById(R.id.txtPassengerPhoneNumber);
                tvPhoneNumber.setText(passenger.getTelephoneNumber());

                TextView emailAddress = getActivity().findViewById(R.id.txtPassengerEmail);
                emailAddress.setText(passenger.getEmail());

                TextView homeAddress = getActivity().findViewById(R.id.txtPassengerAddress);
                homeAddress.setText(passenger.getAddress());

                if(!passenger.getProfilePicture().contains(",")){return;}

                String base64Image = passenger.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                CircleImageView cv = getActivity().findViewById(R.id.imgAvatar);
                cv.setImageBitmap(decodedByte);


            }

            @Override
            public void onFailure(Call<PassengerDTO> call, Throwable t) {
                Log.d("FAIIIL", t.getMessage());
                Log.d("FAIIIL", "BLATRUC");
            }
        });

    }



    private void setTextViews(){
        Passenger passenger = PassengerMockup.getPassengers().get(0);

        TextView txtFullName = getView().findViewById(R.id.txtPassengerFullName);
        txtFullName.setText(String.format("%s %s", passenger.getName(), passenger.getLastName()));

        TextView txtPhoneNumber = getView().findViewById(R.id.txtPassengerPhoneNumber);
        txtPhoneNumber.setText(passenger.getPhoneNumber());

        TextView txtEmailAddress = getView().findViewById(R.id.txtPassengerEmail);
        txtEmailAddress.setText(passenger.getEmailAddress());

        TextView txtAddress = getView().findViewById(R.id.txtPassengerAddress);
        txtAddress.setText(passenger.getAddress());

        TextView tvBlocked = getView().findViewById(R.id.txtBlocked);
        if(!passenger.isBlocked())
            tvBlocked.setText("");
        else {
            tvBlocked.setText(R.string.blocked_text);
            tvBlocked.setTextColor(Color.RED);
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
        requireActivity().setTitle("Profile");
        this.getPassenger(sharedPreferences.getLong("pref_id", 0));
    }

    private void setOnClickListeners() {
        Button btnEditPassword = getView().findViewById(R.id.btnEditPassword);
        Button btnEditBasicInfo = getView().findViewById(R.id.btnEditBasicPassInfo);
        Button btnPassRoutes = getView().findViewById(R.id.btnFavouriteRoutes);
        Button btnPassReports = getView().findViewById(R.id.btnPassengerReports);
        Button btnEditCard = getView().findViewById(R.id.btnEditCardInfo);

        btnEditBasicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerEditInfoFragment()).addToBackStack(null).commit();
            }
        });
        btnPassReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerReportFragment()).addToBackStack(null).commit();

            }
        });
        btnPassRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerFavouriteRoutesFragment()).addToBackStack(null).commit();

            }
        });

        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EditPasswordFragment()).addToBackStack(null).commit();

            }
        });
        btnEditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerFinancialCardFragment()).addToBackStack(null).commit();

            }
        });

    }




}