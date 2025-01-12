package com.unionmobile.angkorlife.domain.model

data class CandidateDetail(
    val id: Int = 0,
    val candidateNumber: Int = 0,
    val name: String = "",
    val country: String = "",
    val education: String = "",
    val major: String = "",
    val hobbies: String = "",
    val talent: String = "",
    val ambition: String = "",
    val contents: String = "",
    val profileInfoList: List<ProfileInfo> = emptyList(),
    val regDt: String = "",
    val voted: Boolean = false
)