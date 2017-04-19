package com.congnt.kotlinmvp.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.util.Base64
import android.view.View
import java.math.BigDecimal
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


/**
 * Created by congn on 4/19/2017.
 */
//////////////////////////////////ENCODE STRING////////////////////////
/////CRYPT///////////
private var AES_Transformation = "AES/ECB/NoPadding"

private val AES_Algorithm = "AES"
/**
 * Encode message using Base64 encoding
 */
fun String.to64Encode(): String {
    return Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
}

/**
 * Decode encoded message using Base64
 */
fun String.to64Decode(): String {
    return Arrays.toString(Base64.decode(this, Base64.DEFAULT))
}

fun String.to64UrlSafeEncode(): ByteArray {
    return Base64.encode(toByteArray(), Base64.URL_SAFE)
}

fun encryptAES(data: ByteArray, key: ByteArray): ByteArray? {
    return desTemplate(data, key, AES_Algorithm, AES_Transformation, true)
}

fun decryptAES(data: ByteArray, key: ByteArray): ByteArray? {
    return desTemplate(data, key, AES_Algorithm, AES_Transformation, false)
}

fun desTemplate(data: ByteArray?, key: ByteArray?, algorithm: String, transformation: String, isEncrypt: Boolean): ByteArray? {
    if (data == null || data.size == 0 || key == null || key.size == 0) return null
    try {
        val keySpec = SecretKeySpec(key, algorithm)
        val cipher = Cipher.getInstance(transformation)
        val random = SecureRandom()
        cipher.init(if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE, keySpec, random)
        return cipher.doFinal(data)
    } catch (e: Throwable) {
        e.printStackTrace()
        return null
    }
}


fun String.toUrlEncode(): String {
    return URLEncoder.encode(this, "UTF-8")
}

fun String.toUrlDecode(): String {
    return URLDecoder.decode(this, "UTF-8")
}

fun String.toHtmlEncode(): String {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

        return Html.escapeHtml(this)

    } else {
        val out = StringBuilder()

        var i = 0
        val len = this.length
        while (i < len) {
            val c = this[i]
            if (c == '<') {
                out.append("&lt;")
            } else if (c == '>') {
                out.append("&gt;")
            } else if (c == '&') {
                out.append("&amp;")
            } else if (c.toInt() in 0xD800..0xDFFF) {
                if (c.toInt() < 0xDC00 && i + 1 < len) {
                    val d = this[i + 1]
                    if (d.toInt() in 0xDC00..0xDFFF) {
                        i++
                        val codepoint = 0x010000 or (c.toInt() - 0xD800 shl 10) or d.toInt() - 0xDC00
                        out.append("&#").append(codepoint).append(";")
                    }
                }
            } else if (c.toInt() > 0x7E || c < ' ') {
                out.append("&#").append(c.toInt()).append(";")
            } else if (c == ' ') {
                while (i + 1 < len && this[i + 1] == ' ') {
                    out.append("&nbsp;")
                    i++
                }
                out.append(' ')
            } else {
                out.append(c)
            }
            i++
        }
        return out.toString()
    }
}

fun String.toHtmlDecode(): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        return Html.fromHtml(this).toString()
    }
}

/////////////////////////////////////FLOAT - DIMENSION////////////////////////
fun Float.dpToPx(context: Context): Float {
    return this * context.resources.displayMetrics.density
}

fun Float.pxToDp(context: Context): Float {
    return this / context.resources.displayMetrics.density
}

fun Float.spToPx(context: Context): Float {
    return this * context.resources.displayMetrics.scaledDensity
}

fun Float.pxToSp(context: Context): Float {
    return this / context.resources.displayMetrics.scaledDensity
}

/**
 * convert an input string to BigDecimal
 */
fun String.toBigDecimal(): BigDecimal {
    return BigDecimal(this)
}

////////////////////VIEW////////////////
fun View.toBitmap(): Bitmap? {
    val ret = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(ret)
    if (background != null) {
        background.draw(canvas)
    } else {
        canvas.drawColor(Color.WHITE)
    }
    draw(canvas)
    return ret

}