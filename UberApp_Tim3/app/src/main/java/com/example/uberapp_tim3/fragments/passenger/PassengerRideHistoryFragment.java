package com.example.uberapp_tim3.fragments.passenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.DriverRidesListAdapter;
import com.example.uberapp_tim3.adapters.PassengerRideHistoryAdapter;
import com.example.uberapp_tim3.fragments.DriveItemDetailFragment;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.PassengerRideDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DrivesMockUp;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerRideHistoryFragment extends ListFragment {
    private Paginated<PassengerRideDTO> rides;

    public PassengerRideHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        Call<Paginated<PassengerRideDTO>> call = ServiceUtils.passengerService.
                getRides(sharedPreferences.getLong("pref_id", 0L), 0, 50, "timeOfStart,desc");
        call.enqueue(new Callback<Paginated<PassengerRideDTO>>() {
            @Override
            public void onResponse(Call<Paginated<PassengerRideDTO>> call, Response<Paginated<PassengerRideDTO>> response) {
                if(!response.isSuccessful()){
                    rides = new Paginated<>(0);
                    return;
                }
                rides = response.body();
                PassengerRideHistoryAdapter adapter = new PassengerRideHistoryAdapter(requireActivity(), rides);
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Paginated<PassengerRideDTO>> call, Throwable t) {
                Log.e("FAIL", t.getMessage());
            }
        });

        return inflater.inflate(R.layout.fragment_passenger_ride_history, vg, false);

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

        PassengerRideDTO drive = rides.getResults().get(position);
        System.out.println(drive.toString());
        System.out.println("position: " + position);

        Bundle args = new Bundle();
        args.putParcelable("ride", drive);
        PassengerRideInfoFragment driveItemDetail = new PassengerRideInfoFragment();
        driveItemDetail.setArguments(args);
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, driveItemDetail).addToBackStack(null).commit();

    }


}