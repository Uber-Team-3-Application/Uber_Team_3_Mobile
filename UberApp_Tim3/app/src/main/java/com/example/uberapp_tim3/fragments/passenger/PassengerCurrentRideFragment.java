package com.example.uberapp_tim3.fragments.passenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.ChatFragment;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.fragments.driver.DriverCurrentRideFragment;
import com.example.uberapp_tim3.fragments.driver.DriverEditInfoFragment;
import com.example.uberapp_tim3.fragments.driver.DriverInfoProfile;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.MessageBundleDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RideUserDTO;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.example.uberapp_tim3.tools.RideSocketConfiguration;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PassengerCurrentRideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PassengerCurrentRideFragment extends Fragment {
    private TextView tvHours, tvMinutes, tvSeconds;
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private int elapsedTime = 0;
    private Handler handler = new Handler();
    private Runnable runnable;
    private ImageView imgChatWithDriver;
    private SharedPreferences sharedPreferences;
    public static RideSocketConfiguration rideSocketConfiguration;



    public PassengerCurrentRideFragment() {
        // Required empty public constructor
    }

    public static PassengerCurrentRideFragment newInstance(String param1, String param2) {
        PassengerCurrentRideFragment fragment = new PassengerCurrentRideFragment();
        return fragment;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        rideSocketConfiguration.stompClient.topic("/topic/passenger/end-ride/" + sharedPreferences.getLong("pref_id", 0L))
                .subscribe(message ->{
                    RideDTO ride  = new Gson().fromJson(message.getPayload(), RideDTO.class);
                    if (ride.getStatus().equalsIgnoreCase("finished")) {
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerRideHistoryFragment()).addToBackStack(null).commit();
                    }
                    });
                }
    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        RideDTO rideDTO = null;
        sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        imgChatWithDriver = getActivity().findViewById(R.id.imgChatWithDriver);

        if (bundle != null)
            rideDTO = bundle.getParcelable("ride");
        setViews(rideDTO);

        initializeTime();
        startMeasuringTime();
        setPanicListener();
    }



    private void setPanicListener() {

        Button btnPanic = requireView().findViewById(R.id.btnPanic);
        btnPanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initializeTime(){
        tvHours = requireView().findViewById(R.id.tv_hour);
        tvMinutes = requireView().findViewById(R.id.tv_minute);
        tvSeconds = requireView().findViewById(R.id.tv_second);
    }


    private void startMeasuringTime() {
        runnable = new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
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
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);


    }

    private void setViews(RideDTO rideDTO) {
        assert rideDTO != null;
        requireActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.currentRideContainerDriver, new DrawRouteFragment(rideDTO)
        ).commit();

        TextView tvStartTime = requireActivity().findViewById(R.id.txtPassengerCurrentRideStartTime);
        TextView tvEndTIme = requireActivity().findViewById(R.id.txtPassengerCurrentRideEndTime);
        TextView tvDeparture = requireActivity().findViewById(R.id.txtPassengerCurrentRideDeparture);
        TextView tvDestination = requireActivity().findViewById(R.id.txtPassengerCurrentRideDestination);
        TextView tvPassengers = requireActivity().findViewById(R.id.txtPassengerCurrentRidePassengerCount);
        TextView tvPrice = requireActivity().findViewById(R.id.txtPassengerCurrentRidePrice);
        

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvStartTime.setText(sdf.format(rideDTO.getStartTime()));
        tvEndTIme.setText(sdf.format(rideDTO.getEndTime()));
        tvDeparture.setText(rideDTO.getLocations().get(0).getDeparture().getAddress());
        tvDestination.setText(rideDTO.getLocations().get(rideDTO.getLocations().size() - 1).getDestination().getAddress());
        String totalPassengers = Integer.toString(rideDTO.getPassengers().size());
        tvPassengers.setText(totalPassengers);
        String totalCost = Double.toString(rideDTO.getTotalCost());
        tvPrice.setText(totalCost);
        setDriver(rideDTO.getDriver());
        setInbox(rideDTO);
    }

    private void setInbox(RideDTO rideDTO) {
        imgChatWithDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long senderId = sharedPreferences.getLong("pref_id", 0);
                Long receiverId = rideDTO.getDriver().getId();
                Long rideId = rideDTO.getId();
                String messageType = "RIDE";
                MessageBundleDTO messageBundleDTO = new MessageBundleDTO(senderId, receiverId, rideId, messageType);
                Bundle args = new Bundle();
                args.putParcelable("message", messageBundleDTO);
                ChatFragment chatFragment = new ChatFragment();
                chatFragment.setArguments(args);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, chatFragment).addToBackStack(null).commit();

            }
        });
    }

    private void setDriver(RideUserDTO driver) {
        Bundle args = new Bundle();
        args.putLong("driverId", driver.getId());
        DriverInfoProfile profile = new DriverInfoProfile();
        profile.setArguments(args);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(
                R.id.lyDriverRideProfile, profile
        ).commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_current_ride, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);

    }
}