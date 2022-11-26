package com.example.uberapp_tim3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.adapters.CommentsListAdapter;

import com.example.uberapp_tim3.fragments.passenger.PassengerInfoProfile;
import com.example.uberapp_tim3.model.mockup.Drive;
import com.example.uberapp_tim3.tools.FragmentTransition;


public class CommentsFragment extends ListFragment {


    public CommentsFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.comments);
    }

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_layout, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();

        assert bundle != null;
        Drive drive = bundle.getParcelable("driveInfo");
        CommentsListAdapter adapter = new CommentsListAdapter(getActivity(), drive.getComments());
        setListAdapter(adapter);

    }


    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        PassengerInfoProfile profile = new PassengerInfoProfile();
        FragmentTransition.to(profile, requireActivity(), true);

    }


}