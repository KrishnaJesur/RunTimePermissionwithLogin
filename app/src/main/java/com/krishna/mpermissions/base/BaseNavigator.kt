package com.krishna.mpermissions.base

interface BaseNavigator {
    fun onLogout()
    fun onUpdateApp(mandatory: Boolean)
    fun onMaintenance()
    fun onUnauthorised()
}