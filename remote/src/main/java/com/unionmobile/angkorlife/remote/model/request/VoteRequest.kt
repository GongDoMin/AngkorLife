package com.unionmobile.angkorlife.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoteRequest(
    @SerialName("userId") val userId: String,
    @SerialName("id") val id: Int
)
