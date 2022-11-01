package com.example.uberapp_tim3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DriverMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);


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
    }
}