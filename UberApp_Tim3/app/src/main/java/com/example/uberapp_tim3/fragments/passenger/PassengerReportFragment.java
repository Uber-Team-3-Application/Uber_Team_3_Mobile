package com.example.uberapp_tim3.fragments.passenger;

import android.graphics.Color;
import android.os.Bundle;
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
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;

public class PassengerReportFragment extends Fragment {
    TextView selectedDate;
    Button calendarButton;
    HorizontalBarChart horizontalBarChart;

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
        selectedDate = getView().findViewById(R.id.text);
        calendarButton = getView().findViewById(R.id.calendar);
        horizontalBarChart = (HorizontalBarChart) getView().findViewById(R.id.chart);
        YAxis yAxis = horizontalBarChart.getAxisLeft();
        yAxis.setAxisMaximum(5000f);
        yAxis.setAxisMinimum(0f);
        setChartData(0);
        horizontalBarChart.notifyDataSetChanged();
        horizontalBarChart.getXAxis().setDrawGridLines(false);
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select").setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())).build();
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "Tag_picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        selectedDate.setText(materialDatePicker.getHeaderText());
                        setChartData(30);
                    }
                });
            }
        });
    }

    private void setChartData(int i) {
        ArrayList<BarEntry> yValues = new ArrayList<>();
        float barWidth = 8.5f;
        float barSpace = 10f;

        for(int j = 0; j < i; j++){
            float value = (float) (Math.random()*5000);
            yValues.add(new BarEntry(j * barSpace, value));
        }

        BarDataSet set;
        set = new BarDataSet(yValues, "Spent");
        set.setColors(new int[] {Color.rgb(170, 174, 159), Color.rgb(156, 168, 158), Color.rgb(77, 112, 109)});
        BarData data = new BarData(set);
        data.setBarWidth(barWidth);
        horizontalBarChart.setData(data);
        horizontalBarChart.notifyDataSetChanged();
    }
}
