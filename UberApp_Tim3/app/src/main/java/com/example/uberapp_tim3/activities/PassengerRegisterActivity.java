package com.example.uberapp_tim3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;
import com.example.uberapp_tim3.model.DTO.PassengerDTO;
import com.example.uberapp_tim3.model.mockup.Passenger;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);

        Button btnPassengerMain = findViewById(R.id.btnRegisterUser);

        btnPassengerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPassenger();
            }
        });


        ImageView ivGoogle = findViewById(R.id.ivGoogleReg);
        ImageView ivFacebook = findViewById(R.id.ivFacebookReg);

        ivGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PassengerRegisterActivity.this, "Functionality not implemented!", Toast.LENGTH_SHORT).show();
            }
        });
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PassengerRegisterActivity.this, "Functionality not implemented!", Toast.LENGTH_SHORT).show();
            }
        });

        EditText password = findViewById(R.id.txtPassword);
        EditText repeatPassword = findViewById(R.id.txtPasswordRepeat);

        password.setHint("password123");
        repeatPassword.setHint("password123");
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    password.setHint("");
                else
                    password.setHint("password123");
            }
        });
        repeatPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    repeatPassword.setHint("");
                else
                    repeatPassword.setHint("password123");
            }
        });
    }

    private void createPassenger() {
        EditText txtName = findViewById(R.id.txtName);
        String firstName = txtName.getText().toString();

        EditText txtLastName = findViewById(R.id.txtLastName);
        String lastName = txtLastName.getText().toString();

        EditText txtPhone = findViewById(R.id.txtPhone);
        String phone = txtPhone.getText().toString();

        EditText txtEmail = findViewById(R.id.txtEmail);
        String email = txtEmail.getText().toString();

        EditText txtAddress = findViewById(R.id.txtAddress);
        String address = txtAddress.getText().toString();

        EditText txtPassword = findViewById(R.id.txtPassword);
        String password = txtPassword.getText().toString();

        EditText txtRepeatPassword = findViewById(R.id.txtPasswordRepeat);
        String repeatPassword = txtRepeatPassword.getText().toString();

        if(!password.equals(repeatPassword))
        {
            Toast.makeText(PassengerRegisterActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 6)
        {
            Toast.makeText(PassengerRegisterActivity.this, "Password must me longer then 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setName(firstName);
        passengerDTO.setSurname(lastName);
        passengerDTO.setAddress(address);
        passengerDTO.setPassword(password);
        passengerDTO.setEmail(email);
        passengerDTO.setTelephoneNumber(phone);
        passengerDTO.setProfilePicture("");
        Call<PassengerDTO> call = ServiceUtils.passengerService.createPassenger(passengerDTO);
        call.enqueue(new Callback<PassengerDTO>() {
            @Override
            public void onResponse(Call<PassengerDTO> call, Response<PassengerDTO> response) {
                Toast.makeText(PassengerRegisterActivity.this, "Check your email!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PassengerDTO> call, Throwable t) {
                Toast.makeText(PassengerRegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
            }
        }
        );
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