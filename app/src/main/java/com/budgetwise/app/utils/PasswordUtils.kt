package com.budgetwise.app.utils

import java.security.MessageDigest

fun hashPassword(password: String): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}

fun isPasswordValid(password: String): Boolean {
    if (password.length < 8) return false
    if (password.none { it.isUpperCase() }) return false
    if (password.none { it.isDigit() }) return false
    return true
}
