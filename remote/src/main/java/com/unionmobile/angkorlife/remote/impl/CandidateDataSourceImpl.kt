package com.unionmobile.angkorlife.remote.impl

import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.data.model.CandidateDetailEntity
import com.unionmobile.angkorlife.data.model.CandidateEntity
import com.unionmobile.angkorlife.remote.model.response.toEntity
import com.unionmobile.angkorlife.remote.service.AngkorLifeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CandidateDataSourceImpl @Inject constructor(
    private val angkorLifeService: AngkorLifeService
): CandidateDataSource {
    override fun getCandidates(page: Int, size: Int, sort: List<String>): Flow<List<CandidateEntity>> =
        flow {
            val response = angkorLifeService.getCandidates(page, size, sort)
            val candidates = response.content.map { it.toEntity() }
            emit(candidates)
        }

    override fun getCandidate(candidateId: Int, userId: String): Flow<CandidateDetailEntity> =
        flow {
            val response = angkorLifeService.getCandidate(candidateId, userId)
            val sortedProfiles =
                response.profileInfoList
                    .filter { it.fileArea == CANDIDATE_DETAIL_IMAGE }
                    .sortedBy { it.displayOrder }
            val entity = angkorLifeService.getCandidate(candidateId, userId).toEntity(sortedProfiles)
            emit(entity)
        }

    override fun getVotedCandidatesId(userId: String): Flow<List<Int>> =
        flow {
            val response = angkorLifeService.getVotedCandidatesId(userId)
            emit(response)
        }
}

private const val CANDIDATE_LIST_IMAGE = 1
private const val CANDIDATE_DETAIL_IMAGE = 2
private const val VIDEO = 3