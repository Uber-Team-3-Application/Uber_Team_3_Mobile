package com.example.uberapp_tim3.fragments.driver;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.DriversStatisticsDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverStatisticsFragment extends Fragment {

    private SharedPreferences sharedPreferences;


    public DriverStatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_driver_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setOnClickListeners();
        this.sharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Call<Set<DriversStatisticsDTO>> call = ServiceUtils.driverService.getStatistics(sharedPreferences.getLong("pref_id", 0L));
        call.enqueue(new Callback<Set<DriversStatisticsDTO>>() {
            @Override
            public void onResponse(@NonNull Call<Set<DriversStatisticsDTO>> call, @NonNull Response<Set<DriversStatisticsDTO>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Can't get driver info.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Set<DriversStatisticsDTO> stats = response.body();
                for(DriversStatisticsDTO dto: stats){
                    if(dto.getType() == 1)
                    {
                        TextView todayAccepted = requireView().findViewById(R.id.todayAccepted);
                        todayAccepted.setText(Integer.toString(dto.getAccepted()));
                        TextView todayRejected = requireView().findViewById(R.id.todayRejected);
                        todayRejected.setText(Integer.toString(dto.getRejected()));
                        TextView todayHors = requireView().findViewById(R.id.todayHours);
                        todayHors.setText(Integer.toString(dto.getHours()));
                        TextView todayIncome = requireView().findViewById(R.id.todayIncome);
                        todayIncome.setText(Double.toString(dto.getIncome()));
                    } else if (dto.getType() == 7) {
                        TextView weekAccepted = requireView().findViewById(R.id.weekAccepted);
                        weekAccepted.setText(Integer.toString(dto.getAccepted()));
                        TextView weekRejected = requireView().findViewById(R.id.weekRejected);
                        weekRejected.setText(Integer.toString(dto.getRejected()));
                        TextView weekHors = requireView().findViewById(R.id.weekHours);
                        weekHors.setText(Integer.toString(dto.getHours()));
                        TextView weekIncome = requireView().findViewById(R.id.weekIncome);
                        weekIncome.setText(Double.toString(dto.getIncome()));
                    } else {
                        TextView monthAccepted = requireView().findViewById(R.id.monthAccepted);
                        monthAccepted.setText(Integer.toString(dto.getAccepted()));
                        TextView monthRejected = requireView().findViewById(R.id.monthRejected);
                        monthRejected.setText(Integer.toString(dto.getRejected()));
                        TextView monthHors = requireView().findViewById(R.id.monthHours);
                        monthHors.setText(Integer.toString(dto.getHours()));
                        TextView monthIncome = requireView().findViewById(R.id.monthIncome );
                        monthIncome.setText(Double.toString(dto.getIncome()));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Set<DriversStatisticsDTO>> call, @NonNull Throwable t) {
                Log.d("FAIL", "Something went wrong!");
            }
        });
    }

    private void setOnClickListeners(){
        Button btnBack = getActivity().findViewById(R.id.btndriverStatsBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
}