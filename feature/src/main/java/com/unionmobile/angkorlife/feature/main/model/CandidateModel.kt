package com.unionmobile.angkorlife.feature.main.model

import com.unionmobile.angkorlife.domain.model.Candidate

data class CandidateModel(
    val id: Int = 0,
    val candidateNumber: Int = 0,
    val name: String = "",
    val profileUrl: String = "",
    val voteCnt: String = ""
)

fun Candidate.toPresentation() =
    CandidateModel(
        id = id,
        candidateNumber = candidateNumber,
        name = name,
        profileUrl = profileUrl,
        voteCnt = voteCnt
    )