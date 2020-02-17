package com.krishna.mpermissions.utils

import android.content.Context
import androidx.annotation.NonNull
import com.google.gson.Gson
import com.krishna.mpermissions.OrataroApplication
import com.krishna.mpermissions.R
import com.krishna.mpermissions.ui.login.LoginResModel

object AppPreference {
    private const val PREFERENCE_NAME = "APP_DATA"
    private const val key_isLogin = "IS_LOGIN"
    private const val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"
    private const val key_userId = "USER_ID"
    private const val key_userName = "USER_NAME"
    private const val key_userMobile = "USER_MOBILE"
    private const val key_userProfile = "USER_PROFILE"
    private const val KEY_LOGIN_DATA = "LOGIN_DATA"
    private const val KEY_LOCAL = "LOCAL"
    private const val KEY_PARENT_DATA = "PARENT_DATA"


    /**
     * Save parent data from login
     * */
    fun saveParentData(context: Context, loginResModel: LoginResModel) {
        val parentData = Gson().toJson(loginResModel)
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
                .putString(KEY_PARENT_DATA, parentData).apply()
    }

    @NonNull
    fun loadParentData(context: Context): LoginResModel {
        return Gson().fromJson(
                context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                        .getString(KEY_PARENT_DATA, ""), LoginResModel::class.java
        )
    }

    fun setLogin(@NonNull isLogin: Boolean) {
        OrataroApplication.instance.getSharedPreferences(OrataroApplication.instance.resources.getString(R.string.app_name), Context.MODE_PRIVATE).edit().putBoolean(key_isLogin, isLogin).apply()
    }

    fun getLogin(): Boolean {
        return OrataroApplication.instance.getSharedPreferences(OrataroApplication.instance.resources.getString(R.string.app_name), Context.MODE_PRIVATE).getBoolean(key_isLogin, false)
    }

    fun saveAccessToken(context: Context, @NonNull token: String?) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
                .putString(KEY_ACCESS_TOKEN, token).apply()
    }

    @NonNull
    fun loadAccessToken(context: Context): String? {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(KEY_ACCESS_TOKEN, "")
    }

    /**
     * Clear the saved shared preference data on logout
     * */
    fun clearPreference(context: Context) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }

    fun saveLocale(context: Context, @NonNull localCode: String) {
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
                .putString(KEY_LOCAL, localCode).apply()
    }

    @NonNull
    fun loadLocale(context: Context): String {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
                .getString(KEY_LOCAL, "en")!!
    }


}