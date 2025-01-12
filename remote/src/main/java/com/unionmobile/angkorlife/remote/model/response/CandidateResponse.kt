package com.unionmobile.angkorlife.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateResponse(
    @SerialName("id") val id: Int = 0,
    @SerialName("candidateNumber") val candidateNumber: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("profileUrl") val profileUrl: String = "",
    @SerialName("voteCnt") val voteCnt: String = ""
)
