package com.example.uberapp_tim3.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.DriverRideDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.RideUserDTO;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.DrivesMockUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverRidesListAdapter extends BaseAdapter {
    private Activity activity;
    private Paginated<DriverRideDTO> rides;
    private SharedPreferences sharedPreferences;

    public DriverRidesListAdapter(Activity activity) {
        sharedPreferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        this.activity = activity;
        Call<Paginated<DriverRideDTO>> call = ServiceUtils.driverService.
                getRides(sharedPreferences.getLong("pref_id", 0L), 0, 50, "timeOfStart,desc");

        call.enqueue(new Callback<Paginated<DriverRideDTO>>() {
            @Override
            public void onResponse(Call<Paginated<DriverRideDTO>> call, Response<Paginated<DriverRideDTO>> response) {
                if(!response.isSuccessful()){
                    rides = new Paginated<DriverRideDTO>(0);
                    return;
                }
                rides = response.body();
                notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Paginated<DriverRideDTO>> call, Throwable t) {
                Log.d("Fail", "Failed fetching driver rides");
            }
        });
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
        String duration = drive.getStartTime().toString() + " - " + drive.getEndTime().toString();
        description.setText(duration);

        return  vi;
    }
}
