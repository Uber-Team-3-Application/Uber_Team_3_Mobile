package com.example.uberapp_tim3.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.ChangePasswordDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditPasswordFragment extends Fragment {

    private SharedPreferences sharedPreferences;

    public EditPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.edit_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setOnCLickListeners();
        sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        super.onViewCreated(view, savedInstanceState);

    }

    private void setOnCLickListeners() {
        ((Button) getActivity().findViewById(R.id.btnCancelEditPassword)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        ((Button) getActivity().findViewById(R.id.btnSaveEditPassword)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvOld = getActivity().findViewById(R.id.txtOldPassword);
                TextView tvNew = getActivity().findViewById(R.id.txtNewPassword);
                TextView tvRepeat = getActivity().findViewById(R.id.txtRepeatPassword);
                if(tvOld.getText().toString().trim().equals("") ||
                        tvNew.getText().toString().trim().equals("") ||
                        tvRepeat.getText().toString().trim().equals("")){
                    Toast.makeText(getView().getContext(), "Passwords must be at least 5 characters long!", Toast.LENGTH_SHORT).show();

                }else if(!tvNew.getText().toString().equals(tvRepeat.getText().toString())){
                    Toast.makeText(getView().getContext(), "New passwords do not match!", Toast.LENGTH_SHORT).show();

                }else {

                    Call<String> call = ServiceUtils.userService
                            .changePassword(sharedPreferences.getLong("pref_id", 0),
                                    new ChangePasswordDTO(tvOld.getText().toString(), tvNew.getText().toString()));
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (!response.isSuccessful()){
                                Toast.makeText(getView().getContext(), "Invalid password, try again!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            String message = response.body();
                            Toast.makeText(getView().getContext(), message, Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().popBackStackImmediate();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getView().getContext(), "Invalid passwords, try again!", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                
            }
        });
    }
}