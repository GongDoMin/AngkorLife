package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.data.datasource.UserInformationDataSource
import com.unionmobile.angkorlife.domain.repository.UserIdRepository
import javax.inject.Inject

class UserIdRepositoryImpl @Inject constructor(
    private val userInformationDataSource: UserInformationDataSource
) : UserIdRepository {
    override fun setUserId(userId: String) {
        userInformationDataSource.setUserId(userId)
    }
}