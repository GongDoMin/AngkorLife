package com.unionmobile.angkorlife.domain.impl

import com.unionmobile.angkorlife.domain.model.Candidate
import com.unionmobile.angkorlife.domain.usecase.GetCandidatesUseCase
import kotlinx.coroutines.flow.Flow

class GetCandidatesUseCaseImpl : GetCandidatesUseCase {
    override fun invoke(): Flow<List<Candidate>> {
        TODO("Not yet implemented")
    }
}