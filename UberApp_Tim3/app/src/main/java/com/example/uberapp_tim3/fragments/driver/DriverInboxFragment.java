package com.example.uberapp_tim3.fragments.driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;

public class DriverInboxFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_driver_inbox, container, false);
    }

    public static DriverInboxFragment newInstance() {
        return new DriverInboxFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //final ListView listView = (ListView) getView().findViewById(R.id.inbox_listview);
        //listView.setAdapter();
    }
}