package com.cipolat.loginespresso.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.cipolat.loginespresso.R

object DialogFactory {
    fun showInfoDialog(title: String?, message: String?, ctx: Context) {
        AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(ctx.getString(R.string.ok_message)) { dialog, _ -> dialog.dismiss() }.show()
    }
}