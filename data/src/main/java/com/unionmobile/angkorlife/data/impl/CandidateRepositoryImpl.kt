package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.data.model.toModel
import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(
    private val candidateDataSource: CandidateDataSource
): CandidateRepository {
    override fun getCandidates(page: Int, perPage: Int, sort: List<String>): Flow<List<Candidate>> =
        candidateDataSource.getCandidates(page, perPage, sort).map { it.map { it.toModel() } }
}