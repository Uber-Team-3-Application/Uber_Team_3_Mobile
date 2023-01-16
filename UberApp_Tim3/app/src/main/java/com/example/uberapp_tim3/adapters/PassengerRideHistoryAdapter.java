package com.example.uberapp_tim3.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.mockup.Drive;

import java.util.List;

public class PassengerRideHistoryAdapter extends BaseAdapter {

    private Activity myActivity;
    private List<Drive> rideHistory;

    public PassengerRideHistoryAdapter(Activity fragment, List<Drive> rideHistory){
        this.myActivity = fragment;
        this.rideHistory = rideHistory;
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi=view;
        Drive drive = rideHistory.get(i);

        if(view==null)
            vi = myActivity.getLayoutInflater().inflate(R.layout.passenger_ride_history_item, null);

        TextView time = (TextView)vi.findViewById(R.id.tvPassengerRideHistoryTimeFromTo);
        TextView path = (TextView)vi.findViewById(R.id.tvPassengerRideHistoryFromTo);
        TextView price = vi.findViewById(R.id.tvPassengerRideHistoryRidePrice);

        time.setText(drive.getStartDrive() + "-" + drive.getEndDrive());
        path.setText(drive.getrelation());
        price.setText(Double.toString(drive.getPrice()));

        return vi;
    }
}
