package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.data.datasource.UserInformationLocalDataSource
import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import javax.inject.Inject

class UserIdRepositoryImpl @Inject constructor(
    private val userInformationLocalDataSource: UserInformationLocalDataSource
) : UserIdRepository {
    override fun setUserId(userId: String) {
        userInformationLocalDataSource.setUserId(userId)
    }
}