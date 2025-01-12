package com.unionmobile.angkorlife.domain.core.time

import com.unionmobile.angkorlife.domain.model.Timer

interface TimerProvider {
    fun getRemainingSeconds() : Timer
}