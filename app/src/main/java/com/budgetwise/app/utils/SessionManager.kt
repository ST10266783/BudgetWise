package com.budgetwise.app.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("budgetwise_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USER_ID  = "user_id"
        private const val KEY_USERNAME = "username"
        private const val NO_USER      = -1
    }

    fun saveSession(userId: Int, username: String) {
        prefs.edit()
            .putInt(KEY_USER_ID, userId)
            .putString(KEY_USERNAME, username)
            .apply()
    }

    fun getUserId(): Int = prefs.getInt(KEY_USER_ID, NO_USER)

    fun getUsername(): String = prefs.getString(KEY_USERNAME, "User") ?: "User"

    fun isLoggedIn(): Boolean = getUserId() != NO_USER

    fun clearSession() = prefs.edit().clear().apply()
}
