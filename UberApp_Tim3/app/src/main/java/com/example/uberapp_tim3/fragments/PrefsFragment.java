package com.example.uberapp_tim3.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.example.uberapp_tim3.R;

public class PrefsFragment extends PreferenceFragmentCompat {

    private static PrefsFragment newInstance() {
        PrefsFragment fragment = new PrefsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }


}