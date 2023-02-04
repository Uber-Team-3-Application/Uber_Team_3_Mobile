package com.example.uberapp_tim3.fragments.driver;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.passenger.PassengerReportFragment;
import com.example.uberapp_tim3.model.DTO.ReportRequestDTO;
import com.example.uberapp_tim3.model.DTO.ReportSumAverageDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverReportFragment extends Fragment {
    TextView selectedDate;
    Button calendarButton;
    PieChart ridesPerDayChart, kilometersPerDayChart, spentPerDayChart;
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
        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select").setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())).build();
        calendarButton.setOnClickListener(view1 -> {
            materialDatePicker.show(getActivity().getSupportFragmentManager(), "Tag_picker");
            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                selectedDate.setText(materialDatePicker.getHeaderText());
                Long startDate = selection.first;
                Long endDate = selection.second;
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date from = new Date(startDate);
                Date to = new Date(endDate);
                getReports(sdf.format(from), sdf.format(to));
            });
        });
    }

    private void getReports(String from, String to){
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
        setChart(report.getResult(), "KILOMETERS_PER_DAY", kilometersPerDayChart);

    }


    private void setSpentPerDayReport(ReportSumAverageDTO report) {
        TextView txtTotal = getActivity().findViewById(R.id.txtTotalSpentPerDayInTimePeriod);
        TextView txtAverage = getActivity().findViewById(R.id.txtAverageSpentPerDay);
        setTextViewsForReports(report, txtTotal, txtAverage);
        setChart(report.getResult(), "SPENT_PER_DAY", spentPerDayChart);


    }


    private void setRidesPerDayReport(ReportSumAverageDTO report) {
        TextView txtTotal = getActivity().findViewById(R.id.txtTotalRidesInTimePeriod);
        TextView txtAverage = getActivity().findViewById(R.id.txtAverageRidesPerDay);
        setTextViewsForReports(report, txtTotal, txtAverage);
        setChart(report.getResult(), "RIDES_PER_DAY", ridesPerDayChart);
    }

    private void setChart(Map<Date, Double> report, String typeOfChart, PieChart chart) {
        List<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<Date, Double> entry : report.entrySet()) {
            double entryConverted = (double) Math.round( entry.getValue() * 100) / 100;
            entries.add(new PieEntry((float) entryConverted, entry.getKey()));
        }
        PieDataSet set = new PieDataSet(entries, typeOfChart.toLowerCase());
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData barData = new PieData(set);
        barData.setDrawValues(true);
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        barData.setValueTextSize(12f);
        barData.setValueTextColor(Color.BLACK);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Date date = (Date) e.getData();
                Toast.makeText(getContext(), format.format(date), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });
        chart.setDrawHoleEnabled(true);
        chart.setEntryLabelTextSize(12);
        chart.setEntryLabelColor(Color.BLACK);
        chart.getDescription().setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setDrawInside(false);

        chart.setData(barData);

        chart.invalidate();

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
        ridesPerDayChart = (PieChart) getView().findViewById(R.id.ridesPerDayChart);
        kilometersPerDayChart = (PieChart) getView().findViewById(R.id.kilometersPerDayChart);
        spentPerDayChart = (PieChart) getView().findViewById(R.id.spentPerDayChart);
        TextView t1 = getView().findViewById(R.id.spentPerDay);
        t1.setText("Earned per day");
        TextView t2 = getView().findViewById(R.id.spentAll);
        t2.setText("Earned in time period:");
    }

}
