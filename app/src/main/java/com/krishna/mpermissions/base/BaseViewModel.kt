package com.krishna.mpermissions.base

import android.content.Context
import androidx.databinding.BaseObservable

open class BaseViewModel<N>(private var navigator: N, protected var context: Context) :
    BaseObservable(), BaseNetworkCallBack {

    override fun onUnauthorised() {
        (navigator as BaseNavigator).onUnauthorised()
    }

    override fun onLogout() {
        (navigator as BaseNavigator).onLogout()
    }

    override fun onUpdateApp(mandatory: Boolean) {
        (navigator as BaseNavigator).onUpdateApp(mandatory)
    }

    override fun onMaintenance() {
        (navigator as BaseNavigator).onMaintenance()
    }
}
