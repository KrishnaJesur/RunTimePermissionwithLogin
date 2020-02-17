package com.krishna.mpermissions.ui.login

interface LoginNavigator {
    fun onLoginSuccess(loginResModel: LoginResModel)
    fun onLoginFailed(error: String)
    fun onForgetPassword()
    fun inValidEmail(error: String)
    fun inValidPassword(error: String)
    fun clearErrors()
}