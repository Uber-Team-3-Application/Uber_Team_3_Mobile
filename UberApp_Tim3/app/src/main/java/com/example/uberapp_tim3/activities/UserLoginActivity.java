package com.example.uberapp_tim3.activities;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.ChangePasswordDTO;
import com.example.uberapp_tim3.model.DTO.CreateWorkingHoursDTO;
import com.example.uberapp_tim3.model.DTO.LoginDTO;
import com.example.uberapp_tim3.model.DTO.LoginResponseDTO;
import com.example.uberapp_tim3.model.DTO.TokenDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.model.DTO.WorkingHoursDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class UserLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        TextView tvRegister = findViewById(R.id.btnRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView forgottenPassword = findViewById(R.id.forgotten);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLoginActivity.this, PassengerRegisterActivity.class);
                startActivity(intent);
            }
        });

        forgottenPassword.setOnClickListener(view -> {
            Intent intent = new Intent(UserLoginActivity.this, ForgottenPasswordActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteTokenPreferences();

                String etUser = ((EditText) findViewById(R.id.editTxtEmail)).getText().toString();
                String etPw = ((EditText)findViewById(R.id.editTxtPassword)).getText().toString();
//                login("mirko@gmail.com", "Mirko123");
                    login("markopreradovic@gmail.com", "Marko123");
//                login(etUser, etPw);


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


    public void login(String email, String password){
        LoginDTO loginDTO = new LoginDTO(email, password);
        Call<LoginResponseDTO> call = ServiceUtils.userService.login(loginDTO);
        call.enqueue(new Callback<LoginResponseDTO>() {

            @Override
            public void onResponse(@NonNull Call<LoginResponseDTO> call, @NonNull Response<LoginResponseDTO> response) {
                if(!response.isSuccessful()) {
//                    if (!checkAnotherPassword(email, password))
                        Log.d("Login Fail", "Response error");

                }
                if(response.code() == 204){
                    Toast.makeText(UserLoginActivity.this, "Email not confirmed!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("TOKEN", response.body().getToken());

                LoginResponseDTO loginResponse = response.body();
                String userRole = "";
                JWT jwt = new JWT(loginResponse.getToken());
                List<HashMap> role =
                        jwt.getClaim("role").asList(HashMap.class);
                for (Object values: role.get(0).values()){
                    userRole = values.toString();
                    break;
                }


                String email = jwt.getClaim("sub").asString();
                Long id = jwt.getClaim("id").asLong();
                TokenDTO tokenDTO = TokenDTO.getInstance();
                tokenDTO.setToken(loginResponse.getToken());
                tokenDTO.setRefreshToken(loginResponse.getRefreshToken());
                Intent intent;



                if(userRole.equalsIgnoreCase("passenger")){
                    setSharedPreferences("PASSENGER", email, id);
                    setTokenPreference(loginResponse.getToken(), loginResponse.getRefreshToken());
                    intent = new Intent(UserLoginActivity.this, PassengerMainActivity.class);
                    startActivity(intent);

                }
                else if(userRole.equalsIgnoreCase("driver")) {
                    setSharedPreferences("DRIVER", email, id);
                    setTokenPreference(loginResponse.getToken(), loginResponse.getRefreshToken());
                    intent = new Intent(UserLoginActivity.this, DriverMainActivity.class);
                    startActivity(intent);

                    createWorkingHours(id);
                }

            }

            @Override
            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                Log.d("Login Failed", t.getMessage());
            }
        });

    }

    private boolean checkAnotherPassword(String email, String password) {
        final boolean[] retVal = {false};
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,
                task -> {
                    if (task.isSuccessful()) {

                    } else {
                        retVal[0] = false;
                    }
                });
        return retVal[0];
    }

    private void createWorkingHours(Long id) {

        Call<WorkingHoursDTO> whCall = ServiceUtils.driverService.createWorkingHours(id, new CreateWorkingHoursDTO(null));
        whCall.enqueue(new Callback<WorkingHoursDTO>() {
            @Override
            public void onResponse(Call<WorkingHoursDTO> call, Response<WorkingHoursDTO> response) {
                if(!response.isSuccessful()) {

                    Log.d("Working Hours", "EXISTING");
                    return;
                }
                createWorkingHoursPreference(response);
                Log.d("Working Hours", "CREATED");
            }

            @Override
            public void onFailure(Call<WorkingHoursDTO> call, Throwable t) {

                Log.d("Working Hours", "FAIL");
            }
        });
    }

    private void createWorkingHoursPreference(Response<WorkingHoursDTO> response) {
        this.sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = this.sharedPreferences.edit();
        spEditor.putLong("pref_working_hour_id", response.body().getId());
        spEditor.apply();
    }

    private void deleteTokenPreferences() {
        TokenDTO tokenDTO = TokenDTO.getInstance();
        tokenDTO.setToken(null);
        tokenDTO.setRefreshToken(null);
        this.sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = this.sharedPreferences.edit();
        spEditor.clear().commit();
    }

    private void setTokenPreference(String token, String refreshToken) {
        this.sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = this.sharedPreferences.edit();
        spEditor.putString("pref_token", token);
        spEditor.putString("pref_refreshToken", refreshToken);
    }

    private void setSharedPreferences(String role, String email, Long id){
        this.sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = this.sharedPreferences.edit();
        spEditor.putString("pref_role", role);
        spEditor.putString("pref_email", email);
        spEditor.putLong("pref_id", id);
        spEditor.apply();

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