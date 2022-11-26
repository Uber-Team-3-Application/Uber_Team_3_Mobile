package com.example.uberapp_tim3.fragments.passenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.ChatFragment;
import com.example.uberapp_tim3.tools.FragmentTransition;

public class PassengerInboxFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner dropdown;
    private static final String[] items = {"All", "Support", "Drivers", "Notifications"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_passenger_inbox, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.inbox);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dropdown = (Spinner) getView().findViewById(R.id.filter);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        loadInbox();
    }

    private void loadInbox() {
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.inboxLayout);
        //linearLayout.setOnClickListener(ClickOnInbox());
        LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View notificationBox;
        notificationBox = inflater.inflate(R.layout.notification_item, (ViewGroup) getView(), false);
        linearLayout.addView(notificationBox);
        for(int i = 1; i < 5; i++) {
            View itemBox;
            itemBox = inflater.inflate(R.layout.inbox_list_item, (ViewGroup) getView(), false);
            itemBox.setOnClickListener(ClickOnInbox());
            linearLayout.addView(itemBox);
        }

        notificationBox = inflater.inflate(R.layout.notification_item, (ViewGroup) getView(), false);
        linearLayout.addView(notificationBox);
    }

    private View.OnClickListener ClickOnInbox() {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //FragmentTransition.to(new ChatFragment(), getActivity(), true);
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).addToBackStack(null).commit();
            }
        };
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

}