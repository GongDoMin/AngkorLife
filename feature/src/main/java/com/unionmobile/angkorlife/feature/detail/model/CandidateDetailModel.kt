package com.unionmobile.angkorlife.feature.detail.model

import com.unionmobile.angkorlife.domain.model.CandidateDetail

data class CandidateDetailModel(
    val id: Int = 0,
    val candidateNumber: Int = 0,
    val name: String = "",
    val education: String = "",
    val major: String = "",
    val hobbies: String = "",
    val talent: String = "",
    val ambition: String = "",
    val profileInfoList: List<ProfileInfoModel> = emptyList(),
    val voted: Boolean = false
)

fun CandidateDetail.toPresentation() =
    CandidateDetailModel(
        id = id,
        candidateNumber = candidateNumber,
        name = name,
        education = education,
        major = major,
        hobbies = hobbies,
        talent = talent,
        ambition = ambition,
        profileInfoList = profileInfoList.map { it.toPresentation() },
        voted = voted
    )