package com.example.uberapp_tim3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.uberapp_tim3.model.Driver;
import com.example.uberapp_tim3.tools.DriverMockup;

public class DriverAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account);
        setTextViews();
    }


    private void setTextViews(){
        Driver driver = DriverMockup.getDrivers().get(0);

        TextView txtFullName = findViewById(R.id.txtdriverFullName);
        txtFullName.setText(String.format("%s %s", driver.getName(), driver.getLastName()));

        TextView txtPhoneNumber = findViewById(R.id.txtdriverPhoneNumber);
        txtPhoneNumber.setText(driver.getPhoneNumber());

        TextView txtEmailAddress = findViewById(R.id.txtdriverEmail);
        txtEmailAddress.setText(driver.getEmailAddress());

        TextView tvBlocked = findViewById(R.id.txtDriverBlocked);
        if(!driver.isBlocked())
            tvBlocked.setText("");
        else {
            tvBlocked.setText(R.string.blocked_text);
            tvBlocked.setTextColor(Color.RED);
        }
    }

}