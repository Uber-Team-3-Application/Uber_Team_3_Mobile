package com.example.uberapp_tim3.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.uberapp_tim3.BuildConfig;
import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RouteDTO;
import com.example.uberapp_tim3.model.DTO.VehicleLocationWithAvailabilityDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.SimulationSocketConfiguration;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawRouteFragment extends Fragment implements OnMapReadyCallback {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private GoogleMap mMap;
    private final String TAG = "so47492459";
    private SupportMapFragment mMapFragment;
    private final LatLng departure;
    private final LatLng destination;
    private final String departureAddress;
    private final String destinationAddress;
    private Long rideId;
    private boolean isSimulation;
    private SimulationSocketConfiguration simulationSocketConfiguration;
    private Map<Long, MarkerOptions> carMarkers;

    public DrawRouteFragment(RideDTO drive) {
        RouteDTO start = drive.getLocations().get(0);
        RouteDTO end = drive.getLocations().get(drive.getLocations().size()-1);
        this.departure = new LatLng(start.getDeparture().getLatitude(), start.getDeparture().getLongitude());
        this.destination = new LatLng(end.getDestination().getLatitude(), end.getDestination().getLongitude());
        this.departureAddress = start.getDeparture().getAddress();
        this.destinationAddress = end.getDestination().getAddress();
        this.isSimulation = false;
    }

    public DrawRouteFragment(RideDTO drive, boolean isSimulation) {
        RouteDTO start = drive.getLocations().get(0);
        RouteDTO end = drive.getLocations().get(drive.getLocations().size()-1);
        this.departure = new LatLng(start.getDeparture().getLatitude(), start.getDeparture().getLongitude());
        this.destination = new LatLng(end.getDestination().getLatitude(), end.getDestination().getLongitude());
        this.departureAddress = start.getDeparture().getAddress();
        this.destinationAddress = end.getDestination().getAddress();
        this.isSimulation = isSimulation;
        this.rideId = drive.getId();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapFragment = SupportMapFragment.newInstance();

        Button btnGetARide = getActivity().findViewById(R.id.btnGetARide);
        btnGetARide.setVisibility(View.INVISIBLE);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.map_container, mMapFragment).commit();
        mMapFragment.getMapAsync(this);
        startSimulation();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.map_layout, vg, false);
        simulationSocketConfiguration = new SimulationSocketConfiguration();
        simulationSocketConfiguration.connect();
        return view;
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(getActivity())
                        .setTitle("Allow user location")
                        .setMessage("To continue working we need your locations....Allow now?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{
                                                Manifest.permission.ACCESS_FINE_LOCATION,
                                                Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(departure).title(departureAddress));
        mMap.addMarker(new MarkerOptions().position(destination).title(destinationAddress));

        //Define list to get all latlng for the route
        List<LatLng> path = new ArrayList();

        if(checkLocationPermission()) {
            mMap.setMyLocationEnabled(true);
        }
        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(BuildConfig.MAPS_API_KEY)
                .build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, departure.latitude + "," + departure.longitude,
                destination.latitude + "," + destination.longitude);

        try {
            DirectionsResult res = req.await();
            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs !=null) {
                    for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
            mMap.addPolyline(opts);
        }

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : path)
            boundsBuilder.include(latLngPoint);

        int routePadding = 100;
        LatLngBounds latLngBounds = boundsBuilder.build();

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
        Call<String> call =  ServiceUtils.vehicleService.startSimulation(this.rideId);
        call.enqueue(new Callback<String>() {
            @SuppressLint("CheckResult")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.d("SIMULATION", "ERROR");
                }
                else{
                    Log.d("SIMULATION", "RUNNING");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("SIMULATION", "FATAL");
            }
        });
    }

    @SuppressLint("CheckResult")
    void startSimulation(){


        Bitmap customMarker = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_directions_car_24);

        simulationSocketConfiguration.stompClient
                .topic("/topic/map-updates")
                .subscribe(message -> {
                            VehicleLocationWithAvailabilityDTO vehicle = new Gson().fromJson(message.getPayload(), VehicleLocationWithAvailabilityDTO.class);
                            if(carMarkers.get(vehicle.getId()) == null) {
                                carMarkers.put(vehicle.getId(),
                                        new MarkerOptions().position(departure).title("Your ride")
                                                .icon(BitmapDescriptorFactory.fromBitmap(customMarker)));
                                mMap.addMarker(Objects.requireNonNull(carMarkers.get(vehicle.getId())));

                            }else{
                                LatLng newPosition = new LatLng(vehicle.getLatitude(), vehicle.getLongitude());
                                carMarkers.get(vehicle.getId()).position(newPosition);
                                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(newPosition);
                                mMap.animateCamera(cameraUpdate);

                            }


                        },
                        throwable -> {Log.d("SOCKET ERROR",
                                throwable.getMessage());
                        }
                );
    }
}