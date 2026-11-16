package com.practicum.playlistmaker.core.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun formatToMinutesAndSeconds(timeMillis: Long): String {
    return SimpleDateFormat("mm:ss", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.format(timeMillis)
}