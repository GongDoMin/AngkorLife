package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.data.datasource.CandidateRemoteDataSource
import com.unionmobile.angkorlife.data.datasource.UserInformationLocalDataSource
import com.unionmobile.angkorlife.data.model.VotedCandidateEntity
import com.unionmobile.angkorlife.data.model.toData
import com.unionmobile.angkorlife.data.model.toDomain
import com.unionmobile.angkorlife.data.model.toModel
import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.model.CandidateDetail
import com.unionmobile.angkorlife.domain.model.VotedCandidate
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import com.unionmobile.angkorlife.exception.ExceptionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.io.IOException
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(
    private val candidateRemoteDataSource: CandidateRemoteDataSource,
    private val userInformationLocalDataSource: UserInformationLocalDataSource
): CandidateRepository {
    override fun getCandidates(page: Int, size: Int, sort: List<String>): Flow<List<Candidate>> =
        flow {
            emit(
                candidateRemoteDataSource.getCandidates(page, size, sort).map { it.toModel() }
            )
        }

    override fun getCandidate(candidateId: Int): Flow<CandidateDetail> =
        flow {
            val userId = userInformationLocalDataSource.getUserId()
            emit(
                candidateRemoteDataSource.getCandidate(candidateId, userId).toModel()
            )
        }

    override suspend fun getVotedCandidatesId(): Flow<List<VotedCandidate>> {
        return userInformationLocalDataSource.getVotedCandidates().onStart {
            val userId = userInformationLocalDataSource.getUserId()

            val votedCandidates = candidateRemoteDataSource.getVotedCandidatesId(userId)
            votedCandidates.forEach { candidateId ->
                userInformationLocalDataSource.updateVotedCandidate(
                    VotedCandidateEntity(
                        candidateId = candidateId,
                        voteCnt = null
                    )
                )
            }
        }.map { it.map { it.toDomain() } }
    }

    override fun vote(votedCandidate: VotedCandidate): Flow<Unit> =
        flow {
            val userId = userInformationLocalDataSource.getUserId()
            candidateRemoteDataSource.vote(votedCandidate.candidateId, userId)
            userInformationLocalDataSource.updateVotedCandidate(
                votedCandidate.toData()
            )
            emit(Unit)
        }
}