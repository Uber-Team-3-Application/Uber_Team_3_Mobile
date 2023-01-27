package com.example.uberapp_tim3.fragments.passenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.PassengerRideHistoryAdapter;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DrivesMockUp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerRideHistoryFragment extends ListFragment {

    public PassengerRideHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Drive> drives = DrivesMockUp.getDrives();
        PassengerRideHistoryAdapter adapter = new PassengerRideHistoryAdapter(getActivity(), drives);
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_ride_history, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.drive_history);
    }

    @Override
    public void onPause() {
        super.onPause();
        requireActivity().setTitle(R.string.drive_history);
    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Call<DriverRideDTO> call = ServiceUtils.rideService.getRide(1L);
        // TODO: OVO JE RADI PROBE, IZMENITI

        call.enqueue(new Callback<DriverRideDTO>() {
            @Override
            public void onResponse(Call<DriverRideDTO> call, Response<DriverRideDTO> response) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("ride", response.body());
                PassengerRideInfoFragment rideInfoFragment = new PassengerRideInfoFragment();
                rideInfoFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rideInfoFragment).addToBackStack(null).commit();

            }

            @Override
            public void onFailure(Call<DriverRideDTO> call, Throwable t) {

            }
        });

    }


}