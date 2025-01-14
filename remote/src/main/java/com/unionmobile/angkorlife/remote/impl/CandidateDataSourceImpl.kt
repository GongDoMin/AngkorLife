package com.unionmobile.angkorlife.remote.impl

import android.util.Log
import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.data.model.CandidateDetailEntity
import com.unionmobile.angkorlife.data.model.CandidateEntity
import com.unionmobile.angkorlife.exception.ExceptionType
import com.unionmobile.angkorlife.remote.model.request.VoteRequest
import com.unionmobile.angkorlife.remote.model.response.toEntity
import com.unionmobile.angkorlife.remote.service.AngkorLifeService
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CandidateDataSourceImpl @Inject constructor(
    private val angkorLifeService: AngkorLifeService
): CandidateDataSource {
    override suspend fun getCandidates(page: Int, size: Int, sort: List<String>) : List<CandidateEntity> {
        return try {
            val response = angkorLifeService.getCandidates(page, size, sort)
            response.content.map { it.toEntity() }
        } catch (t: Throwable) {
            throw t.mapToAngkorLifeError()
        }
    }

    override suspend fun getCandidate(candidateId: Int, userId: String) : CandidateDetailEntity {
        return try {
            val response = angkorLifeService.getCandidate(candidateId, userId)
            val sortedProfiles =
                response.profileInfoList
                    .filter { it.fileArea == CANDIDATE_DETAIL_IMAGE }
                    .sortedBy { it.displayOrder }
            angkorLifeService.getCandidate(candidateId, userId).toEntity(sortedProfiles)
        } catch (t: Throwable) {
            throw t.mapToAngkorLifeError()
        }
    }

    override suspend fun getVotedCandidatesId(userId: String) : List<Int> {
        return try {
            angkorLifeService.getVotedCandidatesId(userId)
        } catch (t: Throwable) {
            throw t.mapToAngkorLifeError()
        }
    }

    override suspend fun vote(candidateId: Int, userId: String) {
        return try {
            angkorLifeService.vote(VoteRequest(userId, candidateId))
        } catch (t: Throwable) {
            throw t.mapToAngkorLifeError()
        }
    }

    private fun Throwable.mapToAngkorLifeError() : ExceptionType {
        Log.e(TAG, message?: "error message is empty")
        return when (this) {
            is HttpException -> {
                when (code()) {
                    400 -> ExceptionType.BadRequest("잘못된 요청입니다.")
                    401 -> ExceptionType.UnAuthorized("권한 에러")
                    404 -> ExceptionType.NotFound("잘못된 요청입니다.")
                    409 -> ExceptionType.Conflict("이미 완료된 처리입니다.")
                    else -> ExceptionType.UnKnown
                }
            }
            is IOException -> ExceptionType.Network("연결 실패")
            else -> ExceptionType.UnKnown
        }
    }
}

private const val CANDIDATE_LIST_IMAGE = 1
private const val CANDIDATE_DETAIL_IMAGE = 2
private const val VIDEO = 3
private const val TAG = "CandidateDataSourceImpl"