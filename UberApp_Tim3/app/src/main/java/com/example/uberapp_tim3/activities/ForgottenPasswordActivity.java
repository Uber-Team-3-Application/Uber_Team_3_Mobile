package com.example.uberapp_tim3.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.EmailDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgottenPasswordActivity extends AppCompatActivity {

    private String code;
    private UserDTO user;
    private String mail;
    private String newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);


        TextView view = findViewById(R.id.btnSend);
        TextView mMail = findViewById(R.id.editTxtEmailForgotten);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView newPasswordView = findViewById(R.id.editTxtNewPassword);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = mMail.getText().toString();
                if (checkEmailAndPassword(mail, newPasswordView.getText().toString())) {
                    newPassword = newPasswordView.getText().toString();
                    getUserByEmail();
                }
                else Toast.makeText(ForgottenPasswordActivity.this, "Please enter valid credentials",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean checkEmailAndPassword(String email, String password) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);
        Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[A-Z])(?!.*[^a-zA-Z0-9@#$^+=])(.{8,15})$");
        Matcher passMatcher = passwordPattern.matcher(password);
        return matcher.matches() && passMatcher.matches();
    }





    private void getUserByEmail() {

    ServiceUtils.userService.findByEmail(mail).enqueue(new Callback<UserDTO>() {
        @Override
        public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
            if (response.body() != null) {
                user = response.body();
                getVerificationCode();
            }

        }
        @Override
        public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {

        }
    });
    }

    private void getVerificationCode() {
        ServiceUtils.userService.resetPassword(user.getId()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                code = response.body();
                sendEmail();
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

            }
        });
    }

    private void sendEmail() {
        String message = "This is a verification code for reset your password!\n";
        String endMessage = "\n\nThis email was sent to you by Reesen Inc." +
                            "You are receiving this email because you registred on our website." +
                            "If this wasn't you, please ignore this mail.";
        EmailDTO dto = new EmailDTO(mail, "Password Reset", message + code + endMessage);
        ServiceUtils.userService.sendEmail(dto).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                System.out.println("POSLATOOO");
                Toast.makeText(ForgottenPasswordActivity.this, "Email with code sent", Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("code", code);
                bundle.putLong("id", user.getId());
                bundle.putString("newPassword", newPassword);
                Intent intent = new Intent(ForgottenPasswordActivity.this, ResetCodeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                System.out.println("ERROR");
                System.out.println(t.getMessage());
                Toast.makeText(ForgottenPasswordActivity.this, "Try later...", Toast.LENGTH_SHORT).show();
            }
        });


    }


}