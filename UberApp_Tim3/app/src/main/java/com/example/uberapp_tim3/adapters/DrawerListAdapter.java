package com.example.uberapp_tim3.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.Drive;
import com.example.uberapp_tim3.model.Driver;
import com.example.uberapp_tim3.model.NavItem;
import com.example.uberapp_tim3.tools.DriverMockup;
import com.example.uberapp_tim3.tools.DrivesMockUp;

import java.text.ParseException;
import java.util.ArrayList;

public class DrawerListAdapter extends BaseAdapter {
    private Activity activity;

    public DrawerListAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        try {
            return  DrivesMockUp.getDrives().size();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /*
     * Ova metoda vraca pojedinacan element na osnovu pozicije
     * */
    @Override
    public Object getItem(int position) {
        try {
            return DrivesMockUp.getDrives().get(position);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
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
        Drive drive = null;
        try {
            drive = DrivesMockUp.getDrives().get(position);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.driver_list, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView description = (TextView)vi.findViewById(R.id.description);
        ImageView image = (ImageView)vi.findViewById(R.id.item_icon);

        assert drive != null;
        name.setText(drive.getrelation());
        description.setText(drive.getStartDrive());

        return  vi;
    }
}
