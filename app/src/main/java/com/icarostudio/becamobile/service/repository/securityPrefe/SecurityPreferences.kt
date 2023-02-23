package com.icarostudio.becamobile.service.repository.securityPrefe

import android.content.Context
import android.content.SharedPreferences

/**
 * Acesso a dados rápidos do projeto - SharedPreferences
 */
class SecurityPreferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("filmShare", Context.MODE_PRIVATE)


    fun store(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun get(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

}