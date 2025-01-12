package com.unionmobile.angkorlife.domain.core.time

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class TimeProvider(
    private val targetZoneId: ZoneId = ZoneId.of(TARGET_ZONE_ID),
    private val targetDateTime: ZonedDateTime =
        ZonedDateTime.of(
            TARGET_YEAR, TARGET_MONTH, TARGET_DAY_OF_MONTH, TARGET_HOUR, TARGET_MINUTE, TARGET_SECOND, TARGET_NANO_OF_SECOND, targetZoneId
        )
) {
    fun getRemainingSeconds() : Long {
        val now = ZonedDateTime.now(targetZoneId)
        val remainingSeconds = now.until(targetDateTime, ChronoUnit.SECONDS)

        return remainingSeconds.coerceAtLeast(0)
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
