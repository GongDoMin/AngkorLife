package com.unionmobile.angkorlife.domain.repository

interface UserIdRepository {
    fun getUserId() : String

    fun setUserId(userId: String)
}