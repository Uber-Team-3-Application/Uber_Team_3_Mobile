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

public class PassengerRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);

        Button btnPassengerMain = findViewById(R.id.btnRegisterUser);

        btnPassengerMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassengerRegisterActivity.this, PassengerMainActivity.class);
                startActivity(intent);
                finish();
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