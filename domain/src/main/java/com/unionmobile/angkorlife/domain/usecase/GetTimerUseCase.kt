package com.unionmobile.angkorlife.domain.usecase

import com.unionmobile.angkorlife.domain.model.Timer
import kotlinx.coroutines.flow.Flow

interface GetTimerUseCase {
    operator fun invoke() : Flow<Timer>
}