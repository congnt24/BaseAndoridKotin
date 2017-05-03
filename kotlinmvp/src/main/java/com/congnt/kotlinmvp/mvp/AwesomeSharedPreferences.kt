package com.congnt.kotlinmvp.mvp

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Consumer
import java.lang.reflect.Type
import java.util.*


/**
 * Extend this class and
 * Create new instance of inner class to save a object/ collection to shared preferences
 * Created by NGUYEN TRUNG CONG on 09/13/2016
 */
abstract class AwesomeSharedPreferences {
    companion object FACTORY {
        var context: Context? = null
        fun initialize(ctx: Context) {
            context = ctx
        }
    }

    fun initialize(ctx: Context) {
        context = ctx
    }

    val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    protected val editor: SharedPreferences.Editor
    var keyChanges: Observable<String>? = null

    init {
        editor = pref.edit()
        keyChanges = Observable.create(ObservableOnSubscribe<String> {
            var listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key -> it.onNext(key) }
            it.setCancellable { pref.unregisterOnSharedPreferenceChangeListener(listener) }
            pref.registerOnSharedPreferenceChangeListener(listener)
        }).share()
    }

    abstract inner class ListSharedPreferences<V>(override var id: String) : SimpleSharedPreferences<MutableList<V>>(id) {
        fun add(value: V) {
            val list = load(ArrayList<V>())
            list.add(value)
            save(list)
        }

        fun insert(pos: Int, value: V) {
            val list = load(ArrayList<V>())
            list.add(pos, value)
            save(list)
        }

        operator fun get(i: Int): V? {
            try {
                return load(ArrayList<V>())[i]
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

        }

        fun has(v: V): Boolean {
            return load(ArrayList<V>()).contains(v)
        }
    }

    abstract inner class MapSharedPreferences<K, V>(override var id: String) : SimpleSharedPreferences<MutableMap<K, V>>(id) {
        fun put(key: K, value: V) {
            val map = load(HashMap<K, V>())
            map.put(key, value)
            save(map)
        }

        /**
         * @param key
         * *
         * @return nul if no have
         */
        operator fun get(key: K): V? {
            val map = load(HashMap<K, V>())
            return if (has(key)) map[key] else null
        }

        fun has(key: K): Boolean {
            return load(HashMap<K, V>()).containsKey(key)
        }
    }

    abstract inner class SimpleSharedPreferences<T>(open var id: String) {
        init {
            if (context == null) {
                throw Throwable("Please call initilize(context: Context) in your Application")
            }
        }

        /**
         * If object you're using isn't a primary type, override this method by
         * return new TypeToken<T>(){}.getType()

         * @return TypeToken
        </T> */
        protected open val type: Type
            get() = object : TypeToken<T>() {
            }.type

        fun save(t: T) {
            editor.putString(id, Gson().toJson(t, type))
            editor.commit()
        }

        fun load(defaultT: T? = null): T {
            val str = pref.getString(id, "")
            return if (str == "") defaultT!! else Gson().fromJson(str, type)
        }

        fun remove() {
            editor.remove(id)
        }

        fun asObservable(): Observable<T> {
            return keyChanges!!.filter { id.equals(it/*changed key*/) }
                    .startWith("<init>") // Dummy value to trigger initial load.
                    .map({ load() })
        }

        fun asConsumer(): Consumer<T> {
            return Consumer { save(it) }
        }
    }

}
