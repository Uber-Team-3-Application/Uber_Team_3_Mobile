package com.example.uberapp_tim3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.DrawerNavListAdapter;
import com.example.uberapp_tim3.fragments.MapFragment;
import com.example.uberapp_tim3.fragments.driver.DriverAccountFragment;
import com.example.uberapp_tim3.fragments.driver.DriverCurrentRideFragment;
import com.example.uberapp_tim3.fragments.driver.DriverInboxFragment;
import com.example.uberapp_tim3.fragments.driver.DriverRideHistoryFragment;
import com.example.uberapp_tim3.model.DTO.DriverActivityDTO;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.EndWorkingHoursDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.DTO.WorkingHoursDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.NavItem;
import com.example.uberapp_tim3.services.DriverMessagesService;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.example.uberapp_tim3.tools.RideSocketConfiguration;
import com.example.uberapp_tim3.tools.SimulationSocketConfiguration;
import com.google.gson.Gson;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverMainActivity extends AppCompatActivity {

    private final static String DRIVER_CHANEL = "Driver channel";
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerPane;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

    private String synctime;
    private boolean allowSync;

    private AlarmManager manager;
    private SharedPreferences sharedPreferences;
    static DriverMainActivity driverMainActivity;

    public static RideSocketConfiguration rideSocketConfiguration;



    public static DriverMainActivity getInstance(){
        if(driverMainActivity == null) driverMainActivity = new DriverMainActivity();
        return driverMainActivity;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_driver_main);
        prepareMenu(mNavItems);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerPane = findViewById(R.id.drawerPane);
        DrawerNavListAdapter adapter = new DrawerNavListAdapter(this, mNavItems);


        mDrawerList = findViewById(R.id.navList);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerList.setAdapter(adapter);

        mTitle = getTitle();

        Toolbar toolbar = findViewById(R.id.driver_main_toolbar);

        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setHomeButtonEnabled(true);
        }
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);


        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Reesen");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        createNotificationChannel();


        if (savedInstanceState == null)
            selectItemFromDrawer(4);

        setUpService();
        consultPreferences();
        initializeSockets();

    }

    
    @SuppressLint("CheckResult")
    private void initializeSockets() {

        long driverId = this.sharedPreferences.getLong("pref_id", 0);
        Log.d("Driver id", String.valueOf(driverId));
        rideSocketConfiguration = new RideSocketConfiguration();
        rideSocketConfiguration.connect();

        rideSocketConfiguration.stompClient
                .topic("/topic/driver/ride/" + driverId)
                .subscribe(message -> {

                    RideDTO ride = new Gson().fromJson(message.getPayload(), RideDTO.class);
                    if(ride.getStatus().equalsIgnoreCase("pending")){
                        setNotification(ride);
                    }

                },
                        throwable -> {Log.d("SOCKET ERROR",
                                throwable.getMessage());
                }
                );


    }

    private void changeActivity(Long id, boolean isActive) {
        Call<String> call = ServiceUtils.driverService.changeActivity(id, new DriverActivityDTO(isActive));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Toast.makeText(getApplicationContext(), "You are active!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.d("Fail", t.getMessage());
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        changeActivity(sharedPreferences.getLong("pref_id", 0L), false);


    }

    @Override
    protected void onResume() {
        super.onResume();
        getDriver(sharedPreferences.getLong("pref_id", 0L));
        changeActivity(sharedPreferences.getLong("pref_id", 0L), true);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean wifi = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

    }

    public UserDTO getDriver(Long id){

        Call<UserDTO> call = ServiceUtils.driverService.getDriver(id);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if(!response.isSuccessful()) return;
                UserDTO driver = response.body();

                TextView tvName = findViewById(R.id.userFullName);
                String fullName = driver.getName() + " " + driver.getSurname();
                tvName.setText(fullName);

                TextView tvPhoneNumber = findViewById(R.id.userPhoneNumber);
                tvPhoneNumber.setText(driver.getTelephoneNumber());
                if(!driver.getProfilePicture().contains(",")){return;}

                String base64Image = driver.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                CircleImageView cv = findViewById(R.id.userProfilePicture);
                cv.setImageBitmap(decodedByte);


            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });
        return null;
    }


    @Override
    protected void onPause() {
        super.onPause();
        //TODO: ACCOUNT SETTINGS
    }

    private void consultPreferences() {
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("pref_sync_list")){
            synctime = sharedPreferences.getString("pref_sync_list", "1");
            if(sharedPreferences.contains("pref_sync")){
                allowSync = sharedPreferences.getBoolean("pref_sync", false);
            }
        }
    }

    private void setPreferences(){
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editor = sharedPreferences.edit();
        if(!sharedPreferences.contains("pref_name")){
            sp_editor.putString("pref_name", "Ressen");
            sp_editor.apply();
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
        }
    }

    private void selectItemFromDrawer(int position) {


        if (position == 0) {
            FragmentTransition.to(DriverAccountFragment.newInstance(), this, true);
        } else if (position == 1) {
            FragmentTransition.to(DriverRideHistoryFragment.newInstance(), this, true);
        } else if (position == 2) {
            FragmentTransition.to(DriverInboxFragment.newInstance(), this, true);
        } else if (position == 4) {
            FragmentTransition.to(MapFragment.newInstance(), this, true);
        } else if (position == 5)
        {
            finishShift();
            this.finish();
        }

        mDrawerList.setItemChecked(position, true);
        if (position != 5) {
            setTitle(mNavItems.get(position).getmTitle());
        }
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    private void finishShift() {
        Call<WorkingHoursDTO> call = ServiceUtils.driverService.changeWorkingHours(
                this.sharedPreferences.getLong("pref_working_hour_id", 0), new EndWorkingHoursDTO(null));
        call.enqueue(new Callback<WorkingHoursDTO>() {
            @Override
            public void onResponse(Call<WorkingHoursDTO> call, Response<WorkingHoursDTO> response) {
                if(!response.isSuccessful()){
                    Log.d("Update WH fail", "FAIL");
                    return;
                }
                Log.d("FINISH SHIFT", "Successfully finished shift");

            }

            @Override
            public void onFailure(Call<WorkingHoursDTO> call, Throwable t) {
                Log.d("Update WH FATAL fail", "FAIL");
            }
        });
    }


    private void setUpService(){
        Intent alarmIntent = new Intent(this, DriverMessagesService.class);
        startService(alarmIntent);
        /*pendingIntent = PendingIntent.getService(this, 0, alarmIntent, 0);
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);*/
    }

    private void prepareMenu(ArrayList<NavItem> mNavItems ) {
        mNavItems.add(new NavItem(getString(R.string.driver_profile), getString(R.string.driver_profile), R.drawable.ic_baseline_person_24));
        mNavItems.add(new NavItem(getString(R.string.drive_history), getString(R.string.drive_history_long), R.drawable.ic_history));
        mNavItems.add(new NavItem(getString(R.string.driver_inbox), getString(R.string.driver_inbox_long), R.drawable.ic_message));
        mNavItems.add(new NavItem(getString(R.string.preferences), getString(R.string.preferences_long), R.drawable.ic_baseline_settings_24));
        mNavItems.add(new NavItem(getString(R.string.driver_back), getString(R.string.driver_back_long), R.drawable.ic_home));
        mNavItems.add(new NavItem(getString(R.string.log_out) , getString(R.string.logout), R.drawable.ic_logout));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "LOCATION GRANTED", Toast.LENGTH_SHORT).show();
                    }

                } else if (grantResults.length > 0
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "LOCATION GRANTED", Toast.LENGTH_SHORT).show();

                    }

                }
            }

        }
    }
    

    public void doTransition(DriverRideDTO ride){
        Bundle bundle = new Bundle();
        bundle.putParcelable("ride", ride);
        DriverCurrentRideFragment driverCurrentRideFragment = new DriverCurrentRideFragment();
        driverCurrentRideFragment.setArguments(bundle);
        FragmentTransition.to(driverCurrentRideFragment, this, true);

    }




    private void setNotification(RideDTO rideDTO) {
        String start = rideDTO.getLocations().get(0).getDeparture().getAddress();
        String end = rideDTO.getLocations().get(rideDTO.getLocations().size()-1).getDestination().getAddress();
        Intent intent = new Intent(this, NewRideNotificationActivity.class);
        intent.putExtra("ride",  rideDTO);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,DRIVER_CHANEL)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("New Ride")
                .setContentText(start + " - " + end)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(DRIVER_CHANEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}