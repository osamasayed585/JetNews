package com.droidos.common.utils

import android.content.Context
import android.os.Build
import com.droidos.common.R
import java.time.Duration
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun formatTimeAgo(
    date: String,
    context: Context,
): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val dateTime = ZonedDateTime.parse(date, formatter)
        val currentTime = ZonedDateTime.now()
        val duration = Duration.between(dateTime, currentTime)

        val hoursAgo = duration.toHours()
        val minutesAgo = duration.toMinutes()
        val secondsAgo = duration.seconds

        when {
            secondsAgo < 60 -> context.getString(R.string.just_now)
            minutesAgo < 60 -> context.getString(R.string.minutes_ago, minutesAgo)
            hoursAgo < 24 -> context.resources.getQuantityString(
                R.plurals.hours,
                hoursAgo.toInt(),
                hoursAgo
            )

            else -> {
                context.resources.getQuantityString(
                    R.plurals.days,
                    duration.toDays().toInt(),
                    duration.toDays()
                )
            }
        }
    } else date
}