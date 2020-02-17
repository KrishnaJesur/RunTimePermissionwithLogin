package com.krishna.mpermissions.ui.login

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.krishna.mpermissions.base.BaseViewModel
import com.krishna.mpermissions.network.ApiProvider
import com.krishna.mpermissions.network.ApiRequest
import com.krishna.mpermissions.network.NetworkCallBack
import com.krishna.mpermissions.utils.Connectivity
import com.krishna.mpermissions.utils.ProjectStrings
import com.krishna.mpermissions.utils.validateEmailField
import com.krishna.mpermissions.utils.validatePasswordField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private var loginNavigator: LoginNavigator, context: Context) : BaseViewModel<LoginNavigator>(loginNavigator, context),
        NetworkCallBack {

    private var api: ApiRequest? = null
    private val mTAG: String? = "LoginViewModel"

    fun doLogin(loginReqModel: LoginReqModel) {

        val loginData: String = Gson().toJson(loginReqModel)

        val validEmail = context.validateEmailField(loginReqModel.userId)

        if (validEmail != "") {
            loginNavigator.inValidEmail(validEmail)
            return
        }

        val validPassword = context.validatePasswordField(loginReqModel.userPassword)

        if (validPassword.isNotEmpty()) {
            loginNavigator.inValidPassword(validPassword)
            return
        }

        Log.e(mTAG, loginData)

        loginNavigator.clearErrors()

        if (Connectivity.isNetworkConnected(context)) {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val service = ApiProvider.provideApi(context)!!
                    api = ApiRequest(context)
                    api?.apiRequest(service.loginAsync(loginReqModel), this@LoginViewModel)
                } catch (e: Exception) {
                    loginNavigator.onLoginFailed(context.getString(ProjectStrings.internet_connection_error))
                }
            }
        } else {
            loginNavigator.onLoginFailed(context.getString(ProjectStrings.internet_connection_error))
        }
    }

    override fun onSuccess(response: Any?) {
        if (response is LoginResModel) {
            loginNavigator.onLoginSuccess(response)
        }
    }

    override fun onError(errorBody: String) {
        loginNavigator.onLoginFailed(errorBody)
    }
}