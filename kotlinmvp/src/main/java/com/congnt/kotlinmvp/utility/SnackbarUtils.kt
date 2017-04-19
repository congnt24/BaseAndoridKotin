package com.congnt.kotlinmvp.utility


import android.support.annotation.ColorInt
import android.support.design.widget.Snackbar
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import java.lang.ref.WeakReference

private var snackbarWeakReference: WeakReference<Snackbar>? = null


fun showShortSnackbar(parent: View, text: CharSequence, @ColorInt textColor: Int, @ColorInt bgColor: Int) {

    showSnackbar(parent, text, Snackbar.LENGTH_SHORT, textColor, bgColor, null, -1, null)

}

fun showShortSnackbar(parent: View, text: CharSequence, @ColorInt textColor: Int, @ColorInt bgColor: Int,

                      actionText: CharSequence, actionTextColor: Int, listener: View.OnClickListener) {

    showSnackbar(parent, text, Snackbar.LENGTH_SHORT, textColor, bgColor,

            actionText, actionTextColor, listener)

}


fun showLongSnackbar(parent: View, text: CharSequence, @ColorInt textColor: Int, @ColorInt bgColor: Int) {

    showSnackbar(parent, text, Snackbar.LENGTH_LONG, textColor, bgColor, null, -1, null)

}

fun showLongSnackbar(parent: View, text: CharSequence, @ColorInt textColor: Int, @ColorInt bgColor: Int,

                     actionText: CharSequence, actionTextColor: Int, listener: View.OnClickListener) {

    showSnackbar(parent, text, Snackbar.LENGTH_LONG, textColor, bgColor,

            actionText, actionTextColor, listener)

}

fun showIndefiniteSnackbar(parent: View, text: CharSequence, @ColorInt textColor: Int, @ColorInt bgColor: Int) {

    showSnackbar(parent, text, Snackbar.LENGTH_INDEFINITE, textColor, bgColor, null, -1, null)

}


fun showIndefiniteSnackbar(parent: View, text: CharSequence, @ColorInt textColor: Int, @ColorInt bgColor: Int,

                           actionText: CharSequence, actionTextColor: Int, listener: View.OnClickListener) {

    showSnackbar(parent, text, Snackbar.LENGTH_INDEFINITE, textColor, bgColor,

            actionText, actionTextColor, listener)

}


private fun showSnackbar(parent: View, text: CharSequence,

                         duration: Int,

                         @ColorInt textColor: Int, @ColorInt bgColor: Int,

                         actionText: CharSequence?, actionTextColor: Int,

                         listener: View.OnClickListener?) {

    val spannableString = SpannableString(text)

    val colorSpan = ForegroundColorSpan(textColor)

    spannableString.setSpan(colorSpan, 0, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    snackbarWeakReference = WeakReference<Snackbar>(Snackbar.make(parent, spannableString, duration))

    val snackbar = snackbarWeakReference!!.get()

    val view = snackbar!!.view

    view.setBackgroundColor(bgColor)

    if (actionText != null && actionText.length > 0 && listener != null) {

        snackbar.setActionTextColor(actionTextColor)

        snackbar.setAction(actionText, listener)

    }

    snackbar.show()

}

fun addView(layoutId: Int, index: Int) {

    val snackbar = snackbarWeakReference!!.get()

    if (snackbar != null) {

        val view = snackbar.view

        val layout = view as Snackbar.SnackbarLayout

        val child = LayoutInflater.from(view.getContext()).inflate(layoutId, null)

        val params = LinearLayout.LayoutParams(

                LinearLayout.LayoutParams.WRAP_CONTENT,

                LinearLayout.LayoutParams.WRAP_CONTENT)

        params.gravity = Gravity.CENTER_VERTICAL

        layout.addView(child, index, params)

    }

}


fun dismissSnackbar() {
    if (snackbarWeakReference != null && snackbarWeakReference!!.get() != null) {
        snackbarWeakReference!!.get()!!.dismiss()
        snackbarWeakReference = null
    }
}