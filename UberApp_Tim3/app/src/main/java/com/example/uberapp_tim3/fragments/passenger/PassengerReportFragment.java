package com.example.uberapp_tim3.fragments.passenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.ReportRequestDTO;
import com.example.uberapp_tim3.model.DTO.ReportSumAverageDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.services.interfaces.IRideService;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerReportFragment extends Fragment {
    TextView selectedDate;
    Button calendarButton;
    HorizontalBarChart ridesPerDayChart, kilometersPerDayChart;
    PieChart spentPerDayChart;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passenger_report, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.reports);
    }

    public static PassengerReportFragment newInstance() {
        return new PassengerReportFragment();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews();
        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
//        YAxis yAxis = horizontalBarChart.getAxisLeft();
//        yAxis.setAxisMaximum(5000f);
//        yAxis.setAxisMinimum(0f);
//        horizontalBarChart.notifyDataSetChanged();
//        horizontalBarChart.getXAxis().setDrawGridLines(false);
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select").setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())).build();
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "Tag_picker");
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    selectedDate.setText(materialDatePicker.getHeaderText());
                    setChartData();
                });
            }
        });
    }

    private void getReports(Date from, Date to){
        String typeOfReport = "RIDES_PER_DAY";
        ReportRequestDTO reportRequestDTO = new ReportRequestDTO(
            preferences.getLong("pref_id", 0),
                preferences.getString("pref_role", ""),
                typeOfReport,
                from,
                to
        );

        
        getRidesPerDay(reportRequestDTO);

        getKilometersPerDay(reportRequestDTO);

        getSpentPerDay(reportRequestDTO);


    }

    private void getSpentPerDay(ReportRequestDTO reportRequestDTO) {
        //TODO : mozda monej earned
        reportRequestDTO.setTypeOfReport("MONEY_SPENT_PER_DAY");
        Call<ReportSumAverageDTO> callSpentPerDay = ServiceUtils.rideService.getReport(reportRequestDTO);
        callSpentPerDay.enqueue(new Callback<ReportSumAverageDTO>() {
            @Override
            public void onResponse(Call<ReportSumAverageDTO> call, Response<ReportSumAverageDTO> response) {
                if(!response.isSuccessful()){
                    Log.d("Report response failure", "FAIL MONEY SPENT PER DAY");
                    return;
                }
        
                assert  response.body() != null;
                ReportSumAverageDTO report = response.body();
                setSpentPerDayReport(report);

            }

            @Override
            public void onFailure(Call<ReportSumAverageDTO> call, Throwable t) {
                Log.d("Report fetch failure", "FAIL SPENT PER DAY");
            }
        });
    }


    private void getKilometersPerDay(ReportRequestDTO reportRequestDTO) {
        reportRequestDTO.setTypeOfReport("KILOMETERS_PER_DAY");
        Call<ReportSumAverageDTO> callKilometersPerDay = ServiceUtils.rideService.getReport(reportRequestDTO);
        callKilometersPerDay.enqueue(new Callback<ReportSumAverageDTO>() {
            @Override
            public void onResponse(Call<ReportSumAverageDTO> call, Response<ReportSumAverageDTO> response) {
                if(!response.isSuccessful()){
                    Log.d("Report response failure", "FAIL KILOMETERS PER DAY");
                    return;
                }
                assert  response.body() != null;
                ReportSumAverageDTO report = response.body();
                setKilometersPerDayReport(report);

            }

            @Override
            public void onFailure(Call<ReportSumAverageDTO> call, Throwable t) {
                Log.d("Report fetch failure", "FAIL KILOMETERS PER DAY");

            }
        });
    }

    private void getRidesPerDay(ReportRequestDTO reportRequestDTO) {
        Call<ReportSumAverageDTO> callRidesPerDay = ServiceUtils.rideService.getReport(reportRequestDTO);
        callRidesPerDay.enqueue(new Callback<ReportSumAverageDTO>() {
            @Override
            public void onResponse(Call<ReportSumAverageDTO> call, Response<ReportSumAverageDTO> response) {
                if(!response.isSuccessful()){
                    Log.d("Report response failure", "FAIL RIDES PER DAY");
                    return;
                }

                assert  response.body() != null;
                ReportSumAverageDTO report = response.body();
                setRidesPerDayReport(report);
            }

            @Override
            public void onFailure(Call<ReportSumAverageDTO> call, Throwable t) {
                Log.d("Report fetch failure", "FAIL RIDES PER DAY");
            }
        });
    }

    private void setKilometersPerDayReport(ReportSumAverageDTO report) {
        TextView txtTotal = getActivity().findViewById(R.id.txtTotalKilometersPerDayInTimePeriod);
        TextView txtAverage = getActivity().findViewById(R.id.txtAverageKilometersPerDay);
        setTextViewsForReports(report, txtTotal, txtAverage);

    }


    private void setSpentPerDayReport(ReportSumAverageDTO report) {
        TextView txtTotal = getActivity().findViewById(R.id.txtTotalSpentPerDayInTimePeriod);
        TextView txtAverage = getActivity().findViewById(R.id.txtAverageSpentPerDay);
        setTextViewsForReports(report, txtTotal, txtAverage);

    }


    private void setRidesPerDayReport(ReportSumAverageDTO report) {
        TextView txtTotal = getActivity().findViewById(R.id.txtTotalRidesInTimePeriod);
        TextView txtAverage = getActivity().findViewById(R.id.txtAverageRidesPerDay);
        setTextViewsForReports(report, txtTotal, txtAverage);
    }

    private void setTextViewsForReports(ReportSumAverageDTO report, TextView txtTotal, TextView txtAverage) {
        double total = (double) Math.round(report.getSum() * 100) / 100;
        double average = (double) Math.round(report.getAverage() * 100) / 100;
        String totalToString = Double.toString(total);
        String averageToString = Double.toString(average);
        txtTotal.setText(totalToString);
        txtAverage.setText(averageToString);
    }

    private void setViews() {
        selectedDate = getView().findViewById(R.id.text);
        calendarButton = getView().findViewById(R.id.calendar);
        ridesPerDayChart = (HorizontalBarChart) getView().findViewById(R.id.ridesPerDayChart);
        kilometersPerDayChart = (HorizontalBarChart) getView().findViewById(R.id.kilometersPerDayChart);
        spentPerDayChart = (PieChart) getView().findViewById(R.id.spentPerDayChart);
    }

    private void setChartData() {
        ArrayList<BarEntry> yValues = new ArrayList<>();
        float barWidth = 8.5f;
        float barSpace = 10f;
        int i = 30;
        for(int j = 0; j < i; j++){
            float value = (float) (Math.random()*5000);
            yValues.add(new BarEntry(j * barSpace, value));
        }

        BarDataSet set;
        set = new BarDataSet(yValues, "Spent");
        set.setColors(new int[] {Color.rgb(170, 174, 159), Color.rgb(156, 168, 158), Color.rgb(77, 112, 109)});
        BarData data = new BarData(set);
        data.setBarWidth(barWidth);
       // horizontalBarChart.setData(data);
        //horizontalBarChart.notifyDataSetChanged();
    }
}
