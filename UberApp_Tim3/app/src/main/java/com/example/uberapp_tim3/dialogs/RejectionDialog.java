package com.example.uberapp_tim3.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;


public class RejectionDialog extends AlertDialog.Builder {


    public RejectionDialog(Context context, Dialog mainDialog) {
        super(context);
        setUpDialog(mainDialog);
    }

    private void setUpDialog(Dialog mainDialog) {
        setTitle("Input reason of rejection");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        setView(input);

        setPositiveButton("OK", (dialog, id) -> {
            dialog.dismiss();
            mainDialog.cancel();
        });

        setNegativeButton("CANCEL", (dialog, id) -> dialog.cancel());
    }


}
