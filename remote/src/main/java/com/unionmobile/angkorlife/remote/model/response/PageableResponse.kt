package com.unionmobile.angkorlife.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageableResponse(
    @SerialName("offset") val offset: Int = 0,
    @SerialName("sort") val sort: SortResponse = SortResponse(),
    @SerialName("pageNumber") val pageNumber: Int = 0,
    @SerialName("pageSize") val pageSize: Int = 0,
    @SerialName("paged") val paged: Boolean = false,
    @SerialName("unpaged") val unPaged: Boolean = false,
)
