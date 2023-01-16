package com.example.uberapp_tim3.dialogs;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import com.example.uberapp_tim3.activities.NewRideNotificationActivity;


public class RejectionDialog extends AlertDialog.Builder {


    public RejectionDialog(Context context, NewRideNotificationActivity newRideNotificationActivity) {
        super(context);
        setUpDialog(newRideNotificationActivity);
    }

    private void setUpDialog(NewRideNotificationActivity newRideNotificationActivity) {
        setTitle("Input reason of rejection");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        setView(input);

        setPositiveButton("OK", (dialog, id) -> {
            dialog.dismiss();
            newRideNotificationActivity.finish();

        });

        setNegativeButton("CANCEL", (dialog, id) -> dialog.cancel());
    }


}
