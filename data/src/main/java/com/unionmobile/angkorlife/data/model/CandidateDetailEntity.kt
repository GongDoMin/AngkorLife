package com.unionmobile.angkorlife.data.model

import com.unionmobile.angkorlife.domain.model.CandidateDetail

data class CandidateDetailEntity(
    val id: Int = 0,
    val candidateNumber: Int = 0,
    val name: String = "",
    val country: String = "",
    val education: String = "",
    val major: String = "",
    val hobby: String = "",
    val talent: String = "",
    val ambition: String = "",
    val contents: String = "",
    val profileInfoList: List<ProfileInfoEntity> = emptyList(),
    val regDt: String = "",
    val voted: Boolean = false
)

fun CandidateDetailEntity.toModel() =
    CandidateDetail(
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
        profileInfoList = profileInfoList.map { it.toModel() },
        regDt = regDt,
        voted = voted
    )