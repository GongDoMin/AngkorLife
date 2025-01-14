package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import com.unionmobile.angkorlife.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val userIdRepository: UserIdRepository
) : LoginUseCase {
    override operator fun invoke(userId: String): Flow<Unit> =
        flow {
            if (!userId.isValidRange()) {
                throw IllegalStateException("ID must be at least 1 and no more than 16")
            }

            userIdRepository.setUserId(userId)

            emit(Unit)
        }


    private fun String.isValidRange() : Boolean {
        return this.length in 1..16
    }
}