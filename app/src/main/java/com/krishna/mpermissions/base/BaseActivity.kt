package com.krishna.mpermissions.base

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krishna.mpermissions.R
import com.krishna.mpermissions.ui.login.LoginActivity
import com.krishna.mpermissions.utils.AppPreference
import com.krishna.mpermissions.utils.isLayoutDirectionRTL
import com.krishna.mpermissions.utils.openPlayStore
import com.krishna.mpermissions.dialog.AppUpdateAlertDialog
import com.krishna.mpermissions.dialog.LogoutAlertDialog
import com.krishna.mpermissions.dialog.logoutDialogDelegate
import com.krishna.mpermissions.dialog.updateDialogDelegate

import java.util.*

open class BaseActivity : AppCompatActivity(), BaseNavigator, logoutDialogDelegate,
        updateDialogDelegate {

    override fun onUnauthorised() {
        LogoutAlertDialog.createUnauthorisedDialog(this, this)
    }

    override fun appUpdate() {
        openPlayStore()
    }

    override fun onLogout() {
        AppPreference.clearPreference(this)
        val intent = LoginActivity.getInstance(this)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onUpdateApp(mandatory: Boolean) {
        if (mandatory) {
            AppUpdateAlertDialog.createDialogForceUpdate(this, this)
        } else {
            AppUpdateAlertDialog.createDialog(this, this)
        }
    }

    override fun onMaintenance() {

    }

    override fun doLogout() {
        AppPreference.clearPreference(this)
        val intent = LoginActivity.getInstance(this)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale()
        setTheme(R.style.AppTheme)
        BaseViewModel(this, this)
    }


    fun rotateView(view: View) {
        if (isLayoutDirectionRTL()) {
            view.rotation = 180F
        }
    }

    @Suppress("deprecation")
    private fun setLocale() {
        val locale = Locale(AppPreference.loadLocale(this))
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}