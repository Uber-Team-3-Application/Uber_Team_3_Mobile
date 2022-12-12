package com.example.uberapp_tim3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.DriverDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        TextView tvRegister = findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        getDriver(findViewById(R.id.editTxtEmail));
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLoginActivity.this, PassengerRegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                String etUser = ((EditText) findViewById(R.id.editTxtEmail)).getText().toString();
                String etPw = ((EditText)findViewById(R.id.editTxtPassword)).getText().toString();
                if(etUser.equals("nebojsa") && etPw.equals("vuga")){
                    setSharedPreferences("PASSENGER", etUser);

                    intent = new Intent(UserLoginActivity.this, PassengerMainActivity.class);
                }
                else {
                    setSharedPreferences("DRIVER", etUser);
                    intent = new Intent(UserLoginActivity.this, DriverMainActivity.class);
                }
                startActivity(intent);
            }
        });

        EditText password = findViewById(R.id.editTxtPassword);
        password.setHint("password123");
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    password.setHint("");
                else
                    password.setHint("password123");
            }
        });
    }

    private void setSharedPreferences(String role, String email){
        this.sharedPreferences = getSharedPreferences("com.example.uberapp_tim3_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = this.sharedPreferences.edit();
        spEditor.putString("pref_role", role);
        spEditor.putString("pref_email", email);
        spEditor.commit();

    }

    public void getDriver(EditText username){
        Call<DriverDTO> call = ServiceUtils.driverService.getDriver(Long.parseLong("2"));
        call.enqueue(new Callback<DriverDTO>() {
            @Override
            public void onResponse(Call<DriverDTO> call, Response<DriverDTO> response) {
                if(!response.isSuccessful()) return;
                    DriverDTO newDriver = response.body();
                    Log.d("REZ","Meesage recieved");
                    username.setText(newDriver.getEmail());
            }

            @Override
            public void onFailure(Call<DriverDTO> call, Throwable t) {
                Log.d("FAIIIL", "Grijeska");
            }
        });
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
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}