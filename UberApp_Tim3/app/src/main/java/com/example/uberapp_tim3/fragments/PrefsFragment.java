package com.example.uberapp_tim3.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import com.example.uberapp_tim3.R;

public class PrefsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }


}