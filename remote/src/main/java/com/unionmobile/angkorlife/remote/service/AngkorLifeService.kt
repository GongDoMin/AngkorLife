package com.unionmobile.angkorlife.remote.service

import com.unionmobile.angkorlife.remote.model.request.VoteRequest
import com.unionmobile.angkorlife.remote.model.response.CandidateDetailResponse
import com.unionmobile.angkorlife.remote.model.response.PageCandidateListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AngkorLifeService {
    @GET("vote/candidate/list")
    suspend fun getCandidates(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: List<String>,
        @Query("searchKeyword") searchKeyword: String = ""
    ) : PageCandidateListResponse

    @GET("vote/candidate/{id}")
    suspend fun getCandidate(
        @Path("id") id: Int,
        @Query("userId") userId: String,
    ) : CandidateDetailResponse

    @GET("vote/voted/candidate/list")
    suspend fun getVotedCandidatesId(
        @Query("userId") userId: String
    ) : List<Int>

    @POST("vote")
    suspend fun vote(
        @Body voteRequest: VoteRequest
    )
}