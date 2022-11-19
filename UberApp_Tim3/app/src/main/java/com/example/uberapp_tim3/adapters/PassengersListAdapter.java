package com.example.uberapp_tim3.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.mockup.Passenger;


import java.util.List;

public class PassengersListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Passenger> passengerList;

    public PassengersListAdapter(FragmentActivity activity, List<Passenger> passengerList) {
        this.activity = activity;
        this.passengerList = passengerList;

    }

    @Override
    public int getCount() {
        return passengerList.size();
    }

    @Override
    public Object getItem(int i) {
        return passengerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi=view;
        Passenger passenger = passengerList.get(i);
        String info = passenger.getName() + " " + passenger.getLastName();
        if(view==null)
            vi = activity.getLayoutInflater().inflate(R.layout.profiles_of_passengers_on_drive_list, null);

        TextView name = (TextView)vi.findViewById(R.id.nameOfPassenger);
        name.setText(info);
        return  vi;
    }
}
