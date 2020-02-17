package com.krishna.mpermissions.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.Uri.fromParts
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.view.View.LAYOUT_DIRECTION_RTL


fun Context.openAppSettings() {
    startActivity(Intent().apply {
        action = ACTION_APPLICATION_DETAILS_SETTINGS
        data = fromParts("package", packageName, null)
    })
}

fun Context.openPlayStore() {
    val appPackageName = packageName
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            )
        )
    }

}

fun Context.isLayoutDirectionRTL(): Boolean {
    return resources.configuration.layoutDirection == LAYOUT_DIRECTION_RTL
}