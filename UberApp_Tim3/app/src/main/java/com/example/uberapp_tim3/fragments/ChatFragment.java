package com.example.uberapp_tim3.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;

public class ChatFragment extends Fragment {

    public LinearLayout mainLayout;
    public String[] messages;
    public ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container1, @Nullable Bundle savedInstanceState) {
        container = container1;
        return inflater.inflate(R.layout.chat, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle(R.string.inbox);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mainLayout = (LinearLayout)getView().findViewById(R.id.chatLayout);
        loadMessages();
    }

    private void loadMessages() {
        messages = new String[]{"Hello", "Hi", "When will you be here?", "I am right around the corner!"};
        int k = 1;
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.chatLayout);
        for(String message: messages) {
            LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemBox;
            if (k%2==1){
                itemBox = inflater.inflate(R.layout.sent_message, (ViewGroup) getView(), false);
                TextView content = (TextView) itemBox.findViewById(R.id.sent_msg);
                content.setText(message);
            }
            else{
                itemBox = inflater.inflate(R.layout.recived_message, (ViewGroup) getView(), false);
                TextView content = (TextView) itemBox.findViewById(R.id.recived_msg);
                content.setText(message);
            }
            linearLayout.addView(itemBox);
            k++;
        }
    }
}
