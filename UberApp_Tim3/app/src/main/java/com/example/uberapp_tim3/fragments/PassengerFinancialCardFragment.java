package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.uberapp_tim3.R;

public class PassengerFinancialCardFragment extends Fragment {



    public PassengerFinancialCardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_financial_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RadioButton rbCard = getActivity().findViewById(R.id.rbCard);
        rbCard.setChecked(true);
        ((TextView) getActivity().findViewById(R.id.txtEnterCardAmmount)).setText("Enter Card Ammount(RSD):");
        ((EditText) getActivity().findViewById(R.id.txtPassengerCardNumber)).setText("5000");
        setOnCheckedChangedListeners();
        setOnClickListeners();
        super.onViewCreated(view, savedInstanceState);
    }

    private void setOnCheckedChangedListeners() {
        RadioButton rbCard = getActivity().findViewById(R.id.rbCard);
        RadioButton rbPaypal = getActivity().findViewById(R.id.rbPaypal);
        RadioButton rbBitcoin = getActivity().findViewById(R.id.rbBitcoin);

        rbCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ((TextView) getActivity().findViewById(R.id.txtEnterCardAmmount)).setText("Enter Card Ammount(RSD):");
                    ((EditText) getActivity().findViewById(R.id.txtPassengerCardNumber)).setText("");
                    rbPaypal.setChecked(false);
                    rbBitcoin.setChecked(false);
                }
            }
        });

        rbPaypal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ((TextView) getActivity().findViewById(R.id.txtEnterCardAmmount)).setText("Enter Paypal Money Ammount($):");
                    ((EditText) getActivity().findViewById(R.id.txtPassengerCardNumber)).setText("");
                    rbCard.setChecked(false);
                    rbBitcoin.setChecked(false);
                }
            }
        });

        rbBitcoin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ((TextView) getActivity().findViewById(R.id.txtEnterCardAmmount)).setText("Enter Bitcoin Ammount(BTC):");
                    ((EditText) getActivity().findViewById(R.id.txtPassengerCardNumber)).setText("");
                    rbCard.setChecked(false);
                    rbPaypal.setChecked(false);
                }
            }
        });
    }

    private void setOnClickListeners() {
        ((Button) getActivity().findViewById(R.id.btnCancelEditPayment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        ((Button) getActivity().findViewById(R.id.btnSaveEditPayment)).setOnClickListener(null);

    }
}