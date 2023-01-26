package com.example.uberapp_tim3.tools;

import android.util.Log;

import com.example.uberapp_tim3.BuildConfig;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class WebSocketConfiguration {
    public static StompClient stompClient;

    public WebSocketConfiguration(String type){
        String newAddress = BuildConfig.LOCALHOST.substring(0, BuildConfig.LOCALHOST.length() - 4);
        Log.d("ADRESA", newAddress);
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP,
                newAddress+type);
        stompClient.connect();
    }
}
