package com.mydailyjournal.unittest

import org.junit.Test

import com.google.common.truth.Truth.assertThat
class AddDailyLogTest {

    @Test
    fun `empty title ,entrydate ,logdiscription , authorname returns false`() {
        val result = AddDailyLog.validateDailyLogUtils(
            "",
            "",
            "",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `image empty returns true`() {
        val result = AddDailyLog.validateDailyLogUtils(
            "My Day",
            "21/12/02 03:20:22",
            "",
            "the is the description of my day.Today I went to park", "SADIKSHYA"
        )
        assertThat(result).isFalse()
    }


    @Test
    fun `empty title returns false`() {
        val result = AddDailyLog.validateDailyLogUtils(
            "",
            "21/12/02 03:20:22",
            "njkdfbnjkbnjkdbndjfbndjfnbdjfn",
            "my dat",
            "Sadikshya"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than 10 digit log_description returns false`() {
        val result = AddDailyLog.validateDailyLogUtils(
            "My Day",
            "21/12/02 03:20:22",
            "",
            "my day",
            "Sadikshya"
        )
        assertThat(result).isFalse()
    }
}