package com.example.uberapp_tim3.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.uberapp_tim3.DriverAccountActivity;
import com.example.uberapp_tim3.DriverInboxActivity;
import com.example.uberapp_tim3.DriverMainActivity;
import com.example.uberapp_tim3.DriverRideHistoryActivity;
import com.example.uberapp_tim3.MainActivity;
import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.UserLoginActivity;

public class DriverFragment extends ListFragment {
    public static DriverFragment newInstance() {
        return new DriverFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.map_layout, vg, false);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.driver_activity_itemdetail, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.ride_history){
            Intent intent = new Intent(getContext(), DriverRideHistoryActivity.class);
            startActivity(intent);
        }
//        if(id == R.id.driver_inbox){
//            Intent intent = new Intent(getContext(), DriverInboxActivity.class);
//            startActivity(intent);
//        }
//        if (id == R.id.driver_profile) {
//            Intent intent = new Intent(getContext(), DriverAccountActivity.class);
//            startActivity(intent);
//        }
//
//        if (id == R.id.back_to_drive_main) {
//
//            Intent intent = new Intent(getContext(), DriverMainActivity.class);
//            startActivity(intent);
//        }
        return super.onOptionsItemSelected(item);
    }


}
