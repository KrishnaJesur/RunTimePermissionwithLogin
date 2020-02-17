package com.krishna.mpermissions.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

@Suppress("unused")
object ToastUtils {

    fun showShortToast(@NonNull context: AppCompatActivity, @NonNull message: String) {
        when (message) {
            "" -> {
                throw Exception("Message should not empty")
            }
        }
        context.runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun showLongToast(@NonNull context: AppCompatActivity, @NonNull message: String) {
        when (message) {
            "" -> {
                throw Exception("Message should not empty")
            }
        }
        context.runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }


    fun showShortToast(@NonNull context: Context, @NonNull message: String) {
        when (message) {
            "" -> {
                throw Exception("Message should not empty")
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(@NonNull context: Context, @NonNull message: String) {
        when (message) {
            "" -> {
                throw Exception("Message should not empty")
            }
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}