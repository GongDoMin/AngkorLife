package com.unionmobile.angkorlife.data.impl

import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.repository.CandidateRepository
import kotlinx.coroutines.flow.Flow

class CandidateRepositoryImpl : CandidateRepository {
    override fun getCandidates(page: Int, perPage: Int, sort: List<String>): Flow<List<Candidate>> {
        TODO("Not yet implemented")
    }
}