package com.example.uberapp_tim3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.ChatFragment;
import com.example.uberapp_tim3.fragments.CommentsFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerAccountFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerFavouriteRoutesFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerInboxFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerHomeFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerReportFragment;
import com.example.uberapp_tim3.fragments.passenger.PassengerRideHistoryFragment;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.PassengerMessagesService;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DrivesMockUp;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final static String PASSENGER_CHANEL = "Passenger channel";
    private DrawerLayout drawer;
    public static String SYNC_DATA = "SYNC_DATA";

    //Sync stuff
    private PendingIntent pendingIntent;
    private AlarmManager manager;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        createNotificationChannel();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerHomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        setUpService();
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerHomeFragment()).addToBackStack(null).commit();
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
            case R.id.nav_logout:
                this.finish();
                break;
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

    public void openChat() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).addToBackStack(null).commit();
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
        this.getPassenger(sharedPreferences.getLong("pref_id", 0));
    }


    public PassengerDTO getPassenger(Long id){

        Call<PassengerDTO> call = ServiceUtils.passengerService.getPassenger(id);
        call.enqueue(new Callback<PassengerDTO>() {
            @Override
            public void onResponse(Call<PassengerDTO> call, Response<PassengerDTO> response) {
                if(!response.isSuccessful()) return;
                PassengerDTO passenger = response.body();

                TextView tvName = findViewById(R.id.passengerNameNavigation);
                String fullName = passenger.getName() + " " + passenger.getSurname();
                tvName.setText(fullName);

                TextView tvPhoneNumber = findViewById(R.id.passengerPhoneNavigation);
                tvPhoneNumber.setText(passenger.getTelephoneNumber());

                if(!passenger.getProfilePicture().contains(",")){return;}

                String base64Image = passenger.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                CircleImageView cv = findViewById(R.id.passengerProfilePictureNavigation);
                cv.setImageBitmap(decodedByte);


            }

            @Override
            public void onFailure(Call<PassengerDTO> call, Throwable t) {
                Log.d("FAIIIL", t.getMessage());
                Log.d("FAIIIL", "BLATRUC");
            }
        });
        return null;
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