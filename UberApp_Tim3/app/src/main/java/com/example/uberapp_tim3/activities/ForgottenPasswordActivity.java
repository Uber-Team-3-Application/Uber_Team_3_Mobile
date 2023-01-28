package com.example.uberapp_tim3.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

@SuppressLint({"MissingInflatedId", "LocalSuppress"})
public class ForgottenPasswordActivity extends AppCompatActivity {

    private String MESSAGE =  "Hello from Reesen!...";

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
                // TODO: CHECK MAIL
                sendMail(mail);

            }
        });

    }

    private void sendMail(String mail) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"veljkobubnjevic01@gmail.com"});
        email.putExtra(Intent.EXTRA_TEXT, MESSAGE);
        email.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(email, "Sending mail.... "));
            finish();
            Toast.makeText(getApplicationContext(), "Email sent.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResetCodeActivity.class);
            startActivity(intent);

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ForgottenPasswordActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }

}