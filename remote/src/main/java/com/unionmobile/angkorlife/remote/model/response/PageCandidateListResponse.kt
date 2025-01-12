package com.unionmobile.angkorlife.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageCandidateListResponse(
    @SerialName("totalPages") val totalPages: Int = 0,
    @SerialName("totalElements") val totalElements: Int = 0,
    @SerialName("size") val size: Int = 0,
    @SerialName("content") val content: List<CandidateResponse> = emptyList(),
    @SerialName("number") val number: Int = 0,
    @SerialName("sort") val sort: SortResponse = SortResponse(),
    @SerialName("pageable") val pageable: PageableResponse = PageableResponse(),
    @SerialName("first") val first: Boolean = false,
    @SerialName("last") val last: Boolean = false,
    @SerialName("numberOfElements") val numberOfElements: Int = 0,
    @SerialName("empty") val empty: Boolean = false
)
