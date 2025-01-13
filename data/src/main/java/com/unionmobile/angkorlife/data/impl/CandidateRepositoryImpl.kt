package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.data.datasource.UserInformationDataSource
import com.unionmobile.angkorlife.data.model.toModel
import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.model.CandidateDetail
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(
    private val candidateDataSource: CandidateDataSource,
    private val userInformationDataSource: UserInformationDataSource
): CandidateRepository {
    override fun getCandidates(page: Int, size: Int, sort: List<String>): Flow<List<Candidate>> =
        candidateDataSource.getCandidates(page, size, sort).map { it.map { it.toModel() } }

    override fun getCandidate(candidateId: Int): Flow<CandidateDetail> {
        val userId = userInformationDataSource.getUserId()
        return candidateDataSource.getCandidate(candidateId, userId).map { it.toModel() }
    }

    override suspend fun getVotedCandidatesId(): Flow<List<Int>> {
        val userId = userInformationDataSource.getUserId()
        candidateDataSource.getVotedCandidatesId(userId)
            .catch {
                // 에러 처리 생략
                // 실패 시 ( IOException, userId is Empty 등 ) 빈 목록을 보여주기 위해서
            }
            .collect { votedCandidates ->
                votedCandidates.forEach {
                    userInformationDataSource.updateVotedCandidate(it)
                }
            }
        return userInformationDataSource.getVotedCandidates()
    }


    override fun vote(candidateId: Int): Flow<Unit> {
        val userId = userInformationDataSource.getUserId()
        return candidateDataSource.vote(userId, candidateId)
            .onEach {
                userInformationDataSource.updateVotedCandidate(candidateId)
            }
    }
}