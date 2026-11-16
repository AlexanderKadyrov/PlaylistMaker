package com.practicum.playlistmaker.core.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun formatToMinutesAndSeconds(timeMillis: Long): String {
    return SimpleDateFormat("mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.format(timeMillis)
}

fun formatToYear(isoDate: String?): String? {
    if (isoDate.isNullOrBlank()) {
        return null
    }
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val date = inputFormat.parse(isoDate)
        date?.let { outputFormat.format(it) }
    } catch (e: Exception) {
        null
    }
}