package com.unionmobile.angkorlife.data.model

import com.unionmobile.angkorlife.domain.model.Candidate

data class CandidateEntity(
    val id: Int = 0,
    val candidateNumber: Int = 0,
    val name: String = "",
    val profileUrl: String = "",
    val voteCnt: String = ""
)

fun CandidateEntity.toModel() =
    Candidate(
        id = id,
        candidateNumber = candidateNumber,
        name = name,
        profileUrl = profileUrl,
        voteCnt = voteCnt
    )