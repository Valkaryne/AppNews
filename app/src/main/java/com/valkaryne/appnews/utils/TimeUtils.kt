package com.valkaryne.appnews.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_PATTERN_PUBLISH = "dd MMM yyyy, hh:mm aaa"
const val EMPTY = ""

fun Timestamp.convertToString(pattern: String): String {
    return try {
        val format = SimpleDateFormat(pattern, Locale.US)
        format.format(Date(this.time))
    } catch (e: Exception) {
        EMPTY
    }
}

fun String.convertToTimestamp(pattern: String): Timestamp {
    return try {
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        val date = format.parse(this) as Date
        Timestamp(date.time)
    } catch (e: Exception) {
        Timestamp(System.currentTimeMillis())
    }
}