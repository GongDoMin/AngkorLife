package com.unionmobile.angkorlife.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SortResponse(
    @SerialName("empty") val empty: Boolean = false,
    @SerialName("sorted") val sorted: Boolean = false,
    @SerialName("unsorted") val unsorted: Boolean = false
)
