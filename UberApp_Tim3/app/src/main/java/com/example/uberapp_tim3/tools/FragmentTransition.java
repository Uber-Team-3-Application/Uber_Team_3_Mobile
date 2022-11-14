package com.example.uberapp_tim3.tools;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.uberapp_tim3.R;

public class FragmentTransition {

    public static void to(Fragment newFragment, FragmentActivity activity)
    {
        to(newFragment, activity, true);
    }

    public static void to(Fragment newFragment, FragmentActivity activity, boolean addToBackstack)
    {
        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.mainContent, newFragment);
        if(addToBackstack) transaction.addToBackStack(null);
        transaction.commit();

    }

    public static void remove(Fragment fragment, FragmentActivity activity) // TODO izbaciti fragment parametar
    {
        activity.getSupportFragmentManager().popBackStack();
    }
}
