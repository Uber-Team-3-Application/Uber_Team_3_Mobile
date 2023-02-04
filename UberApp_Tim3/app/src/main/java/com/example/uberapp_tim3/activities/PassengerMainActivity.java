
package com.example.uberapp_tim3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.MapFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerAcceptedRide;
import com.example.uberapp_tim3.fragments.passenger.PassengerAccountFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerCurrentRideFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerFavouriteRoutesFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerInboxFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerRejectedRide;
import com.example.uberapp_tim3.fragments.passenger.PassengerReportFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerRideHistoryFragment;
import com.example.uberapp_tim3.fragments.passenger.RateDriverFragment;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.services.PassengerMessagesService;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.RideSocketConfiguration;
import com.example.uberapp_tim3.tools.SimulationSocketConfiguration;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final static String PASSENGER_CHANEL = "Passenger channel";
    private DrawerLayout drawer;
    public static String SYNC_DATA = "SYNC_DATA";
    public static RideSocketConfiguration rideSocketConfiguration;
    public static SimulationSocketConfiguration simulationSocketConfiguration;
    //Sync stuff
    private PendingIntent pendingIntent;
    private AlarmManager manager;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        this.getPassenger(sharedPreferences.getLong("pref_id", 0L));

        
        createNotificationChannel();
        setUpService();
        initializeSockets();
    }

    @SuppressLint("CheckResult")
    private void initializeSockets() {
        rideSocketConfiguration = new RideSocketConfiguration();
        rideSocketConfiguration.connect();

        rideSocketConfiguration.stompClient
                .topic("/topic/passenger/ride/" + this.sharedPreferences.getLong("pref_id", 0))
                .subscribe(message ->{

                    if(message.getPayload().equals("No suitable driver found!"))
                    {
                        notifyForRejectedRide();
                        PassengerRejectedRide rejectedRide = new PassengerRejectedRide();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rejectedRide).addToBackStack(null).commit();
                    } else if(message.getPayload().equals("You have a scheduled ride!"))
                    {
                        Toast.makeText(this, "You have a scheduled ride!", Toast.LENGTH_LONG);
                    }

                    else {
                        Log.d("PAYLOADD", message.getPayload());
                        Log.d("PAYLOADD", "No suitable driver found!");
                        RideDTO ride = new Gson().fromJson(message.getPayload(), RideDTO.class);
                        if (ride.getStatus().equalsIgnoreCase("accepted")) {
                            notifyForAcceptedRide();
                            PassengerAcceptedRide currentRideFragment = new PassengerAcceptedRide();
                            Bundle args = new Bundle();
                            args.putParcelable("ride", ride);
                            currentRideFragment.setArguments(args);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, currentRideFragment).addToBackStack(null).commit();

                        } else if (ride.getStatus().equalsIgnoreCase("rejected")) {
                            notifyForRejectedRide();
                            PassengerRejectedRide rejectedRide = new PassengerRejectedRide();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rejectedRide).addToBackStack(null).commit();

                        }
                    }

                });

        rideSocketConfiguration.stompClient
                .topic("/topic/passenger/start-ride/" + this.sharedPreferences.getLong("pref_id", 0))
                .subscribe( message ->{
                    notifyForStartedRide();
                    RideDTO ride  = new Gson().fromJson(message.getPayload(), RideDTO.class);
                    PassengerCurrentRideFragment currentRideFragment = new PassengerCurrentRideFragment();
                    Bundle args = new Bundle();
                    args.putParcelable("ride", ride);
                    currentRideFragment.setArguments(args);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, currentRideFragment).addToBackStack(null).commit();


                });

        rideSocketConfiguration.stompClient
                .topic("/topic/passenger/end-ride/" + this.sharedPreferences.getLong("pref_id", 0))
                .subscribe( message ->{
                    System.out.println(message);
                    RideDTO ride  = new Gson().fromJson(message.getPayload(), RideDTO.class);
                    if (ride.getStatus().equalsIgnoreCase("finished")) {
                        rideIsFinishedNotification(ride);
                        Bundle args = new Bundle();
                        args.putParcelable("ride", ride);
                        RateDriverFragment rateDriverFragment = new RateDriverFragment();
                        rateDriverFragment.setArguments(args);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                rateDriverFragment).addToBackStack(null).commit();

                    }

                });

        simulationSocketConfiguration = new SimulationSocketConfiguration();
        simulationSocketConfiguration.connect();

    }

    private void notifyForStartedRide() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,PASSENGER_CHANEL)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Your ride is started!")
                .setContentText("We wish you a pleasant ride...");

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    private void notifyForRejectedRide() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,PASSENGER_CHANEL)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Your ride is rejected!")
                .setContentText("Please try later...");

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    private void notifyForAcceptedRide() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,PASSENGER_CHANEL)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Your ride is accepted!")
                .setContentText("Please wait to driver arrived");

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_inbox:
                setTitle("Inbox");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerInboxFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_home:
                setTitle("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MapFragment.newInstance()).addToBackStack(null).commit();
                break;
            case R.id.nav_profile:
                setTitle("Account");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerAccountFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_report:
                setTitle("Reports");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerReportFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_history:
                setTitle("Ride History");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerRideHistoryFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_favorite:
                setTitle("Favourite Routes");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerFavouriteRoutesFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_logout: {
                this.finish();
                break;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        boolean wifi = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

    }


    private void getPassenger(Long id){

        Call<PassengerDTO> call = ServiceUtils.passengerService.getPassenger(id);
        call.enqueue(new Callback<PassengerDTO>() {
            @Override
            public void onResponse(@NonNull Call<PassengerDTO> call, @NonNull Response<PassengerDTO> response) {
                if(!response.isSuccessful()) return;
                setMapFragment();

                PassengerDTO passenger = response.body();
                TextView tvName = findViewById(R.id.passengerNameNavigation);
                assert passenger != null;
                String fullName = passenger.getName() + " " + passenger.getSurname();
//                tvName.setText(fullName);

                TextView tvPhoneNumber = findViewById(R.id.passengerPhoneNavigation);
//                tvPhoneNumber.setText(passenger.getTelephoneNumber());


                if(!passenger.getProfilePicture().contains(",")){return;}

                String base64Image = passenger.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                CircleImageView cv = findViewById(R.id.passengerProfilePictureNavigation);
//                cv.setImageBitmap(decodedByte);

            }

            @Override
            public void onFailure(Call<PassengerDTO> call, Throwable t) {
                Log.d("FAIIIL", t.getMessage());
                Log.d("FAIIIL", "BLATRUC");
            }
        });
    }

    private void setMapFragment() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MapFragment.newInstance()).addToBackStack(null).commit();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    private void rideIsFinishedNotification(RideDTO rideDTO) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,PASSENGER_CHANEL)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Your ride is finished!")
                .setContentText("You can now rate the driver and the vehicle");

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(PASSENGER_CHANEL, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setUpService(){
        Intent alarmIntent = new Intent(this, PassengerMessagesService.class);
        startService(alarmIntent);
        /*pendingIntent = PendingIntent.getService(this, 0, alarmIntent, 0);
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);*/
    }
}