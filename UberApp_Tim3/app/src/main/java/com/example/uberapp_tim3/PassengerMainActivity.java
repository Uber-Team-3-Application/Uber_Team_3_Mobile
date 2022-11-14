package com.example.uberapp_tim3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uberapp_tim3.fragments.PassengerAccountFragment;
import com.example.uberapp_tim3.fragments.PassengerFavouriteRoutesFragment;
import com.example.uberapp_tim3.fragments.PassengerInboxFragment;
import com.example.uberapp_tim3.fragments.PassengerMapFragment;
import com.example.uberapp_tim3.fragments.PassengerReportFragment;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.google.android.material.navigation.NavigationView;

public class PassengerMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerMapFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerMapFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_profile:
                setTitle("Account");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerAccountFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_report:
                setTitle("Reports");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PassengerReportFragment()).addToBackStack(null).commit();
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
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}