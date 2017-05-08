package com.example.congn.kotlindemo

import com.congnt.kotlinmvp.mvp.RxSharedPreferences
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

/**
 * Created by congn on 4/17/2017.
 */
object MyPrefs : RxSharedPreferences() {
    var isFirst = object : SimpleSharedPreferences<Boolean>("is_first") {}
    var test = object : SimpleSharedPreferences<User>("user") {
        override val type: Type
            get() = object : TypeToken<User>() {}.type
    }
}

data class User(var name: String) : Serializable {
}