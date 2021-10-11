package com.mydailyjournal.unittest

import java.util.regex.Pattern

object LoginUtils {
    private val existUser = listOf("Sadikshya", "Karan")
    private val existPassword = listOf("123123", "123123")


    fun validateLoginUtils(
        fullName: String,
        password: String
    ): Boolean {
        if (fullName.isEmpty() || password.isEmpty()) {
            return false
        }

        if (password.count { it.isDigit() } < 2) {
            return false
        }

        if (fullName.equals(existUser) && password.equals(existPassword)) {
            return false
        }
        return true
    }
}