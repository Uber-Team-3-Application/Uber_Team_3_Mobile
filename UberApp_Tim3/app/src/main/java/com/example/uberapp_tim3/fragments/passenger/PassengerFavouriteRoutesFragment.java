package com.example.uberapp_tim3.fragments.passenger;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.passenger.stepper.ConfirmRideFragment;
import com.example.uberapp_tim3.model.DTO.FavouriteRideDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerFavouriteRoutesFragment extends Fragment {

    private LinearLayout favouriteRoutesLayout;
    private Button btnPickDate;


    public PassengerFavouriteRoutesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.favorite_routes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_favourite_routes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favouriteRoutesLayout = getActivity().findViewById(R.id.favouriteRoutesLayout);
        loadFavouriteRoutes();

    }

    private void loadFavouriteRoutes() {

        Call<List<FavouriteRideDTO>> call = ServiceUtils.rideService.getFavouriteRides();
        call.enqueue(new Callback<List<FavouriteRideDTO>>() {
            @Override
            public void onResponse(Call<List<FavouriteRideDTO>> call, Response<List<FavouriteRideDTO>> response) {
                if(!response.isSuccessful()){
                    Log.d("Favourite rides error", "Something went wrong!");
                    return;
                }
                assert response.body() != null;
                List<FavouriteRideDTO> favouriteRides = response.body();
                setFavouriteRides(favouriteRides);

            }

            @Override
            public void onFailure(Call<List<FavouriteRideDTO>> call, Throwable t) {
                Log.d("Favourite rides error", "COULDNT FETCH FAVOURITE RIDES");
            }
        });
    }

    private void setFavouriteRides(List<FavouriteRideDTO> favouriteRides) {

        LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(FavouriteRideDTO ride: favouriteRides){
            View singleRide = inflater.inflate(R.layout.favourite_route, (ViewGroup) getView(), false);
            TextView favouriteName = singleRide.findViewById(R.id.tvFavouriteRouteName);
            TextView from = singleRide.findViewById(R.id.txtFromFavouriteRoute);
            TextView to = singleRide.findViewById(R.id.txtToFavouriteRoute);
            TextView vehicleType = singleRide.findViewById(R.id.txtVehicleTypeFavouriteRoute);
            btnPickDate = singleRide.findViewById(R.id.dateTimeFuturePicker);
            favouriteName.setText(ride.getFavoriteName());
            from.setText(ride.getLocations().get(0).getDeparture().getAddress());
            to.setText(ride.getLocations().get(0).getDestination().getAddress());
            vehicleType.setText(ride.getVehicleType());

            btnPickDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDateTimePicker();
                }
            });

            setAdditionalInfo(ride, singleRide);

            Button btnOrderAgain = singleRide.findViewById(R.id.btnOrderRideAgainFromFavourites);
            Button btnRemoveFromFavourites = singleRide.findViewById(R.id.btnRemoveFromFavouriteRide);

            setOnClickListeners(btnOrderAgain, btnRemoveFromFavourites, ride);

            favouriteRoutesLayout.addView(singleRide);
        }

    }

    private void setOnClickListeners(Button btnOrderAgain, Button btnRemoveFromFavourites, FavouriteRideDTO ride) {

        setBtnOrderAgainClickListener(btnOrderAgain, ride);
        setBtnRemoveFromFavouritesOnClickListener(btnRemoveFromFavourites, ride);
    }

    private void setBtnRemoveFromFavouritesOnClickListener(Button btnRemoveFromFavourites, FavouriteRideDTO ride) {
        btnRemoveFromFavourites.setOnClickListener(view -> {
            Call<ResponseBody> call = ServiceUtils.rideService.deleteFavouriteRide(ride.getId());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(getActivity(), "Successfully deleted favorite route", Toast.LENGTH_SHORT).show();

                    favouriteRoutesLayout.removeAllViews();
                    loadFavouriteRoutes();

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("Delete fail", "Coudlnt delete favourite ride");
                }
            });
        });
    }

    private void setBtnOrderAgainClickListener(Button btnOrderAgain, FavouriteRideDTO ride) {

        btnOrderAgain.setOnClickListener(view -> {
            // form in an attempt to save or send the data.
            Bundle bundle = new Bundle();
            bundle.putString("departure", ride.getLocations().get(0).getDeparture().getAddress());
            bundle.putString("destination", ride.getLocations().get(0).getDestination().getAddress());
            bundle.putBoolean("babyTransport", ride.isBabyTransport());
            bundle.putBoolean("petTransport", ride.isPetTransport());
            bundle.putString("vehicleType", ride.getVehicleType());
            String orderForLaterText = btnOrderAgain.getText().toString();
            if(!orderForLaterText.equalsIgnoreCase("set ride time")){
                bundle.putString("dateTme", orderForLaterText);
            }else{
                bundle.putString("dateTme", (new Date()).toString());

            }
            ConfirmRideFragment fragment = new ConfirmRideFragment();
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();

        });
    }

    private void setAdditionalInfo(FavouriteRideDTO ride, View singleRide) {
        boolean isPet = ride.isPetTransport();
        boolean isBaby = ride.isBabyTransport();

        LinearLayout lyAdditionalInfo = singleRide.findViewById(R.id.lyAdditionalInfo);
        if(!isBaby && !isPet) lyAdditionalInfo.setVisibility(View.INVISIBLE);

        ImageView babyView = lyAdditionalInfo.findViewById(R.id.imgChild);
        ImageView petView = lyAdditionalInfo.findViewById(R.id.imgPets);

        if(!isBaby) babyView.setVisibility(View.INVISIBLE);
        if(!isPet) petView.setVisibility(View.INVISIBLE);
    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar selectedDateTime = Calendar.getInstance();
                        selectedDateTime.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
                        btnPickDate.setText(getFormattedDateTime(selectedDateTime));
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.show();
    }

    private String getFormattedDateTime(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

}