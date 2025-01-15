package com.unionmobile.angkorlife.data.model

import com.unionmobile.angkorlife.domain.model.VotedCandidate

data class VotedCandidateEntity(
    val candidateId: Int = 0,
    val voteCnt: Int? = 0
)

fun VotedCandidate.toData() =
    VotedCandidateEntity(
        candidateId = candidateId,
        voteCnt = voteCnt
    )

fun VotedCandidateEntity.toDomain() =
    VotedCandidate(
        candidateId = candidateId,
        voteCnt = voteCnt
    )


