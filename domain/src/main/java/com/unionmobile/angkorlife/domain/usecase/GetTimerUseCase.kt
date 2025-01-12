package com.unionmobile.angkorlife.domain.usecase

import com.unionmobile.angkorlife.domain.core.time.TimerProvider
import com.unionmobile.angkorlife.domain.model.Timer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTimerUseCase(
    private val timerProvider: TimerProvider
) {
    operator fun invoke() : Flow<Timer> =
        flow {
            while (true) {
                val timer = timerProvider.getRemainingSeconds()

                emit(timer)

                if (timer.isZero()) {
                    break
                }

                delay(1000L)
            }
        }
}