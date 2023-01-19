package com.example.uberapp_tim3.fragments.passenger;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.FavouriteRideDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PassengerFavouriteRoutesFragment extends Fragment {

    private LinearLayout favouriteRoutesLayout;


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

            favouriteName.setText(ride.getFavoriteName());
            from.setText(ride.getLocations().get(0).getAddress());
            to.setText(ride.getLocations().get(ride.getLocations().size() - 1).getAddress());
            vehicleType.setText(ride.getVehicleType());

            setAdditionalInfo(ride, singleRide);

            favouriteRoutesLayout.addView(singleRide);


        }

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
}