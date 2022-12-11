package com.example.uberapp_tim3.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import android.os.Bundle;

import com.example.uberapp_tim3.R;
import com.example.uberapp_tim3.tools.FragmentTransition;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        FragmentTransition.to(PrefsFragment.newInstance(), this, false);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static class PrefsFragment extends PreferenceFragmentCompat {

        private static PrefsFragment newInstance() {
            Bundle args = new Bundle();

            PrefsFragment fragment = new PrefsFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.preferences);
        }

    }
}