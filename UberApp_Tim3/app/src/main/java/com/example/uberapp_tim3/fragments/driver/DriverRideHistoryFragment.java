package com.example.uberapp_tim3.fragments.driver;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.DriverRidesListAdapter;
import com.example.uberapp_tim3.fragments.DrawRouteFragment;
import com.example.uberapp_tim3.fragments.DriveItemDetailFragment;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.RouteDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DrivesMockUp;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.google.android.gms.maps.model.LatLng;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class DriverRideHistoryFragment extends ListFragment {
    private SharedPreferences sharedPreferences;
    private Paginated<DriverRideDTO> rides;
    public static DriverRideHistoryFragment newInstance() {
        return new DriverRideHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        sharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        Call<Paginated<DriverRideDTO>> call = ServiceUtils.driverService.
                getRides(sharedPreferences.getLong("pref_id", 0L), 0, 50, "timeOfStart,desc");

        call.enqueue(new Callback<Paginated<DriverRideDTO>>() {
            @Override
            public void onResponse(@NonNull Call<Paginated<DriverRideDTO>> call,
                                   @NonNull Response<Paginated<DriverRideDTO>> response) {

                if(!response.isSuccessful()){
                    rides = new Paginated<DriverRideDTO>(0);
                    return;
                }
                rides = response.body();
                DriverRidesListAdapter adapter = new DriverRidesListAdapter(getActivity(), rides);
                setListAdapter(adapter);

            }

            @Override
            public void onFailure(@NonNull Call<Paginated<DriverRideDTO>> call, @NonNull Throwable t) {
                Log.d("Fail", "Failed fetching driver rides");
            }
        });
        return inflater.inflate(R.layout.list_layout, vg, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.drive_history);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        DriverRideDTO drive = rides.getResults().get(position);

        Bundle args = new Bundle();
        args.putLong("driveId", drive.getId());
        DriveItemDetailFragment driveItemDetail = new DriveItemDetailFragment();
        driveItemDetail.setArguments(args);
        FragmentTransition.to(driveItemDetail, requireActivity(), true);


    }


}