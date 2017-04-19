package com.congnt.kotlinmvp.utility

import android.content.Context
import android.support.v7.app.AlertDialog


/**
 * Created by congn on 4/19/2017.
 */

interface OnDialogListener {
    fun onPositive() {}
    fun onNegative() {}
}

fun baseAlertDialog(context: Context, title: String, message: String, theme: Int = android.R.style.Theme_Material_Dialog_Alert): AlertDialog.Builder {
    return AlertDialog.Builder(context, theme).setTitle(title)
            .setMessage(message)
}

fun messageDialog(context: Context, title: String, message: String, theme: Int = android.R.style.Theme_Material_Dialog_Alert, listener: OnDialogListener? = null): AlertDialog.Builder {
    return baseAlertDialog(context, title, message, theme)
            .setPositiveButton(android.R.string.ok, { dialog, _ -> dialog.dismiss(); listener?.onPositive() })
}

fun confirmDialog(context: Context, title: String, message: String, theme: Int = android.R.style.Theme_Material_Dialog_Alert, listener: OnDialogListener? = null): AlertDialog.Builder {
    return baseAlertDialog(context, title, message, theme)
            .setPositiveButton(android.R.string.ok, { dialog, _ -> dialog.dismiss() })
            .setNegativeButton(android.R.string.cancel, { dialog, _ -> dialog.dismiss(); listener?.onNegative() })
}

fun yesNoDialog(context: Context, title: String, message: String, theme: Int = android.R.style.Theme_Material_Dialog_Alert, listener: OnDialogListener? = null): AlertDialog.Builder {
    return baseAlertDialog(context, title, message, theme)
            .setPositiveButton(android.R.string.yes, { dialog, _ -> dialog.dismiss() })
            .setNegativeButton(android.R.string.no, { dialog, _ -> dialog.dismiss(); listener?.onNegative() })
}