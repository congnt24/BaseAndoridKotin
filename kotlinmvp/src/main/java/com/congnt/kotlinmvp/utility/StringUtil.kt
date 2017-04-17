package com.congnt.kotlinmvp.utility

import android.text.TextUtils

/**
 * Created by congn on 4/12/2017.
 */

fun isExistNull(strs: Array<String>): Boolean{
    return strs.any { TextUtils.isEmpty(it) }
}
