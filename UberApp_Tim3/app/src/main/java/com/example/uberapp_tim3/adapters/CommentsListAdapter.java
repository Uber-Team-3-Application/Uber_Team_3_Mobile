package com.example.uberapp_tim3.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.users.Passenger;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentsListAdapter extends BaseAdapter {
    private Activity activity;
    HashMap<Passenger, String> comments = new HashMap<>();

    public CommentsListAdapter(Activity activity, HashMap<Passenger, String> comments) {
        this.activity = activity;
        this.comments = comments;
    }


    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return (new ArrayList<>(comments.keySet())).get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi=view;
        Passenger passenger = (new ArrayList<Passenger>(comments.keySet())).get(i);
        String comment = comments.get(passenger);


        if(view==null)
            vi = activity.getLayoutInflater().inflate(R.layout.comments_list, null);

        TextView name = (TextView)vi.findViewById(R.id.nameOfPassenger);
        TextView description = (TextView)vi.findViewById(R.id.comment);
        RatingBar ratingBar = vi.findViewById(R.id.ratingFromPass);
        ratingBar.setEnabled(false);
        ratingBar.setRating(4);

        String info = passenger.getName() + " " + passenger.getLastName();
        name.setText(info);
        description.setText(comment);



        return  vi;
    }
}
