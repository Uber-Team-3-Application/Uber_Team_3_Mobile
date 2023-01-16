package com.example.uberapp_tim3.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import com.example.uberapp_tim3.R;

public class AccountSettingsFragment extends PreferenceFragmentCompat {
    public static AccountSettingsFragment newInstance() {
        Bundle args = new Bundle();

        AccountSettingsFragment fragment = new AccountSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
