package com.cipolat.loginespresso.Login;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.cipolat.loginespresso.R;

public class DialogFactory {
    public static void showInfoDialog(String title,String message,Context ctx){
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(ctx.getString(R.string.ok_message), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
