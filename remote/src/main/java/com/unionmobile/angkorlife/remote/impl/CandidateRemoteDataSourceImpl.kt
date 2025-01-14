package com.unionmobile.angkorlife.remote.impl

import com.unionmobile.angkorlife.data.datasource.CandidateRemoteDataSource
import com.unionmobile.angkorlife.data.model.CandidateDetailEntity
import com.unionmobile.angkorlife.data.model.CandidateEntity
import com.unionmobile.angkorlife.exception.ExceptionType
import com.unionmobile.angkorlife.remote.model.request.VoteRequest
import com.unionmobile.angkorlife.remote.model.response.toEntity
import com.unionmobile.angkorlife.remote.service.AngkorLifeService
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class CandidateRemoteDataSourceImpl @Inject constructor(
    private val angkorLifeService: AngkorLifeService
): CandidateRemoteDataSource {
    override suspend fun getCandidates(page: Int, size: Int, sort: List<String>) : List<CandidateEntity> {
        return try {
            val response = angkorLifeService.getCandidates(page, size, sort)
            response.content.map { it.toEntity() }
        } catch (t: Throwable) {
            throw when (t) {
                is IOException -> ExceptionType.Network
                else -> ExceptionType.UnKnown
            }
        }
    }

    override suspend fun getCandidate(candidateId: Int, userId: String) : CandidateDetailEntity {
        return try {
            userId.validate()

            val response = angkorLifeService.getCandidate(candidateId, userId)
            val sortedProfiles =
                response.profileInfoList
                    .filter { it.fileArea == CANDIDATE_DETAIL_IMAGE }
                    .sortedBy { it.displayOrder }
            angkorLifeService.getCandidate(candidateId, userId).toEntity(sortedProfiles)
        } catch (t: Throwable) {
            throw when (t) {
                is HttpException -> {
                    when (t.code()) {
                        401 -> ExceptionType.UnAuthorized("Authorization error")
                        404 -> ExceptionType.NotFound("Candidate is note exist")
                        else -> ExceptionType.UnKnown
                    }
                }
                is IOException -> ExceptionType.Network
                else -> ExceptionType.UnKnown
            }
        }
    }

    override suspend fun getVotedCandidatesId(userId: String) : List<Int> {
        userId.validate()

        return angkorLifeService.getVotedCandidatesId(userId)
    }

    override suspend fun vote(candidateId: Int, userId: String) {
        return try {
            userId.validate()

            angkorLifeService.vote(VoteRequest(userId, candidateId))
        } catch (t: Throwable) {
            throw when (t) {
                is HttpException -> {
                    when (t.code()) {
                        400 -> ExceptionType.BadRequest("The maximum number of allowed votes is 3")
                        401 -> ExceptionType.UnAuthorized("Authorization error")
                        404 -> ExceptionType.NotFound("Invalid Request")
                        409 -> ExceptionType.Conflict("Already Voted")
                        else -> ExceptionType.UnKnown
                    }
                }
                is IOException -> ExceptionType.Network
                else -> ExceptionType.UnKnown
            }
        }
    }

    private fun String.validate() {
        if (isEmpty()) {
            throw HttpException(Response.error<Any>(401, "401 HTTP".toResponseBody()))
        }
    }
}

private const val CANDIDATE_LIST_IMAGE = 1
private const val CANDIDATE_DETAIL_IMAGE = 2
private const val VIDEO = 3
private const val TAG = "CandidateDataSourceImpl"