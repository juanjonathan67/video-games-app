package com.rawg.games.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.toFormattedDateString(
    outputFormat: String = "dd MMMM yyyy",
    locale: Locale = Locale.getDefault()
): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date: Date = inputFormat.parse(this) ?: return this

    val calendar = Calendar.getInstance()
    calendar.time = date

    val formatter = SimpleDateFormat(outputFormat, locale)
    return formatter.format(calendar.time)
}
