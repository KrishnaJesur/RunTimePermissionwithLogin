package com.krishna.mpermissions.utils

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView

@Suppress("unused", "deprecation")
fun createSpan(
    text: String,
    textView: TextView
) {
    val ss = SpannableString(text)
    ss.setSpan(ss, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.text = ss
    textView.movementMethod = LinkMovementMethod.getInstance()
}

@Suppress("unused", "deprecation")
fun getSpannedText(text: String): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }
}
