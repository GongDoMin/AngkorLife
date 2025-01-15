package com.unionmobile.angkorlife.local.model

import com.unionmobile.angkorlife.data.model.VotedCandidateEntity

data class VotedCandidateLocal(
    val candidateId: Int = 0,
    val voteCount: Int = 0
)

fun VotedCandidateEntity.toLocal() =
    VotedCandidateLocal(
        candidateId = candidateId,
        voteCount = voteCount
    )

fun VotedCandidateLocal.toEntity() =
    VotedCandidateEntity(
        candidateId = candidateId,
        voteCount = voteCount
    )