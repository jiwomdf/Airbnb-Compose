package com.programmergabut.airbnbcompose.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(f: String): String {
    val simpleDateFormat = SimpleDateFormat(f, Locale.getDefault())
    return simpleDateFormat.format(this)
}