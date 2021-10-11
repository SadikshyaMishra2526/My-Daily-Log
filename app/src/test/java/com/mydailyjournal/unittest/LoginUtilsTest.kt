package com.mydailyjournal.unittest

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Test

class LoginUtilsTest {
    @Test
    fun `empty fullname and password returns false`() {
        val result = LoginUtils.validateLoginUtils(
            "",
            "",

        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `invalid username  returns falsa`() {
        val result = LoginUtils.validateLoginUtils(
            "000",
            "123123",

            )
        Truth.assertThat(result).isFalse()
    }


    @Test
    fun `username and password mismatch returns false`() {
        val result = LoginUtils.validateLoginUtils(
            "000",
            "123123",

            )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `less than 6 digit password returns false`() {
        val result = LoginUtils.validateLoginUtils(
            "000",
            "1231",
            )
        Truth.assertThat(result).isFalse()

    }
}
