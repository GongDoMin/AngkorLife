package com.unionmobile.angkorlife.domain.usecase

import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(userId: String): Flow<Unit>
}