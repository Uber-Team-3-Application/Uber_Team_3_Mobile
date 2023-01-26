package com.example.uberapp_tim3.tools;

import com.example.uberapp_tim3.BuildConfig;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class SimulationSocketConfiguration {
    public static StompClient stompClient;

    public void connect(){
        String newAddress = BuildConfig.LOCALHOST.substring(0, BuildConfig.LOCALHOST.length() - 4);
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP,
                newAddress+"vehicle-simulation/websocket");
        stompClient.connect();
    }
    public void disconnect(){
        stompClient.disconnect();
    }
}
