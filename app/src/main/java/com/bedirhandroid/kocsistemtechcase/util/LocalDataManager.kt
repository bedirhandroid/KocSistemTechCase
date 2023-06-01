package com.bedirhandroid.kocsistemtechcase.util

import android.content.Context
import android.content.SharedPreferences
import com.bedirhandroid.kocsistemtechcase.util.Constant.KEY_QUERY

class LocalDataManager(context: Context) {

    var localKey: String?
        set(value) {
            pref.edit().putString(KEY_QUERY, value).apply()
        }
        get() {
            return pref.getString(KEY_QUERY, null)
        }

    private val pref: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    companion object {
        private var instance: LocalDataManager? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): LocalDataManager {
            return instance!!
        }

        @JvmStatic
        fun init(context: Context) {
            if (instance == null) {
                instance = LocalDataManager(context)
            }
        }
    }
}