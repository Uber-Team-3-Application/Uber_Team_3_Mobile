package com.example.uberapp_tim3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.tools.ReesenTools;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    public Timer timer = new Timer();
    int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnectivity();
//        checkLocation();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, UserLoginActivity.class));
            }
        }, SPLASH_TIME_OUT);
    }

    private void checkLocation() {

    }

    private void checkConnectivity() {
        int status = ReesenTools.getConnectivityStatus(getApplicationContext());
        if (status != ReesenTools.TYPE_WIFI && status != ReesenTools.TYPE_MOBILE) {
            Toast.makeText(getApplicationContext(), "Device is not connected to the internet",
                    Toast.LENGTH_SHORT).show();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    finishAffinity();
                }
            }, 1000);
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
        timer.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}