package com.example.vezbe4.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.Driver;
import com.example.uberapp_tim3.model.NavItem;
import com.example.uberapp_tim3.tools.DriverMockup;

import java.util.ArrayList;

public class DrawerListAdapter extends BaseAdapter {
    private Activity activity;

    public DrawerListAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return DriverMockup.getDrivers().size();
    }

    /*
     * Ova metoda vraca pojedinacan element na osnovu pozicije
     * */
    @Override
    public Object getItem(int position) {
        return DriverMockup.getDrivers().get(position);
    }


    /*
     * Ova metoda vraca jedinstveni identifikator, za adaptere koji prikazuju
     * listu ili niz, pozicija je dovoljno dobra. Naravno mozemo iskoristiti i
     * jedinstveni identifikator objekta, ako on postoji.
     * */
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Driver driver = DriverMockup.getDrivers().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.driver_list, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView description = (TextView)vi.findViewById(R.id.description);
        ImageView image = (ImageView)vi.findViewById(R.id.item_icon);

        name.setText(driver.getName());
        description.setText(driver.getVehicleRegistrationNumber());

        return  vi;
    }
}
