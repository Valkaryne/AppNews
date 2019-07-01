package com.valkaryne.appnews.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_PATTERN_PUBLISH = "dd MMM yyyy, hh:mm aaa"
const val EMPTY = ""

/**
 * Extension method converts [Timestamp] to [String]
 *
 * @param pattern is the format of date
 * @return [String] representation of [Timestamp]
 */
fun Timestamp.convertToString(pattern: String): String {
    return try {
        val format = SimpleDateFormat(pattern, Locale.US)
        format.format(Date(this.time))
    } catch (e: Exception) {
        EMPTY
    }
}

/**
 * Extension method converts [String] to [Timestamp]
 *
 * @param pattern is the format of date
 * @return [Timestamp] values of [String] representation
 */
fun String.convertToTimestamp(pattern: String): Timestamp {
    return try {
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        val date = format.parse(this) as Date
        Timestamp(date.time)
    } catch (e: Exception) {
        Timestamp(System.currentTimeMillis())
    }
}