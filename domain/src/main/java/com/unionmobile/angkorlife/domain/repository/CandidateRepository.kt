package com.unionmobile.angkorlife.domain.repository

import com.unionmobile.angkorlife.domain.model.Candidate
import kotlinx.coroutines.flow.Flow

interface CandidateRepository {
    fun getCandidates(page: Int, perPage: Int, sort: List<String>) : Flow<List<Candidate>>
}