package com.unionmobile.angkorlife.feature.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable data object LOGIN : Routes

    @Serializable data object MAIN : Routes

    @Serializable data class DETAIL(
        val candidateId: Int
    ) : Routes {
        companion object {
            const val CANDIDATE_ID = "candidateId"
        }
    }
}