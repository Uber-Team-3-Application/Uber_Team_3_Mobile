package com.example.uberapp_tim3.tools;


import android.annotation.SuppressLint;
import android.util.Log;

import com.example.uberapp_tim3.BuildConfig;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.LifecycleEvent;

public class RideSocketConfiguration {
    public StompClient stompClient;

    @SuppressLint("CheckResult")
    public void connect(){
        String newAddress = BuildConfig.LOCALHOST.substring(0, BuildConfig.LOCALHOST.length() - 4);
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP,
                newAddress+"socket/websocket");
        Log.d("SOCKET ADRESA", newAddress+"socket/websocket");
        stompClient.lifecycle()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                                .subscribe(this::handleConnectionLifecycle);
        stompClient.connect();
    }

    private void handleConnectionLifecycle(LifecycleEvent event){
        switch (event.getType()) {

            case OPENED:
                Log.d("OPENED", "Stomp connection opened");
                break;

            case ERROR:
                Log.e("ERROR", "Error", event.getException());
                break;

            case CLOSED:
                Log.d("CLOSED", "Stomp connection closed");
                break;
        }
    }
    public void disconnect(){
        stompClient.disconnect();
    }
}
