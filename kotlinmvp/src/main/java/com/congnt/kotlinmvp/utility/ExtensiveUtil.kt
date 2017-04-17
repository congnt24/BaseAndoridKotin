import android.content.Context
import android.text.TextUtils
import android.util.Base64
import java.math.BigDecimal
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.regex.Pattern
import kotlin.experimental.and


////////////////////////////STRING//////////////////////////////////////////
/////////////////////////////CRYPTO/////////////////////////////////
/**
 * Encode message using Base64 encoding
 */
fun String.to64(): String {
    return Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
}

/**
 * Decode encoded message using Base64
 */
fun String.from64(): String {
    return Arrays.toString(Base64.decode(this, Base64.DEFAULT))
}

/**
 * Get MD5 value for a string
 */
fun String.toMd5(): String? {
    try {
        val md = MessageDigest.getInstance("MD5")
        var md5 = BigInteger(1, md.digest(this.toByteArray())).toString(16)
        while (md5.length < 32) {
            md5 = "0" + md5
        }
        return md5
    } catch (e: NoSuchAlgorithmException) {
        return null
    }
}

/**
 * Get MD5 value for a string
 */
fun String.toSHA1(): String? {
    try {
        val md = MessageDigest.getInstance("SHA-1")
        md.update(this.toByteArray())
        val bytes = md.digest()
        val buffer = StringBuilder()
        for (b in bytes) {
            buffer.append(Integer.toString((b and 0xff.toByte()) + 0x100, 16).substring(1))
        }
        return buffer.toString()

    } catch (e: NoSuchAlgorithmException) {
        return null
    }
}


//////////////////////////CONVERT PRIMARY TYPE////////////////////////

/**
 * convert an input string to BigDecimal
 */
fun String.toBigDecimal(): BigDecimal {
    return BigDecimal(this)
}

/**
 * Checking an input string whether or not match a pattern
 */
fun String.checkMatchPattern(pattern: Pattern): Boolean {
    return pattern.matcher(this).matches()
}

/**
 * Checking an array of String whether or not have a null value
 */
fun Array<String>.isExistNull(): Boolean {
    return this.any { TextUtils.isEmpty(it) }
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
