package com.krishna.mpermissions.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.krishna.mpermissions.R
import com.krishna.mpermissions.utils.ProjectStrings


typealias logoutDialogDelegate = LogoutAlertDialog.LogoutDialogButtonClick

object LogoutAlertDialog {
    fun createDialog(context: Context, logoutDialogButtonClick: LogoutDialogButtonClick) {
        AlertDialog.Builder(context).setIcon(R.mipmap.app_icon_square)
            .setTitle(context.getString(ProjectStrings.logout))
            .setMessage(context.getString(ProjectStrings.logout_alert_message))
            .setPositiveButton(context.getString(ProjectStrings.option_yes)) { dialog, _ ->
                logoutDialogButtonClick.doLogout()
                dialog.dismiss()
            }.setNegativeButton(context.getString(ProjectStrings.option_no)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun createUnauthorisedDialog(
        context: Context,
        logoutDialogButtonClick: LogoutDialogButtonClick
    ) {
        AlertDialog.Builder(context).setIcon(R.mipmap.app_icon_square)
            .setTitle(context.getString(ProjectStrings.logout))
            .setCancelable(false)
            .setMessage(context.getString(ProjectStrings.you_have_been_unauthorised))
            .setPositiveButton(context.getString(ProjectStrings.logout)) { dialog, _ ->
                logoutDialogButtonClick.doLogout()
                dialog.dismiss()
            }.show()
    }

    interface LogoutDialogButtonClick {
        fun doLogout()
    }
}