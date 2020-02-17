package com.krishna.mpermissions.network

import android.content.Context
import android.util.Log
import com.krishna.mpermissions.base.BaseNetworkCallBack
import com.krishna.mpermissions.utils.ProjectStrings
import kotlinx.coroutines.*
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class ApiRequest(var context: Context) {
    private val mTag = ApiRequest::class.java.simpleName
    private var scope: Job? = null

    suspend fun <T : Any> apiRequest(call: Deferred<T>, callBack: NetworkCallBack) {
        scope = GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = call.await() as Response<*>
                when {
                    response.isSuccessful -> {
                        val logout =
                            (response.headers()["is_session_timeout"] != null && response.headers()["is_session_timeout"]!!.toBoolean())

                        val update =
                            (response.headers()["update_app"] != null && response.headers()["update_app"]!!.toBoolean())

                        val isUpdateMandatory =
                            (response.headers()["is_update_mandatory"] != null && response.headers()["is_update_mandatory"]!!.toBoolean())

                        when {
                            logout -> {
                                (callBack as BaseNetworkCallBack).onLogout()
                                return@launch
                            }
                            update -> {
                                (callBack as BaseNetworkCallBack).onUpdateApp(isUpdateMandatory)
                                return@launch
                            }
                            else -> callBack.onSuccess(response.body())
                        }
                    }
                    response.code() == HTTP_UNAUTHORIZED -> {
                        (callBack as BaseNetworkCallBack).onUnauthorised()
                        return@launch
                    }
                    response.code() == HTTP_NOT_FOUND -> {
                        callBack.onError(context.getString(ProjectStrings.service_unavailable))
                        return@launch
                    }
                    else -> {
                        callBack.onError(response.errorBody().toString())
                        return@launch
                    }
                }
            } catch (e: Exception) {
                Log.e(mTag, e.message.toString())
                callBack.onError(context.getString(ProjectStrings.request_timeout))
            }
        }
    }
}