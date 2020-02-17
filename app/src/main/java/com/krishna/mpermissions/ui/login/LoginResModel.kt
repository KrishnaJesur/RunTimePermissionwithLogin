package com.krishna.mpermissions.ui.login

import com.google.gson.annotations.SerializedName

data class LoginResModel(

        @SerializedName("status_code")
        var status_code: String,
        @SerializedName("message")
        var message: String,
        @SerializedName("data")
        var data: Data

)

data class Data(
        @SerializedName("accesstoken")
        val accesstoken: String,
        @SerializedName("Name")
        val username: String,
        @SerializedName("Photo")
        val userprofile: String,
        @SerializedName("MobileNo")
        val usermobile: String
)
//
//@BindingAdapter("android:parent_photo")
//fun setParentPhoto(view: ImageView, url: String) {
//    val finalUrl = IMAGE_URL + url
//
//    val requestOptions = RequestOptions().placeholder(R.drawable.ic_default_avatar)
//        .error(R.drawable.ic_default_avatar).diskCacheStrategy(
//            DiskCacheStrategy.ALL
//        )
//    Glide.with(view).setDefaultRequestOptions(requestOptions).load(finalUrl)
//        .into(view)
//}