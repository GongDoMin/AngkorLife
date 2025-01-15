package com.unionmobile.angkorlife.local.model

import com.unionmobile.angkorlife.data.model.VotedCandidateEntity

data class VotedCandidateLocal(
    val candidateId: Int = 0,
    val voteCnt: Int? = 0
)

fun VotedCandidateEntity.toLocal() =
    VotedCandidateLocal(
        candidateId = candidateId,
        voteCnt = voteCnt
    )

fun VotedCandidateLocal.toEntity() =
    VotedCandidateEntity(
        candidateId = candidateId,
        voteCnt = voteCnt
    )