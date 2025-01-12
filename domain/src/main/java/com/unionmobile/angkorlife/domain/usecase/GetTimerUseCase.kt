package com.unionmobile.angkorlife.domain.usecase

import com.unionmobile.angkorlife.domain.core.time.TimeProvider
import com.unionmobile.angkorlife.domain.model.Timer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTimerUseCase(
    private val timeProvider: TimeProvider
) {
    operator fun invoke() : Flow<Timer> =
        flow {
            while (true) {
                val remainingSeconds = timeProvider.getRemainingSeconds()

                if (remainingSeconds == 0L) {
                    emit(Timer())
                    break
                }

                val timer = remainingSeconds.remainingSecondsToTimer()
                emit(timer)
                delay(1000L)
            }
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

private const val SECONDS_IN_MINUTE = 60
private const val SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE
private const val SECONDS_IN_DAY = 24 * SECONDS_IN_HOUR