package com.example.uberapp_tim3.fragments.passenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.fragments.ChatFragment;
import com.example.uberapp_tim3.fragments.driver.DriverInboxFragment;
import com.example.uberapp_tim3.model.DTO.MessageBundleDTO;
import com.example.uberapp_tim3.model.DTO.MessageDisplayDTO;
import com.example.uberapp_tim3.model.DTO.MessageFullDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;
import com.example.uberapp_tim3.tools.FragmentTransition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerInboxFragment  extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerChangeMessageType;
    private RelativeLayout rlSupportInbox;
    private SharedPreferences preferences;
    private EditText editTextSearch;
    private LinearLayout inboxItemsLayout;
    private List<MessageFullDTO> messages;
    private List<MessageDisplayDTO> messageDisplayDTOS;
    private static final String[] items = {"All", "Drivers", "Notifications"};
    private String selectedTypeOfMessages = "All";

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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerChangeMessageType = (Spinner) getView().findViewById(R.id.spFilterInbox);
        editTextSearch = getView().findViewById(R.id.etTxtSearchInbox);
        preferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChangeMessageType.setAdapter(adapter);
        spinnerChangeMessageType.setOnItemSelectedListener(this);
        rlSupportInbox = getActivity().findViewById(R.id.supportInbox);
        messages = new ArrayList<>();
        setOnClickForSupportInbox();
        loadInbox();
        setSpinnerListener();
        setSearchListener();
    }

    private void setSearchListener() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    inboxItemsLayout.removeAllViews();
                    setMessages(messages);
                    return;
                }
                String text = charSequence.toString().trim();
                if (text.equalsIgnoreCase("")) return;

                List<MessageFullDTO> newMessages = new ArrayList<>();
                for (MessageFullDTO message : messages) {
                    if (message.getMessage().toLowerCase().contains(text.toLowerCase()))
                        newMessages.add(message);
                    else {
                        for (MessageDisplayDTO messageDisplayDTO : messageDisplayDTOS) {
                            if (messageDisplayDTO.getId() == message.getId()
                                    && messageDisplayDTO.getContactName().toLowerCase().contains(text.toLowerCase())) {
                                newMessages.add(message);
                                break;
                            }
                        }
                    }

                }
                inboxItemsLayout.removeAllViews();
                setMessages(newMessages);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void setSpinnerListener() {
        spinnerChangeMessageType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (messages.size() == 0) return;
                editTextSearch.setText("");
                selectedTypeOfMessages = items[i];
                inboxItemsLayout.removeAllViews();
                setMessages(messages);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setOnClickForSupportInbox() {
        rlSupportInbox.setOnClickListener(view -> {
            Call<Long> call = ServiceUtils.userService.getAdminId();
            call.enqueue(new Callback<Long>() {
                @Override
                public void onResponse(@NonNull Call<Long> call, @NonNull Response<Long> response) {
                    if (!response.isSuccessful()) {
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
                    PassengerChatFragment chatFragment = new PassengerChatFragment();
                    chatFragment.setArguments(args);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, chatFragment).addToBackStack(null).commit();

                }

                @Override
                public void onFailure(@NonNull Call<Long> call, @NonNull Throwable t) {
                    Log.d("Couldnt fetch admin", "Admin error");
                }
            });


        });
    }

    private void loadInbox() {

        Call<Paginated<MessageFullDTO>> call = ServiceUtils.userService.getMessages(preferences.getLong("pref_id", 0));
        call.enqueue(new Callback<Paginated<MessageFullDTO>>() {
            @Override
            public void onResponse(@NonNull Call<Paginated<MessageFullDTO>> call, @NonNull Response<Paginated<MessageFullDTO>> response) {
                if (!response.isSuccessful()) {
                    Log.d("Message Error", "Something went wrong with messages");
                    return;
                }
                assert response.body() != null;
                messages = response.body().getResults();
                setMessages(messages);
            }

            @Override
            public void onFailure(Call<Paginated<MessageFullDTO>> call, Throwable t) {
                Log.d("Message Error", "Couldn't fetch messages");
            }
        });

    }

    private void setMessages(List<MessageFullDTO> messages) {

        messageDisplayDTOS = new ArrayList<>();
        for (MessageFullDTO message : messages) {
            filterThroughMessagesAndSet(messageDisplayDTOS, message);
        }

        inboxItemsLayout = (LinearLayout) getView().findViewById(R.id.inboxLayout);
        LayoutInflater inflater = (LayoutInflater) getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        orderMessagesByDate(messageDisplayDTOS);
        setViewsForMessages(messageDisplayDTOS, inboxItemsLayout, inflater, sdf);
    }

    /*
     *
     *
     * */
    private void setViewsForMessages(List<MessageDisplayDTO> messageDisplayDTOS, LinearLayout linearLayout, LayoutInflater inflater, SimpleDateFormat sdf) {

        for (int i = 0; i < messageDisplayDTOS.size(); i++) {
            if (messageDisplayDTOS.get(i).getMessageType().equalsIgnoreCase("notification")
                    && (!selectedTypeOfMessages.equalsIgnoreCase("drivers"))) {
                setNotificationMessage(linearLayout,
                        sdf, messageDisplayDTOS.get(i), inflater);


            } else if (messageDisplayDTOS.get(i).getMessageType().equalsIgnoreCase("ride")
                    && (!selectedTypeOfMessages.equalsIgnoreCase("notifications"))) {
                setPassengerMessage(linearLayout,
                        messageDisplayDTOS.get(i), inflater);

            }
        }
    }

    private void orderMessagesByDate(List<MessageDisplayDTO> messageDisplayDTOS) {
        for (int i = 0; i < messageDisplayDTOS.size() - 1; i++) {
            for (int j = i; j < messageDisplayDTOS.size(); j++) {
                if (messageDisplayDTOS.get(j).getLastMessageTime().after(messageDisplayDTOS.get(i).getLastMessageTime())) {
                    Collections.swap(messageDisplayDTOS, i, j);
                }
            }
        }
    }

    private void filterThroughMessagesAndSet(List<MessageDisplayDTO> messageDisplayDTOS, MessageFullDTO message) {
        boolean isUpdated = false;
        Log.d("MESSAGE INFO", message.getMessage());

        if (message.getReceiverId() == preferences.getLong("pref_id", 0)
                && message.getRideId() != 0
                && !message.getType().equalsIgnoreCase("notification")) {
            Long receiverId = message.getReceiverId();
            message.setReceiverId(message.getSenderId());
            message.setSenderId(receiverId);
        }
        if (message.getType().equalsIgnoreCase("notification")
                && message.getReceiverId() == preferences.getLong("pref_id", 0)) {

            messageDisplayDTOS.add(new MessageDisplayDTO(
                    message.getId(),
                    message.getReceiverId(),
                    "Name",
                    "Picture",
                    message.getTimeOfSending(),
                    message.getMessage(),
                    message.getType(),
                    message.getRideId()
            ));
            return;
        }
        for (MessageDisplayDTO messageDisplayDTO : messageDisplayDTOS) {
            if (messageDisplayDTO.getReceiverId() == message.getReceiverId()
                    && !message.getType().equalsIgnoreCase("support")) {
                messageDisplayDTO.setLastMessage(message.getMessage());
                messageDisplayDTO.setLastMessageTime(message.getTimeOfSending());
                messageDisplayDTO.setId(message.getId());
                isUpdated = true;
                break;
            }
        }
        if (!isUpdated && !message.getType().equalsIgnoreCase("support")) {
            messageDisplayDTOS.add(new MessageDisplayDTO(
                    message.getId(),
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

    private void setPassengerMessage(LinearLayout linearLayout, MessageDisplayDTO messageDisplayDTO, LayoutInflater inflater) {

        View passengerMessage = inflater.inflate(R.layout.inbox_list_item, (ViewGroup) getView(), false);
        TextView contactName = passengerMessage.findViewById(R.id.contact_name);
        String name = messageDisplayDTO.getReceiverId().toString();
        contactName.setText(name);
        TextView lastMessage = passengerMessage.findViewById(R.id.last_message);
        lastMessage.setText(messageDisplayDTO.getLastMessage());

        Call<UserDTO> call = ServiceUtils.userService.findById(messageDisplayDTO.getReceiverId());
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NonNull Call<UserDTO> call, @NonNull Response<UserDTO> response) {
                if (!response.isSuccessful()) {
                    Log.d("User Error", "Something went wrong fetching user");
                    return;
                }
                assert response.body() != null;
                UserDTO user = response.body();
                TextView contactName = passengerMessage.findViewById(R.id.contact_name);
                String fullName = user.getName() + " " + user.getSurname();
                contactName.setText(fullName);
                messageDisplayDTO.setContactName(fullName);

                if (!user.getProfilePicture().contains(",")) {
                    return;
                }

                String base64Image = user.getProfilePicture().split(",")[1];
                byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                CircleImageView cv = passengerMessage.findViewById(R.id.imgChatUserAvatar);
                cv.setImageBitmap(decodedByte);


            }

            @Override
            public void onFailure(@NonNull Call<UserDTO> call, @NonNull Throwable t) {
                Log.d("User Error", "Couldnt fetch user");
            }
        });
        setOnClickListener(passengerMessage, messageDisplayDTO);
        linearLayout.addView(passengerMessage);
    }

    private void setOnClickListener(View messageView, MessageDisplayDTO message) {
        messageView.setOnClickListener(view -> {
            Long senderId = preferences.getLong("pref_id", 0);
            Long receiverId = message.getReceiverId();
            String messageType = message.getMessageType();
            MessageBundleDTO messageBundleDTO = new MessageBundleDTO(senderId, receiverId, message.getRideId(), messageType);
            Bundle args = new Bundle();
            args.putParcelable("message", messageBundleDTO);
            PassengerChatFragment chatFragment = new PassengerChatFragment();
            chatFragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, chatFragment).addToBackStack(null).commit();
        });
    }

    private void setNotificationMessage(LinearLayout linearLayout, SimpleDateFormat sdf, MessageDisplayDTO messageDisplayDTO, LayoutInflater inflater) {
        View notificationMessage = inflater.inflate(R.layout.notification_item, (ViewGroup) getView(), false);

        TextView notification = notificationMessage.findViewById(R.id.contact_name);
        notification.setText(messageDisplayDTO.getLastMessage());
        String date = sdf.format(messageDisplayDTO.getLastMessageTime());
        TextView notificationDate = notificationMessage.findViewById(R.id.time_and_date);
        notificationDate.setText(date);
        linearLayout.addView(notificationMessage);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}