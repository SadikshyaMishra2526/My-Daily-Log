package com.mydailyjournal.unittest
object AddDailyLog {

    fun validateDailyLogUtils(
        logTitle:String,
        logEntryDate:String,
        logImage:String,
        logDescription:String,
        logWrittenBy:String,
//        existLogger:ArrayList<String>
    ):Boolean{
        if(logTitle.isEmpty() || logEntryDate.isEmpty()|| logImage.isEmpty()|| logDescription.isEmpty()|| logWrittenBy.isEmpty()) {
            return false
        }

//        if(logWrittenBy in existLogger) {
//         return  true
//        }

        if(logDescription.count { it.isDigit() } < 10) {
            return false
        }
        return true
    }


}