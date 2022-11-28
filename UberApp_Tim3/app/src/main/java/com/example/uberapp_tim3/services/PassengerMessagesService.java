package com.example.uberapp_tim3.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.uberapp_tim3.activities.PassengerMainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PassengerMessagesService extends Service {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());
    final int delay = 3 * 60 * 1000;

    public PassengerMessagesService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        executor.execute(() -> {
            //Background work here
            Log.i("MESSAGE", "New Passenger Messages Incoming");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i("MESSAGE", "New Passenger Messages Incoming");
                    Intent ints = new Intent(PassengerMainActivity.SYNC_DATA);
                    getApplicationContext().sendBroadcast(ints);
                    handler.postDelayed(this, delay);
                }
            }, delay) ;

        });

        stopSelf();
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}