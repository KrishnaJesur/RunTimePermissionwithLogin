package com.krishna.mpermissions.ui.login

import com.google.gson.annotations.SerializedName

data class LoginReqModel(@SerializedName("username") var userId: String = "",
                         @SerializedName("password") var userPassword: String = "") {
}