package com.krishna.mpermissions.base

interface BaseNetworkCallBack {
    fun onLogout()
    fun onUpdateApp(mandatory: Boolean)
    fun onMaintenance()
    fun onUnauthorised()
}
