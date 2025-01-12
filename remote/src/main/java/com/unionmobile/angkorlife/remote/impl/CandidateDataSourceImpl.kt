package com.unionmobile.angkorlife.remote.impl

import com.unionmobile.angkorlife.data.datasource.CandidateDataSource
import com.unionmobile.angkorlife.data.model.CandidateEntity
import kotlinx.coroutines.flow.Flow

class CandidateDataSourceImpl : CandidateDataSource {
    override fun getCandidates(page: Int, perPage: Int, sort: List<String>): Flow<List<CandidateEntity>> {
        TODO("Not yet implemented")
    }
}