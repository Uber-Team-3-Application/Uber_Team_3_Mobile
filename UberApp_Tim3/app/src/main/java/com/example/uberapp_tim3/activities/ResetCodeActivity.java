package com.example.uberapp_tim3.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.ResetPasswordDTO;
import com.example.uberapp_tim3.model.watcher.GenericTextWatcher;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetCodeActivity extends AppCompatActivity {

    private static class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }

    private String code;
    EditText otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,
            otp_textbox_five, otp_textbox_six;
    Button verify;
    private String newPassword;
    private Long id;

    public ResetCodeActivity() {

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getLongExtra("id", 0L);
            code = intent.getStringExtra("code");
            newPassword = intent.getStringExtra("newPassword");


        }
        setContentView(R.layout.activity_reset_code);

        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
        otp_textbox_five = findViewById(R.id.otp_edit_box5);
        otp_textbox_six = findViewById(R.id.otp_edit_box6);
        verify = findViewById(R.id.verify_otp_btn);

        EditText[] edit = {otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four,
        otp_textbox_five, otp_textbox_six};


        for (EditText editText : edit) {
            editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        }

        otp_textbox_one.addTextChangedListener(new GenericTextWatcher(otp_textbox_one, edit));
        otp_textbox_two.addTextChangedListener(new GenericTextWatcher(otp_textbox_two, edit));
        otp_textbox_three.addTextChangedListener(new GenericTextWatcher(otp_textbox_three, edit));
        otp_textbox_four.addTextChangedListener(new GenericTextWatcher(otp_textbox_four, edit));
        otp_textbox_five.addTextChangedListener(new GenericTextWatcher(otp_textbox_five, edit));
        otp_textbox_six.addTextChangedListener(new GenericTextWatcher(otp_textbox_six, edit));

        addVerifyListener();
    }

    private void addVerifyListener() {
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = otp_textbox_one.getText() + String.valueOf(otp_textbox_two) +
                        otp_textbox_three.getText() + otp_textbox_four.getText() + otp_textbox_five.getText() +
                        otp_textbox_six.getText();
                if (Objects.equals(code, input)) {
                    sendCode(input);
                } else {
                    Toast.makeText(ResetCodeActivity.this, "Wrong code. Check email.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendCode(String input) {
        ResetPasswordDTO dto = new ResetPasswordDTO(newPassword, input);
        Call<String> call = ServiceUtils.userService.resetPasswordWithCode(id, dto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Toast.makeText(ResetCodeActivity.this, "Successfully changed password.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ResetCodeActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(ResetCodeActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}