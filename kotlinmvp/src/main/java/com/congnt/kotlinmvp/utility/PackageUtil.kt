package com.congnt.kotlinmvp.utility

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.speech.RecognizerIntent

import java.text.MessageFormat
import java.util.Locale

/**
 * PackageUtil helps to handle methods related to package.
 */
object PackageUtil {

    val FACEBOOK = "com.facebook.katana"
    val TWITTER = "com.twitter.android"
    val GOOGLE_PLUS = "com.google.android.apps.plus"
    val GMAP = "com.google.android.apps.maps"
    val YOUTUBE = "com.google.android.apps.youtube"
    val GMAIL = "com.google.android.gm"
    val INBOX = "com.google.android.apps.inbox"
    val MAIL = "android.mail"
    val PINTEREST = "com.pinterest"
    val TUMBLR = "com.tumblr"
    val FANCY = "com.thefancy.app"
    val FLIPBOARD = "flipboard.app"
    val GOOGLE_APP = "com.google.android.googlequicksearchbox"
    val SPEECH_TO_TEXT = RecognizerIntent.ACTION_RECOGNIZE_SPEECH
    val MARKET_APP_URL_TEMPLATE = MessageFormat("market://details?id={0}", Locale.ENGLISH)
    val PLAYSTORE_APP_URL_TEMPLATE = MessageFormat("https://play.google.com/store/apps/details?id={0}", Locale.ENGLISH)


    //PACKAGE
    fun isIntentAvailable(intent: Intent, context: Context): Boolean {
        return context.packageManager.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER).size > 0
    }

    /**
     * get resolve info for opening one of these application: GMAIL, MAIL ....
     * @param context
     * @param packageName
     * @param intentCategory
     * @return
     */
    fun getResolveInfo(context: Context, packageName: String, intentCategory: String): ResolveInfo {
        val packageManager = context.packageManager
        val intent = Intent()
        intent.`package` = packageName
        intent.addCategory(intentCategory)
        return packageManager.resolveActivity(intent, PackageManager.GET_RESOLVED_FILTER)
    }

    /**
     * Check if an application was installed
     * @param context
     * @param packageName   com.xxxx.xxxxx
     * @return
     */
    fun isInstalled(context: Context, packageName: String): Boolean {
        val packageManager = context.packageManager
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }

    /**
     * Open in google play for user to download
     * @param context
     * @param packageName
     */
    fun openPlayStore(context: Context, packageName: String = context.packageName) {
        var intent: Intent? = null
        try {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_APP_URL_TEMPLATE.format(arrayOf(packageName))))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        } catch (anfe: ActivityNotFoundException) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(PLAYSTORE_APP_URL_TEMPLATE.format(arrayOf(packageName))))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        } finally {
            if (intent != null)
                context.startActivity(intent)
        }
    }
}