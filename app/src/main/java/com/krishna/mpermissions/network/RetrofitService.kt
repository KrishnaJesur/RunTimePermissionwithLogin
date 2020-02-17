package com.krishna.mpermissions.network

import com.krishna.mpermissions.ui.login.LoginReqModel
import com.krishna.mpermissions.ui.login.LoginResModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @POST("DriverLogin")
    fun loginAsync(@Body loginReqModel: LoginReqModel): Deferred<Response<LoginResModel>>

//    @Multipart
//    @POST("upload/uploadimage")
//    fun updateParentProfilePhotoAsync(
//        @Query("user_id") userId: String, @Query("usertype") userType: String,
//        @Part filedata: MultipartBody.Part
//    ): Deferred<Response<ParentProfilePhotoUpdateResModel>>


}