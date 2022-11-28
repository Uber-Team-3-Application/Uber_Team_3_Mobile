package com.example.uberapp_tim3.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DriverMessagesService extends Service {
    public DriverMessagesService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}