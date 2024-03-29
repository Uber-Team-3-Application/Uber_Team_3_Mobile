package com.example.uberapp_tim3.fragments.passenger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.model.DTO.MessageBundleDTO;
import com.example.uberapp_tim3.model.DTO.MessageFullDTO;
import com.example.uberapp_tim3.model.DTO.Paginated;
import com.example.uberapp_tim3.model.DTO.SendMessageDTO;
import com.example.uberapp_tim3.model.DTO.UserDTO;
import com.example.uberapp_tim3.services.ServiceUtils;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerChatFragment extends Fragment {

    public LinearLayout mainLayout;
    public String[] messages;
    public ViewGroup container;
    TextView txtContactName;
    CircleImageView imgAvatar;
    ImageView imgViewGoBackChat;

    private Button btnSendMessage;
    private TextView txtSendMessage;
    private Long receiverId;
    private Long rideId;
    private Long senderId;
    private String messageType;
    private Handler handler = new Handler();
    final int delay = 2000;

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
        Bundle bundle = this.getArguments();
        MessageBundleDTO messageBundleDTO = null;
        if(bundle == null ) return;

        messageBundleDTO = bundle.getParcelable("message");
        this.receiverId = messageBundleDTO.getReceiverId();
        this.senderId = messageBundleDTO.getSenderId();
        this.messageType = messageBundleDTO.getMessageType();
        if(!this.messageType.equalsIgnoreCase("support")){
            this.rideId = messageBundleDTO.getRideId();
        }else{
            this.rideId = 0L;
        }

        txtContactName = getActivity().findViewById(R.id.contactName);
        imgAvatar = getActivity().findViewById(R.id.imgChatAvatar);
        btnSendMessage = getActivity().findViewById(R.id.btnSendMessage);
        txtSendMessage = getActivity().findViewById(R.id.txtSendMessage);

        if(!messageType.equalsIgnoreCase("panic") && !messageType.equalsIgnoreCase("support")) {
            setUserName(this.receiverId);
        }else{
            txtContactName.setText("SUPPORT");
        }
        setOnClickListenerForButtonSendMessage();
        setOnClickListenerForBack();
        loadMessages();
    }

    private void setOnClickListenerForBack() {
        imgViewGoBackChat = getView().findViewById(R.id.imgViewGoBackChat);
        imgViewGoBackChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();

            }
        });
    }

    private void setUserName(Long receiverId) {
        Call<UserDTO> call = ServiceUtils.userService.findById(receiverId);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if(!response.isSuccessful()) return;
                UserDTO user = response.body();
                assert user != null;

                String fullName = user.getName() + " " + user.getSurname();
                txtContactName.setText(fullName);

                String profilePicture = user.getProfilePicture();
                if(profilePicture.contains(",")) {
                    String base64Image = profilePicture.split(",")[1];
                    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imgAvatar.setImageBitmap(decodedByte);
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Log.d("User error", "Couldn't find user!");

            }
        });
    }

    private void setOnClickListenerForButtonSendMessage() {
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = txtSendMessage.getText().toString().trim();

                if(message.equalsIgnoreCase("")) return;
                txtSendMessage.setText("");
                SendMessageDTO sendMessageDTO = new SendMessageDTO();
                sendMessageDTO.setMessage(message);
                sendMessageDTO.setRideId(rideId);
                sendMessageDTO.setType(messageType);

                Call<MessageFullDTO> call = ServiceUtils.userService.sendMessage(receiverId, sendMessageDTO);
                call.enqueue(new Callback<MessageFullDTO>() {
                    @Override
                    public void onResponse(Call<MessageFullDTO> call, Response<MessageFullDTO> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getContext(), "Couldn't send message", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        MessageFullDTO messageFullDTO = response.body();
                        assert  messageFullDTO != null;
                        loadMessages();
                    }

                    @Override
                    public void onFailure(Call<MessageFullDTO> call, Throwable t) {
                        Log.d("Messagge error", "Couldn't send message!");
                    }
                });
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    private void loadMessages() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Call<Paginated<MessageFullDTO>> call = ServiceUtils.userService.getMessages(senderId);

                call.enqueue(new Callback<Paginated<MessageFullDTO>>() {
                    @Override
                    public void onResponse(Call<Paginated<MessageFullDTO>> call, Response<Paginated<MessageFullDTO>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getContext(), "Couldn't fetch messages", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        assert response.body() != null;
                        List<MessageFullDTO> messages = response.body().getResults();
                        addMessagesToLayout(messages);

                    }

                    @Override
                    public void onFailure(Call<Paginated<MessageFullDTO>> call, Throwable t) {
                        Log.d("Messagge error", "Couldn't send message!");

                    }
                });
                handler.postDelayed(this, delay);
            }
        }, 0);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void addMessagesToLayout(List<MessageFullDTO> messages) {
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.chatLayout);
        linearLayout.removeAllViews();
        LayoutInflater inflater = (LayoutInflater)getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(MessageFullDTO message: messages) {

            if(isMessageInvalid(message)) continue;

            AddMessageToAppropriateSide(linearLayout, inflater, message);
        }

    }

    private void AddMessageToAppropriateSide(LinearLayout linearLayout, LayoutInflater inflater, MessageFullDTO message) {
        View itemBox;
        if (Objects.equals(message.getSenderId(), senderId)){
            itemBox = inflater.inflate(R.layout.sent_message, (ViewGroup) getView(), false);
            TextView content = (TextView) itemBox.findViewById(R.id.sent_msg);
            content.setText(message.getMessage());
        }
        else{
            itemBox = inflater.inflate(R.layout.recived_message, (ViewGroup) getView(), false);
            TextView content = (TextView) itemBox.findViewById(R.id.recived_msg);
            content.setText(message.getMessage());
        }
        linearLayout.addView(itemBox);
    }

    private boolean isMessageInvalid(MessageFullDTO message) {
        if(messageType.equalsIgnoreCase("panic") || messageType.equalsIgnoreCase("support")){
            if(!message.getType().equalsIgnoreCase("panic") && !message.getType().equalsIgnoreCase("support"))
                return true;
        }else {
            if (!message.getType().equalsIgnoreCase(messageType)) return true;
        }
        if(this.rideId != 0L && this.rideId != null) {
            if (!Objects.equals(message.getRideId(), rideId)) return true;
        }
        if (message.getSenderId() != senderId && message.getSenderId() != receiverId) return true;
        return message.getReceiverId() != senderId && message.getReceiverId() != receiverId;
    }
}
