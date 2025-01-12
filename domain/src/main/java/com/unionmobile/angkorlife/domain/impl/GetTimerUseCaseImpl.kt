package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.core.time.TimerProvider
import com.unionmobile.angkorlife.domain.model.Timer
import com.unionmobile.angkorlife.domain.usecase.GetTimerUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTimerUseCaseImpl @Inject constructor(
    private val timerProvider: TimerProvider
) : GetTimerUseCase {
    override operator fun invoke() : Flow<Timer> =
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