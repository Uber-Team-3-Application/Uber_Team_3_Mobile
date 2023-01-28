package com.example.uberapp_tim3.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uberapp_tim3.tools.SendMail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class ForgottenPasswordActivity extends AppCompatActivity {

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);


        TextView view = findViewById(R.id.btnSend);
        TextView mMail = (EditText) findViewById(R.id.editTxtEmailForgotten);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = mMail.getText().toString();
                if (checkEmail(mail))
                    sendMail(mail);
                Toast.makeText(ForgottenPasswordActivity.this, "Please enter validate email",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    private boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void sendMail(String mail) {
        try {
            SendMail sm = new SendMail(this, mail, code);
            sm.execute();

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }


    }

}