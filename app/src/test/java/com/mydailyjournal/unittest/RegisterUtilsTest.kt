package com.mydailyjournal.unittest

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Test

class RegisterUtilsTest{

@Test
fun `empty title ,entrydate ,logdiscription , authorname returns false`() {
    val result = AddDailyLog.validateDailyLogUtils(
        "",
        "",
        "",
        "",
        ""
    )
    Truth.assertThat(result).isFalse()
}

@Test
fun `image empty returns true`() {
    val result = AddDailyLog.validateDailyLogUtils(
        "",
        "123",
        "123",
        "", ""
    )
    Truth.assertThat(result).isFalse()
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
    Truth.assertThat(result).isFalse()
}

@Test
fun `less than 10 digit log_description returns false`() {
    val result = AddDailyLog.validateDailyLogUtils(
        "My Day",
        "21/12/02 03:20:22",
        "",
        "my dat",
        "Sadikshya"
    )
    Truth.assertThat(result).isFalse()
}
}