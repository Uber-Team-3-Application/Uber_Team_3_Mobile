package com.example.uberapp_tim3.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import com.example.uberapp_tim3.R;


public class LocationDialog extends AlertDialog.Builder{

	public LocationDialog(Context context) {

		super(context);

		setUpDialog();
	}

	private void setUpDialog(){
		setTitle(R.string.oops);
		setMessage(R.string.location_disabled_message);
		setCancelable(false);

		setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				getContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//				getContext().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

				dialog.dismiss();
			}
		});

		setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

	}

	public AlertDialog prepareDialog(){
		AlertDialog dialog = create();
		dialog.setCanceledOnTouchOutside(false);

		return dialog;
	}
}

