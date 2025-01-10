package com.unionmobile.angkorlife.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase {
    fun invoke(id: String): Flow<Unit> =
        flow {
            if (id.isValidRange()) {
                throw IllegalArgumentException("id must be between 1 and 16")
            }
            emit(Unit)
        }


    private fun String.isValidRange() : Boolean {
        return this.length in 1..16
    }
}