package com.example.uberapp_tim3.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.LocationDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.PassengerRideDTO;
import com.example.uberapp_tim3.model.DTO.RideDTO;
import com.example.uberapp_tim3.model.DTO.RouteDTO;
import com.example.uberapp_tim3.model.mockup.Drive;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PassengerRideHistoryAdapter extends BaseAdapter {

    private final Activity myActivity;
    private final List<PassengerRideDTO> rideHistory;

    public PassengerRideHistoryAdapter(Activity fragment, Paginated<PassengerRideDTO> rideHistory){
        this.myActivity = fragment;
        this.rideHistory = rideHistory.getResults();
    }

    @Override
    public int getCount() {
        return this.rideHistory.size();
    }

    @Override
    public Object getItem(int i) {
        return this.rideHistory.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi=view;
        PassengerRideDTO drive = rideHistory.get(i);

        if(view==null)
            vi = myActivity.getLayoutInflater().inflate(R.layout.passenger_ride_history_item, null);

        TextView time = (TextView)vi.findViewById(R.id.tvPassengerRideHistoryTimeFromTo);
        TextView path = (TextView)vi.findViewById(R.id.tvPassengerRideHistoryFromTo);
        TextView price = vi.findViewById(R.id.tvPassengerRideHistoryRidePrice);

        RouteDTO route1 = drive.getLocations().get(0);
        RouteDTO route2 =  drive.getLocations().get(drive.getLocations().size() - 1);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String duration = "";
        if(drive.getStartTime() != null && drive.getEndTime() != null) {
            Date startTime = drive.getStartTime();
            Date endTime = drive.getEndTime();
            duration = sdf.format(startTime) + " - " + sdf.format(endTime);
        }
        time.setText(duration);

        path.setText(route1.getDeparture().getAddress() + " - " + route2.getDestination().getAddress());
        price.setText(Double.toString(drive.getTotalCost()));

        return vi;
    }
}
