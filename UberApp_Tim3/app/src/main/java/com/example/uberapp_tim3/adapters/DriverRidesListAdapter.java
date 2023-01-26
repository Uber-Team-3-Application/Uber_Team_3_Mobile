package com.example.uberapp_tim3.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.DriveItemDetailFragment;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.RideUserDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DrivesMockUp;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRidesListAdapter extends BaseAdapter {
    private Activity activity;
    private Paginated<DriverRideDTO> rides;

    public DriverRidesListAdapter(Activity activity, Paginated<DriverRideDTO> rides) {
        this.activity = activity;
        this.rides = rides;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        if(rides == null) return 0;
        else return  rides.getResults().size();

    }

    /*
     * Ova metoda vraca pojedinacan element na osnovu pozicije
     * */
    @Override
    public Object getItem(int position) {
        return rides.getResults().get(position);

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
        DriverRideDTO drive = rides.getResults().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.drives_list_item, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView description = (TextView)vi.findViewById(R.id.description);
        ImageView image = (ImageView)vi.findViewById(R.id.item_icon);

        assert drive != null;


        String fromTo = drive.getLocations().get(0).getDeparture().getAddress() + " - " +
                        drive.getLocations().get(drive.getLocations().size() - 1).getDestination().getAddress();

        name.setText(fromTo);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if(drive.getStartTime() != null && drive.getEndTime() != null) {
            Date startTime = drive.getStartTime();
            Date endTime = drive.getEndTime();
            String duration = sdf.format(startTime) + " - " + sdf.format(endTime);
            description.setText(duration);
        }
        return  vi;
    }
}
