package com.example.uberapp_tim3.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.uberapp_tim3.BuildConfig;
import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.activities.DriverMainActivity;
import com.example.uberapp_tim3.activities.NewRideNotificationActivity;
import com.example.uberapp_tim3.model.DTO.LocationDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RouteDTO;
import com.example.uberapp_tim3.model.DTO.VehicleLocationWithAvailabilityDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.RideSocketConfiguration;
import com.example.uberapp_tim3.tools.SimulationSocketConfiguration;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.disposables.Disposable;
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
    Handler handler = new Handler(Looper.getMainLooper());
    private boolean isSimulation;
    private Map<Long, MarkerOptions> carMarkers;
    public static SimulationSocketConfiguration simulationSocketConfiguration;
    public static RideSocketConfiguration rideSocketConfiguration;
    Marker marker = null;
    private SharedPreferences preferences;
    MarkerOptions carMarker;
    BitmapDescriptor panic;


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
        RouteDTO start;
        RouteDTO end;

        start = drive.getLocations().get(0);
        end = drive.getLocations().get(drive.getLocations().size() - 1);

        this.departure = new LatLng(start.getDeparture().getLatitude(), start.getDeparture().getLongitude());
        this.destination = new LatLng(end.getDestination().getLatitude(), end.getDestination().getLongitude());
        this.departureAddress = start.getDeparture().getAddress();
        this.destinationAddress = end.getDestination().getAddress();
        this.isSimulation = isSimulation;
        this.rideId = drive.getId();

    }

    public DrawRouteFragment(LocationDTO start, LocationDTO end) {
        this.departure = new LatLng(start.getLatitude(), start.getLongitude());
        this.destination = new LatLng(end.getLatitude(), end.getLongitude());
        this.departureAddress = start.getAddress();
        this.destinationAddress = end.getAddress();
        this.isSimulation = false;

    }


    public DrawRouteFragment(RideDTO ride, boolean isSimulation, LocationDTO locationDTO) {
        RouteDTO end;
        end = ride.getLocations().get(0);
        this.departure =  new LatLng(locationDTO.getLatitude(), locationDTO.getLongitude());
        this.destination =  new LatLng(end.getDeparture().getLatitude(), end.getDeparture().getLongitude());
        this.departureAddress = locationDTO.getAddress();
        this.destinationAddress = ride.getLocations().get(0).getDeparture().getAddress();
        this.isSimulation = isSimulation;
        this.rideId = ride.getId();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        simulationSocketConfiguration = new SimulationSocketConfiguration();
        simulationSocketConfiguration.connect();
        rideSocketConfiguration = new RideSocketConfiguration();
        rideSocketConfiguration.connect();
        mMapFragment = SupportMapFragment.newInstance();

        Button btnGetARide = getActivity().findViewById(R.id.btnGetARide);
        btnGetARide.setVisibility(View.INVISIBLE);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.map_container, mMapFragment).commit();
        mMapFragment.getMapAsync(this);
        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        if(isSimulation) {

            Log.d("SIMULATION", "ON RESUME");
            startSimulation();
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        panic = BitmapFromVector(R.drawable.ic_baseline_car_crash_36);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.map_layout, vg, false);

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
        BitmapDescriptor markerRed = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
        BitmapDescriptor markerGreen = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);

        mMap.addMarker(new MarkerOptions()
                .position(departure)
                .title(departureAddress)
                .snippet("Start")
                        .icon(markerGreen)
                    );
        mMap.addMarker(new MarkerOptions()
                .position(destination)
                .title(destinationAddress)
                .snippet("End")
                .icon(markerRed)
                );

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


    }

    @SuppressLint("CheckResult")
    void startSimulation(){
        simulate();
        Call<String> call =  ServiceUtils.vehicleService.startSimulation(this.rideId);
        call.enqueue(new Callback<String>() {
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
    private BitmapDescriptor BitmapFromVector(int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(getContext(), vectorResId);
        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    @SuppressLint("CheckResult")
    private void simulate() {


        carMarker =  new MarkerOptions().position(departure).title("Your ride")
                .icon(BitmapFromVector(R.drawable.ic_baseline_directions_car_36_black));
        rideSocketConfiguration.stompClient
                        .topic("/topic/panic/"+this.preferences.getLong("pref_id", 0))
                                .subscribe(message ->{
                                            handler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if(marker != null)
                                                        marker.remove();

                                                    carMarker.icon(panic);
                                                    marker = mMap.addMarker(carMarker);

                                                }
                                            });
                                },
                                        throwable -> {Log.d("PANIC", "ERROR");});


        simulationSocketConfiguration.stompClient
                        .topic("/topic/map-updates")
                        .subscribe(message -> {
                                    VehicleLocationWithAvailabilityDTO vehicle = new Gson().fromJson(message.getPayload(), VehicleLocationWithAvailabilityDTO.class);
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(marker != null)
                                                marker.remove();

                                            LatLng newPosition = new LatLng(vehicle.getLongitude(), vehicle.getLatitude());
                                            carMarker.position(newPosition);
                                            marker = mMap.addMarker(carMarker);

                                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newPosition, 15.0f));

                                        }
                                    });

                                },
                                throwable -> {Log.d("SOCKET ERROR",
                                        throwable.getMessage());
                                }
                        );

    }
}