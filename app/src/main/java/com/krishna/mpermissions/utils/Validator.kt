package com.krishna.mpermissions.utils

import android.content.Context
import android.util.Patterns
import java.util.regex.Pattern


fun Context.validateEmailField(email: String): String {
    if (email.isEmpty()) {
        return getString(ProjectStrings.error_enter_email)
    }
//    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//        return getString(ProjectStrings.error_enter_valid_email)
//    }
    return ""
}

fun Context.validatePasswordField(password: String): String {
    if (password.isEmpty()) {
        return getString(ProjectStrings.enter_password)
    }
    return ""
}

fun Context.validateFirstName(name: String): String {
    val namePattern = "^[\\p{L} .'-]+$"
    val pattern = Pattern.compile(namePattern)
    val matcher = pattern.matcher(name)
    return if (!matcher.matches()) {
        getString(ProjectStrings.wrong_first_name)
    } else {
        ""
    }
}

fun Context.validateLastName(name: String): String {
    val namePattern = "^[\\p{L} .'-]+$"
    val pattern = Pattern.compile(namePattern)
    val matcher = pattern.matcher(name)
    return if (!matcher.matches()) {
        getString(ProjectStrings.wrong_last_name)
    } else {
        ""
    }
}

@Suppress("unused")
fun compareStrings(str1: String, str2: String): Boolean {
    return str1 == str2
}


