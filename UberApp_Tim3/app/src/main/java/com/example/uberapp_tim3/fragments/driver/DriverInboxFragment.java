package com.example.uberapp_tim3.fragments.driver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.ChatFragment;
import com.example.uberapp_tim3.model.DTO.MessageBundleDTO;
import com.example.uberapp_tim3.model.DTO.MessageDisplayDTO;
import com.example.uberapp_tim3.model.DTO.MessageFullDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverInboxFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner dropdown;
    private RelativeLayout rlSupportInbox;
    private SharedPreferences preferences;
    private static final String[] items = {"All", "Support", "Passengers" , "Notifications"};

    public static Fragment newInstance() {
        return new DriverInboxFragment();
    }

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
        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        rlSupportInbox = getActivity().findViewById(R.id.supportInbox);
        setOnClickForSupportInbox();
        loadInbox();
    }

    private void setOnClickForSupportInbox() {
        rlSupportInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Long> call = ServiceUtils.userService.getAdminId();
                call.enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        if(!response.isSuccessful()){
                            Log.d("Message Error", "Something went wrong");
                            return;
                        }
                        Long senderId = preferences.getLong("pref_id", 0);
                        assert response.body() != null;
                        Long receiverId = response.body();
                        String messageType = "SUPPORT";
                        MessageBundleDTO messageBundleDTO = new MessageBundleDTO(senderId, receiverId, null, messageType);
                        Bundle args = new Bundle();
                        args.putParcelable("message", messageBundleDTO);
                        ChatFragment chatFragment = new ChatFragment();
                        chatFragment.setArguments(args);
                        FragmentTransition.to(chatFragment, requireActivity(), true);
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        Log.d("Couldnt fetch admin", "Admin error");
                    }
                });


            }
        });
    }

    private void loadInbox() {

        Call<Paginated<MessageFullDTO>> call = ServiceUtils.userService.getMessages(preferences.getLong("pref_id", 0));
        call.enqueue(new Callback<Paginated<MessageFullDTO>>() {
            @Override
            public void onResponse(Call<Paginated<MessageFullDTO>> call, Response<Paginated<MessageFullDTO>> response) {
                if(!response.isSuccessful()){
                    Log.d("Message Error", "Something went wrong with messages");
                    return;
                }
                assert response != null;
                setMessages(response);
            }

            @Override
            public void onFailure(Call<Paginated<MessageFullDTO>> call, Throwable t) {
                Log.d("Message Error", "Couldn't fetch messages");
            }
        });

    }

    private void setMessages(Response<Paginated<MessageFullDTO>> response) {

        List<Long> userIds = new ArrayList<>();

        List<MessageFullDTO> messages = response.body().getResults();
        List<MessageDisplayDTO> messageDisplayDTOS = new ArrayList<>();
        for(MessageFullDTO message: messages){
            filterThroughMessagesAndSet(messageDisplayDTOS, message);

        }

        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.inboxLayout);
        LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for(MessageDisplayDTO messageDisplayDTO: messageDisplayDTOS){
            if(messageDisplayDTO.getMessageType().equalsIgnoreCase("ride") && messageDisplayDTO.getReceiverId() == null){
                setNotificationMessage(linearLayout,
                        sdf, messageDisplayDTOS.get(messageDisplayDTOS.size() - 1), inflater);

            }else if(messageDisplayDTO.getMessageType().equalsIgnoreCase("ride")){
                setPassengerMessage(linearLayout,
                        sdf, messageDisplayDTOS.get(messageDisplayDTOS.size() - 1), inflater);
            }
        }
    }

    private void filterThroughMessagesAndSet(List<MessageDisplayDTO> messageDisplayDTOS, MessageFullDTO message) {
        boolean isUpdated = false;
        for(MessageDisplayDTO messageDisplayDTO: messageDisplayDTOS){
            if(messageDisplayDTO.getReceiverId() == message.getReceiverId()){
                messageDisplayDTO.setLastMessage(message.getMessage());
                messageDisplayDTO.setLastMessageTime(message.getTimeOfSending());
                isUpdated = true;
                break;
            }
        }
        if(!isUpdated) {
            messageDisplayDTOS.add(new MessageDisplayDTO(
                    message.getReceiverId(),
                    "Name",
                    "Picture",
                    message.getTimeOfSending(),
                    message.getMessage(),
                    message.getType(),
                    message.getRideId()
            ));
        }


    }

    private void setPassengerMessage(LinearLayout linearLayout, SimpleDateFormat sdf, MessageDisplayDTO messageDisplayDTO, LayoutInflater inflater) {

        View passengerMessage = inflater.inflate(R.layout.inbox_list_item, (ViewGroup) getView(), false);
        TextView contactName = passengerMessage.findViewById(R.id.contact_name);
        String name = messageDisplayDTO.getReceiverId().toString();
        contactName.setText(name);
        TextView lastMessage = passengerMessage.findViewById(R.id.last_message);
        lastMessage.setText(messageDisplayDTO.getLastMessage());
        String date = sdf.format(messageDisplayDTO.getLastMessageTime());
        TextView messageDate = passengerMessage.findViewById(R.id.time_and_date);
        messageDate.setText(date);
        linearLayout.addView(passengerMessage);
    }

    private void setNotificationMessage(LinearLayout linearLayout, SimpleDateFormat sdf, MessageDisplayDTO messageDisplayDTO, LayoutInflater inflater) {
        View notificationMessage = inflater.inflate(R.layout.notification_item, (ViewGroup) getView(), false);

        TextView notification = notificationMessage.findViewById(R.id.contact_name);
        notification.setText(messageDisplayDTO.getLastMessage());
        String date = sdf.format(messageDisplayDTO.getLastMessageTime());
        TextView notificationDate = notificationMessage.findViewById(R.id.time_and_date);
        notificationDate.setText(date);
        linearLayout.addView(notificationDate);
    }

    private View.OnClickListener ClickOnInbox() {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    FragmentTransition.to(new ChatFragment(), getActivity(), true);
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