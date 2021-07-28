package com.thinlineit.ctrlf.util

import java.util.regex.Pattern

fun String.isValid(expression: String): Boolean {
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}
