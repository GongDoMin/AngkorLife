package com.unionmobile.angkorlife.remote.service

import com.unionmobile.angkorlife.remote.model.response.PageCandidateListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AngkorLifeService {
    @GET("vote/candidate/list")
    suspend fun getCandidates(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: List<String>,
        @Query("searchKeyword") searchKeyword: String = ""
    ) : PageCandidateListResponse
}