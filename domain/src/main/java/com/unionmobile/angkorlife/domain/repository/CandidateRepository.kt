package com.unionmobile.angkorlife.domain.repository

import com.unionmobile.angkorlife.domain.model.Candidate
import kotlinx.coroutines.flow.Flow

interface CandidateRepository {
    fun getCandidates(page: Int, size: Int, sort: List<String>) : Flow<List<Candidate>>
}