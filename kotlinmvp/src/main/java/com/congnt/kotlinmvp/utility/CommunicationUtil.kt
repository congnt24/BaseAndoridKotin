package com.congnt.kotlinmvp.utility

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.telephony.SmsManager
import android.util.Log


/**
 * Created by congnt24 on 27/09/2016.
 */

object CommunicationUtil {

    private val INTENT_TYPE_TEXT = "text/plain"
    var MAX_SMS_MESSAGE_LENGTH = 80

    fun sendSmsAuto(phonenumber: String, message: String) {
        val manager = SmsManager.getDefault()
        val length = message.length
        Log.d("SEND SMS AUTO", "sendSMS: $phonenumber: $message")
        if (length > MAX_SMS_MESSAGE_LENGTH) {
            val messagelist = manager.divideMessage(message)
            manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null)
        } else {
            manager.sendTextMessage(phonenumber, null, message, null, null)
        }
    }

    fun sendSms(context: Context, phoneNumber: String, message: String) {
        val smsIntent = Intent(Intent.ACTION_VIEW)
        smsIntent.type = "vnd.android-dir/mms-sms"
        smsIntent.putExtra("address", phoneNumber)
        smsIntent.putExtra("sms_body", message)
        context.startActivity(Intent.createChooser(smsIntent, "SMS:"))
    }

    fun dialTo(context: Context, phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber))
        callIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(callIntent)
    }

    //Email

    /**
     * Direct open email app
     * @param context
     * @param subject
     * @param text
     */
    fun openMailApp1(context: Context, dialogTitle: String, subject: String, text: String) {
        var dialogTitle = dialogTitle
        var resolveInfo: ResolveInfo? = null
        if (PackageUtil.isInstalled(context, PackageUtil.GMAIL)) {
            resolveInfo = PackageUtil.getResolveInfo(context, PackageUtil.GMAIL, Intent.CATEGORY_APP_EMAIL)
        } else if (PackageUtil.isInstalled(context, PackageUtil.INBOX)) {
            resolveInfo = PackageUtil.getResolveInfo(context, PackageUtil.INBOX, Intent.CATEGORY_APP_EMAIL)
        } else if (PackageUtil.isInstalled(context, PackageUtil.MAIL)) {
            resolveInfo = PackageUtil.getResolveInfo(context, PackageUtil.MAIL, Intent.CATEGORY_APP_EMAIL)
        }
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.type = INTENT_TYPE_TEXT
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        if (resolveInfo != null) {
            intent.component = ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
        } else {
            if (dialogTitle.isEmpty()) {
                dialogTitle = "Email: "
            }
            context.startActivity(Intent.createChooser(intent, dialogTitle))
        }
    }

    private fun isPackageOfMailApp(packageName: String): Boolean {
        return packageName.contains("com.google.android.gm") || packageName.contains("android.mail")
    }

    fun openMailApp(context: Context, dialogTitle: String, email: String, subject: String, text: String) {
        val intentMail = Intent(Intent.ACTION_SENDTO)
        intentMail.data = Uri.parse(email)
        intentMail.putExtra(Intent.EXTRA_SUBJECT, subject)
        intentMail.putExtra(Intent.EXTRA_TEXT, text)
        try {
            context.startActivity(Intent.createChooser(intentMail, dialogTitle))
        } catch (ex: ActivityNotFoundException) {
        }

    }

    fun shareAll(context: Context, dialogTitle: String, content: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, content)
        intent.type = INTENT_TYPE_TEXT
        context.startActivity(Intent.createChooser(intent, dialogTitle))
    }
}
