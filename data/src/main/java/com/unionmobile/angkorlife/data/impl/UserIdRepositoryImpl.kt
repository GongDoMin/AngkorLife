package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import javax.inject.Inject

class UserIdRepositoryImpl @Inject constructor() : UserIdRepository {
    private var userId: String = ""

    override fun getUserId(): String =
        userId

    override fun setUserId(userId: String) {
        this.userId = userId
    }
}