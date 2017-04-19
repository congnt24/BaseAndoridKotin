package com.congnt.kotlinmvp.utility

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Context.copyText(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.primaryClip = ClipData.newPlainText("text", text)
}

fun Context.getClipboarText(): String? {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    return clipboard.primaryClip.getItemAt(0).coerceToText(this).toString()
}