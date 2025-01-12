package com.unionmobile.angkorlife.feature.detail.model

import com.unionmobile.angkorlife.domain.model.CandidateDetail

data class CandidateDetailModel(
    val id: Int = 0,
    val candidateNumber: String = "",
    val name: String = "",
    val education: String = "",
    val major: String = "",
    val hobby: String = "",
    val talent: String = "",
    val ambition: String = "",
    val profileInfoList: List<ProfileInfoModel> = emptyList(),
    val voted: Boolean = false
)

fun CandidateDetail.toPresentation() =
    CandidateDetailModel(
        id = id,
        candidateNumber = "Entry No.$candidateNumber",
        name = name,
        education = education,
        major = major,
        hobby = hobby,
        talent = talent,
        ambition = ambition,
        profileInfoList = profileInfoList.map { it.toPresentation() },
        voted = voted
    )