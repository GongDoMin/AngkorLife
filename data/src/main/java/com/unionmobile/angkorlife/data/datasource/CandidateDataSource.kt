package com.unionmobile.angkorlife.data.datasource

import com.unionmobile.angkorlife.data.model.CandidateEntity
import kotlinx.coroutines.flow.Flow

interface CandidateDataSource {
    fun getCandidates(page: Int, perPage: Int, sort: List<String>) : Flow<List<CandidateEntity>>
}