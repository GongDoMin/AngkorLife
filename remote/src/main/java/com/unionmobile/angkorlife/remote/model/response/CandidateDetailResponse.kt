package com.unionmobile.angkorlife.remote.model.response

import com.unionmobile.angkorlife.data.model.CandidateDetailEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CandidateDetailResponse(
    @SerialName("id") val id: Int = 0,
    @SerialName("candidateNumber") val candidateNumber: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("country") val country: String = "",
    @SerialName("education") val education: String = "",
    @SerialName("major") val major: String = "",
    @SerialName("hobby") val hobby: String = "",
    @SerialName("talent") val talent: String = "",
    @SerialName("ambition") val ambition: String = "",
    @SerialName("contents") val contents: String = "",
    @SerialName("profileInfoList") val profileInfoList: List<ProfileInfoResponse> = emptyList(),
    @SerialName("regDt") val regDt: String = "",
    @SerialName("voted") val voted: Boolean = false
)

fun CandidateDetailResponse.toEntity(
    sortedProfileInfoList: List<ProfileInfoResponse>
) =
    CandidateDetailEntity(
        id = id,
        candidateNumber = candidateNumber,
        name = name,
        country = country,
        education = education,
        major = major,
        hobby = hobby,
        talent = talent,
        ambition = ambition,
        contents = contents,
        profileInfoList = sortedProfileInfoList.map { it.toEntity() },
        regDt = regDt,
        voted = voted
    )
