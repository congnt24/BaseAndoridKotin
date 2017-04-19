package com.example.congn.kotlindemo

import com.congnt.kotlinmvp.mvp.AwesomeSharedPreferences

/**
 * Created by congn on 4/17/2017.
 */
object MyPrefs : AwesomeSharedPreferences() {
    var isFirst = object : SimpleSharedPreferences<Boolean>("is_first") {}
}