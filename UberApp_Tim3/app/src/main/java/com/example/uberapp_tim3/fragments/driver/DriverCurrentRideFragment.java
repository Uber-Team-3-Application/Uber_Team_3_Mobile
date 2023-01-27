package com.example.uberapp_tim3.fragments.driver;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.ChatFragment;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.fragments.MapFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerInfoProfile;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.MessageBundleDTO;
import com.example.uberapp_tim3.model.DTO.ReasonDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RideUserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.google.maps.model.Unit;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverCurrentRideFragment extends Fragment {

    private TextView tvHours, tvMinutes, tvSeconds;
    private Handler handler = new Handler();
    private Runnable runnable;
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private int elapsedTime = 0;
    RideDTO rideDTO = null;
    private SharedPreferences preferences;
    public DriverCurrentRideFragment() {
        // Required empty public constructor
    }

    public static DriverCurrentRideFragment newInstance(String param1, String param2) {
        DriverCurrentRideFragment fragment = new DriverCurrentRideFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null)
            rideDTO = bundle.getParcelable("ride");
        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        setViews(rideDTO);
        initializeTime();
        startMeasuringTime();
        setOnClickListeners();
    }


    private void setOnClickListeners(){
        setFinishRideListener();
        setPanicListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }


    private void setPanicListener() {
        Button btnPanic = getActivity().findViewById(R.id.btnDriverCurrentRidePanic);
        EditText txtPanic = getActivity().findViewById(R.id.txtPanicReason);
        btnPanic.setOnClickListener(view -> {
            String reason = txtPanic.getText().toString().trim();
            if(reason.equalsIgnoreCase("")){
                Toast.makeText(getActivity(), "Please enter a reason.", Toast.LENGTH_SHORT).show();
                return;
            }
            Call<RideDTO> call = ServiceUtils
                    .rideService.panicRide(rideDTO.getId(),
                                        new ReasonDTO(reason));
            call.enqueue(new Callback<RideDTO>() {
                @Override
                public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                    if(!response.isSuccessful()){

                        Toast.makeText(getActivity(), "Something went wrong with the response, try again!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    assert response.body() != null;
                    Toast.makeText(getActivity(), "Support contacted, stay safe.", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<RideDTO> call, Throwable t) {
                    Toast.makeText(getActivity(), "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                }
            });


        });
    }

    private void setFinishRideListener() {
        Button finishRide = getActivity().findViewById(R.id.btnFinishRideDriver);
        finishRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                Call<DriverRideDTO> call = ServiceUtils.rideService.endRide(rideDTO.getId());
                call.enqueue(new Callback<DriverRideDTO>() {
                    @Override
                    public void onResponse(Call<DriverRideDTO> call, Response<DriverRideDTO> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(getContext(), "Ride successfully finished!", Toast.LENGTH_SHORT).show();
                        FragmentTransition.to(MapFragment.newInstance(), getActivity(), true);
                    }

                    @Override
                    public void onFailure(Call<DriverRideDTO> call, Throwable t) {
                        Log.d("Ride finish fail", "Something went wrong");
                    }
                });
            }
        });
    }

    private void startMeasuringTime() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    int hours  = elapsedTime/3600;
                    int minutes = elapsedTime/60;
                    int seconds = elapsedTime % 60;
                    tvHours.setText(String.format("%02d", hours));
                    tvMinutes.setText(String.format("%02d", minutes));
                    tvSeconds.setText(String.format("%02d", seconds));
                    elapsedTime++;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Current Ride");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private void initializeTime(){
        tvHours = getActivity().findViewById(R.id.tv_hour);
        tvMinutes = getActivity().findViewById(R.id.tv_minute);
        tvSeconds = getActivity().findViewById(R.id.tv_second);
    }

    private void setViews(RideDTO rideDTO) {

        assert rideDTO != null;
        requireActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.currentRideContainerDriver, new DrawRouteFragment(rideDTO, true)
        ).commit();

        TextView tvStartTime = getActivity().findViewById(R.id.txtDriverCurrentRideStartTime);
        TextView tvEndTIme = getActivity().findViewById(R.id.txtDriverCurrentRideEndTime);
        TextView tvDeparture = getActivity().findViewById(R.id.txtDriverCurrentRideDeparture);
        TextView tvDestination = getActivity().findViewById(R.id.txtDriverCurrentRideDestination);
        TextView tvPassengers = getActivity().findViewById(R.id.txtDriverCurrentRidePassengerCount);
        TextView tvPrice = getActivity().findViewById(R.id.txtDriverCurrentRidePrice);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date today = new Date();
        tvStartTime.setText(sdf.format(today));
        // TODO: ZASTO BEK VRACA NULL ZA END TIME ?
//        tvEndTIme.setText(sdf.format(rideDTO.getEndTime()));
        tvDeparture.setText(rideDTO.getLocations().get(0).getDeparture().getAddress());
        tvDestination.setText(rideDTO.getLocations().get(rideDTO.getLocations().size() - 1).getDestination().getAddress());
        String totalPassengers = Integer.toString(rideDTO.getPassengers().size());
        tvPassengers.setText(totalPassengers);
        String totalCost = Double.toString(rideDTO.getTotalCost());
        tvPrice.setText(totalCost);

        setPassengers(rideDTO);

    }

    private void setPassengers(RideDTO rideDTO) {
        LinearLayout lyPassengers = getActivity().findViewById(R.id.lyDriverCurrentRidePassengers);
        LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int order = 1;
        for(RideUserDTO passenger: rideDTO.getPassengers()){
            View passengerView = inflater.inflate(R.layout.passenger_item, (ViewGroup) getView(), false);
            TextView twOrderNumber = passengerView.findViewById(R.id.txtRidePassengerOrder);
            String orderText = Integer.toString(order) + ".";
            twOrderNumber.setText(orderText);
            setListenerForProfiles(passengerView, passenger.getId());
            setPassengerMessagesView(passenger.getEmail(), passenger.getId());
            TextView twEmail = passengerView.findViewById(R.id.txtRidePassengerEmail);
            twEmail.setText(passenger.getEmail());
            order++;
            lyPassengers.addView(passengerView);
        }
    }

    private void setListenerForProfiles(View profileView, Long passengerId) {

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putLong("passengerId", passengerId);
                PassengerInfoProfile profile = new PassengerInfoProfile();
                profile.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.lySelectedPassengerProfile, profile
                ).commit();


            }
        });


    }

    private void setPassengerMessagesView(String email, Long id) {
        LinearLayout lyPassengers = getActivity().findViewById(R.id.lySendMessageToPassenger);
        LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View passengerView = inflater.inflate(R.layout.passenger_send_message, (ViewGroup) getView(), false);
        TextView txtRidePassengerOrder = passengerView.findViewById(R.id.txtRidePassengerOrder);
        txtRidePassengerOrder.setText(email);
        passengerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Long senderId = preferences.getLong("pref_id", 0);
                Long receiverId = id;
                Long rideId = rideDTO.getId();
                String messageType = "RIDE";
                MessageBundleDTO messageBundleDTO = new MessageBundleDTO(senderId, receiverId, rideId, messageType);
                Bundle args = new Bundle();
                args.putParcelable("message", messageBundleDTO);
                ChatFragment chatFragment = new ChatFragment();
                chatFragment.setArguments(args);
                FragmentTransition.to(chatFragment, requireActivity(), true);

            }
        });
        lyPassengers.addView(passengerView);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_current_ride, container, false);
    }
}