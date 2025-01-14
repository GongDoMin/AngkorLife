package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.data.datasource.UserInformationDataSource
import com.unionmobile.angkorlife.data.model.toModel
import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.model.CandidateDetail
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(
    private val candidateDataSource: CandidateDataSource,
    private val userInformationDataSource: UserInformationDataSource
): CandidateRepository {
    override fun getCandidates(page: Int, size: Int, sort: List<String>): Flow<List<Candidate>> =
        flow {
            emit(
                candidateDataSource.getCandidates(page, size, sort).map { it.toModel() }
            )
        }

    override fun getCandidate(candidateId: Int): Flow<CandidateDetail> =
        flow {
            val userId = userInformationDataSource.getUserId()
            emit(
                candidateDataSource.getCandidate(candidateId, userId).toModel()
            )
        }

    override suspend fun getVotedCandidatesId(): Flow<List<Int>> {
        val userId = userInformationDataSource.getUserId()
        try {
            val votedCandidates = candidateDataSource.getVotedCandidatesId(userId)
            votedCandidates.forEach {
                userInformationDataSource.updateVotedCandidate(it)
            }
        } catch (t: Throwable) {
            // 에러 처리 생략
            // 실패 시 ( IOException, userId is Empty 등 ) 빈 목록을 보여주기 위해서
        }

        return userInformationDataSource.getVotedCandidates()
    }

    override fun vote(candidateId: Int): Flow<Unit> =
        flow {
            val userId = userInformationDataSource.getUserId()
            candidateDataSource.vote(candidateId, userId)
            userInformationDataSource.updateVotedCandidate(candidateId)
            emit(Unit)
        }
}