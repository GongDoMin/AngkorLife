package com.unionmobile.angkorlife.domain.model

data class Timer(
    val days: Int = 0,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0
) {
    fun isZero() : Boolean =
        days == 0 && hours == 0 && minutes == 0 && seconds == 0
}
