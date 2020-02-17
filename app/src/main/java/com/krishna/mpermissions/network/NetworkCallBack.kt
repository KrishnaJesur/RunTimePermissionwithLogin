package com.krishna.mpermissions.network

import com.krishna.mpermissions.base.BaseNetworkCallBack

interface NetworkCallBack : BaseNetworkCallBack {
    fun onSuccess(response: Any?)

    fun onError(errorBody: String)
}