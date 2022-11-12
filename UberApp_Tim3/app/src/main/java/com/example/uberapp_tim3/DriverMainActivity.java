package com.example.uberapp_tim3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.uberapp_tim3.model.NavItem;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.util.ArrayList;

public class DriverMainActivity extends AppCompatActivity {

    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerPane;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        prepareMenu(mNavItems);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerPane = findViewById(R.id.drawerPane);
        com.example.vezbe4.adapters.DrawerListAdapter adapter = new com.example.vezbe4.adapters.DrawerListAdapter(this, mNavItems);


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

        ToggleButton tbChangeActivity = findViewById(R.id.btnToggleOnline);

        tbChangeActivity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String text = "Current status: ";
                if(b) text += "Online";
                else text += "Offline";
                Toast.makeText(DriverMainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

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
                getSupportActionBar().setTitle("Uber");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };



    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItemFromDrawer(position);
        }
    }

    private void selectItemFromDrawer(int position) {

        if (position == 0) {
            Intent intent = new Intent(this, DriverAccountActivity.class);
            startActivity(intent);
        } else if (position == 1) {
            Intent intent = new Intent(this, DriverRideHistoryActivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(this, DriverInboxActivity.class);
            startActivity(intent);
        } else if (position == 3) {
            Intent intent = new Intent(this, DriverMainActivity.class);
            startActivity(intent);
        }

            mDrawerList.setItemChecked(position, true);
            if (position != 5) {
                setTitle(mNavItems.get(position).getmTitle());
            }
            mDrawerLayout.closeDrawer(mDrawerPane);

    }

    private void prepareMenu(ArrayList<NavItem> mNavItems ) {
        mNavItems.add(new NavItem(getString(R.string.driver_profile), getString(R.string.driver_profile), R.drawable.ic_baseline_person_24));
        mNavItems.add(new NavItem(getString(R.string.drive_history), getString(R.string.drive_history_long), R.drawable.ic_history));
        mNavItems.add(new NavItem(getString(R.string.driver_inbox), getString(R.string.driver_inbox_long), R.drawable.ic_message));
        mNavItems.add(new NavItem(getString(R.string.driver_back), getString(R.string.driver_back_long), R.drawable.ic_home));
    }
}