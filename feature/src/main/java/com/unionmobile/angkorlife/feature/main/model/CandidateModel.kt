package com.unionmobile.angkorlife.feature.main.model

import android.icu.text.DecimalFormat
import com.unionmobile.angkorlife.domain.model.Candidate

data class CandidateModel(
    val id: Int = 0,
    val candidateNumber: Int = 0,
    val name: String = "",
    val profileUrl: String = "",
    val voteCntInt: Int = 0,
    val voteCntString: String = "",
    val isVoted: Boolean = false
)

fun Candidate.toPresentation(
    isVoted: Boolean
) =
    CandidateModel(
        id = id,
        candidateNumber = candidateNumber,
        name = name,
        profileUrl = profileUrl,
        voteCntInt = voteCnt.toInt(),
        voteCntString = voteCnt.toInt().toFormattedString(),
        isVoted = isVoted
    )

fun Int.toFormattedString() : String {
    val decimalFormat = DecimalFormat("#,###")
    return "${decimalFormat.format(this)} voted"
}