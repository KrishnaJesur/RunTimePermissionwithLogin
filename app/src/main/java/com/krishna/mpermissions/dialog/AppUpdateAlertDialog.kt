package com.krishna.mpermissions.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.krishna.mpermissions.R
import com.krishna.mpermissions.utils.ProjectStrings


typealias updateDialogDelegate = AppUpdateAlertDialog.AppUpdateAlertDialogButtonClick

object AppUpdateAlertDialog {
    fun createDialogForceUpdate(
        context: Context,
        appUpdateAlertDialogButton: AppUpdateAlertDialogButtonClick
    ) {
        AlertDialog.Builder(context).setIcon(R.mipmap.app_icon_square)
            .setTitle(context.getString(ProjectStrings.title_update_app))
            .setCancelable(false)
            .setMessage(context.getString(ProjectStrings.message_update_app))
            .setPositiveButton(context.getString(ProjectStrings.button_update)) { dialog, _ ->
                appUpdateAlertDialogButton.appUpdate()
                dialog.dismiss()
            }.show()
    }

    fun createDialog(
        context: Context,
        appUpdateAlertDialogButton: AppUpdateAlertDialogButtonClick
    ) {
        AlertDialog.Builder(context).setIcon(R.mipmap.app_icon_square)
            .setTitle(context.getString(ProjectStrings.title_update_app))
            .setCancelable(true)
            .setMessage(context.getString(ProjectStrings.message_update_app))
            .setPositiveButton(context.getString(ProjectStrings.button_update)) { dialog, _ ->
                appUpdateAlertDialogButton.appUpdate()
                dialog.dismiss()
            }.setNegativeButton(context.getString(ProjectStrings.button_later)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    interface AppUpdateAlertDialogButtonClick {
        fun appUpdate()
    }
}