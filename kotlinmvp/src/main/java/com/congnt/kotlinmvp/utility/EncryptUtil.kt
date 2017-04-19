package com.congnt.kotlinmvp.utility

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

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

