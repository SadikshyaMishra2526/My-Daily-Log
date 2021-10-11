package com.mydailyjournal.unittest

import java.util.regex.Pattern

object  RegisterUtils {

    private val existUser = listOf("Sadikshya", "Karan")
    private val existPassword = listOf("123123", "123123")


//    Input will not be validate in following cases

    fun validateRegistrationUtils(
         fullName:String,
         email:String,
         password:String,
         age:String,
         registerTime:String,
    ):Boolean{
        if(fullName.isEmpty() || email.isEmpty()|| password.isEmpty()|| age.isEmpty()|| registerTime.isEmpty()) {
            return false
        }
        if(fullName in existUser) {
            return false
        }

        if(isValidEmailId(email)) {
            return false
        }

        if(password.count { it.isDigit() } < 2) {
            return false
        }
        return true
    }

    fun isValidEmailId(email: String?): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

}