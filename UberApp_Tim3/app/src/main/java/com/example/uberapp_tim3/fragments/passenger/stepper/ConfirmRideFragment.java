package com.example.uberapp_tim3.fragments.passenger.stepper;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.CreateRideDTO;
import com.example.uberapp_tim3.model.DTO.CreatedRideDTO;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.LocationDTO;
import com.example.uberapp_tim3.model.DTO.PassengerEmailDTO;
import com.example.uberapp_tim3.model.DTO.RouteDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.users.User;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmRideFragment extends Fragment {

    String departure;
    String destination;
    String dateTime;
    String passengers;
    String vehicleType;
    Boolean babyTransport;
    Boolean petTransport;
    Button btnOrderARide;
    private SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.passenger_confirm_ride, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            departure = getArguments().getString("departure");
            destination = getArguments().getString("destination");
            dateTime = getArguments().getString("dateTme");
            passengers = getArguments().getString("passengers");
            vehicleType = getArguments().getString("vehicleType");
            babyTransport = getArguments().getBoolean("babyTransport");
            petTransport = getArguments().getBoolean("petTransport");
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        departure = getArguments().getString("departure");
        destination = getArguments().getString("destination");
        dateTime = getArguments().getString("dateTime");
        passengers = getArguments().getString("passengers");
        vehicleType = getArguments().getString("vehicleType");
        babyTransport = getArguments().getBoolean("babyTransport");
        petTransport = getArguments().getBoolean("petTransport");
        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        TextView departureView = getView().findViewById(R.id.departure);
        departureView.setText(departure);
        TextView destinationView = getView().findViewById(R.id.destination);
        destinationView.setText(destination);
        TextView dateTimeView = getView().findViewById(R.id.chosenTime);
        dateTimeView.setText(dateTime);
        TextView passengersView = getView().findViewById(R.id.chosenPassengers);
        passengersView.setText(passengers);
        TextView vehicleTypeView = getView().findViewById(R.id.vehicleType);
        vehicleTypeView.setText(vehicleType);
        TextView babyTransportView = getView().findViewById(R.id.babyTransport);
        babyTransportView.setText(babyTransport ? "Yes" : "No");
        TextView petTransportView = getView().findViewById(R.id.petTransport);
        petTransportView.setText(petTransport ? "Yes" : "No");

        // Add the logic for drawing the route on the map

        btnOrderARide = getView().findViewById(R.id.btnOrderARide);
        btnOrderARide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Call<CreatedRideDTO> call =ServiceUtils.rideService.createARide(getCreatedRide());
                    call.enqueue(new Callback<CreatedRideDTO>() {
                        @Override
                        public void onResponse(Call<CreatedRideDTO> call, Response<CreatedRideDTO> response) {
                            if(!response.isSuccessful()) return;
                            Toast.makeText(getActivity(), "Successfully created ride", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CreatedRideDTO> call, Throwable t) {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private CreateRideDTO getCreatedRide() throws IOException {
        Set<PassengerEmailDTO> users = new HashSet<>();
        if(passengers.contains(",")) {
            passengers += ",";
        }
        passengers += preferences.getString("pref_email", "0");

        String[] passengersEmails = passengers.split(",");

        for (String email : passengersEmails) {
            Call<UserDTO> call = ServiceUtils.userService.findByEmail(email);
            call.enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    assert  response != null;
                    UserDTO user = response.body();
                    users.add(new PassengerEmailDTO(user.getId(), user.getEmail()));
                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {

                }
            });

        }
        RouteDTO routeDTO = new RouteDTO(getLocation(departure), getLocation(destination));
        LinkedHashSet<RouteDTO> locations = new LinkedHashSet<>();
        locations.add(routeDTO);
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        }
        LocalDateTime scheduledTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if(dateTime != null) scheduledTime = LocalDateTime.parse(dateTime, formatter);
            else scheduledTime = LocalDateTime.now();
        }
        return new CreateRideDTO(users, locations, vehicleType, babyTransport, petTransport, scheduledTime);
    }

    private LocationDTO getLocation(String address) throws IOException {
        LocationDTO location = new LocationDTO();
        location.setAddress(address);
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addresses = geocoder.getFromLocationName(address, 1);
        if (addresses.size() > 0) {
            double latitude = addresses.get(0).getLatitude();
            location.setLatitude(latitude);
            double longitude = addresses.get(0).getLongitude();
            location.setLongitude(longitude);
            Log.i("GEOCODER", "Latitude: " + latitude + ", Longitude: " + longitude);
        } else {
            Log.i("GEOCODER", "No address found");
        }
        return location;
    }

}
