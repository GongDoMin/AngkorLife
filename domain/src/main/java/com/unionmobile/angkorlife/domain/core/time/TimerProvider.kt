package com.unionmobile.angkorlife.domain.core.time

import com.unionmobile.angkorlife.domain.model.Timer
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class TimerProvider(
    private val targetZoneId: ZoneId = ZoneId.of(TARGET_ZONE_ID),
    private val targetDateTime: ZonedDateTime =
        ZonedDateTime.of(
            TARGET_YEAR, TARGET_MONTH, TARGET_DAY_OF_MONTH, TARGET_HOUR, TARGET_MINUTE, TARGET_SECOND, TARGET_NANO_OF_SECOND, targetZoneId
        )
) {
    fun getRemainingSeconds() : Timer {
        val now = ZonedDateTime.now(targetZoneId)
        val remainingSeconds = now.until(targetDateTime, ChronoUnit.SECONDS).coerceAtLeast(0)

        return remainingSeconds.remainingSecondsToTimer()
    }

    private fun Long.remainingSecondsToTimer() : Timer {
        val days = this / SECONDS_IN_DAY
        val hours = (this % SECONDS_IN_DAY) / (SECONDS_IN_HOUR)
        val minutes = (this % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE
        val seconds = this % SECONDS_IN_MINUTE

        return Timer(
            days = days.toInt(),
            hours = hours.toInt(),
            minutes = minutes.toInt(),
            seconds = seconds.toInt()
        )
    }
}

private const val TARGET_YEAR = 2025
private const val TARGET_MONTH = 2
private const val TARGET_DAY_OF_MONTH = 3
private const val TARGET_HOUR = 0
private const val TARGET_MINUTE = 0
private const val TARGET_SECOND = 0
private const val TARGET_NANO_OF_SECOND = 0
private const val TARGET_ZONE_ID = "Asia/Seoul"

private const val SECONDS_IN_MINUTE = 60
private const val SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE
private const val SECONDS_IN_DAY = 24 * SECONDS_IN_HOUR