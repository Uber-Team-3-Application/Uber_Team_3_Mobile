package com.example.uberapp_tim3.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.tools.DrivesMockUp;

public class DrawerListAdapter extends BaseAdapter {
    private Activity activity;

    public DrawerListAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return  DrivesMockUp.getDrives().size();

    }

    /*
     * Ova metoda vraca pojedinacan element na osnovu pozicije
     * */
    @Override
    public Object getItem(int position) {
        return DrivesMockUp.getDrives().get(position);

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
        drive = DrivesMockUp.getDrives().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.drives_list_item, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        TextView description = (TextView)vi.findViewById(R.id.description);
        ImageView image = (ImageView)vi.findViewById(R.id.item_icon);

        assert drive != null;
        name.setText(drive.getrelation());
        description.setText(drive.getStartDrive());

        return  vi;
    }
}
