package com.unionmobile.angkorlife.domain.usecase

import com.unionmobile.angkorlife.domain.model.CandidateDetail
import kotlinx.coroutines.flow.Flow

interface GetCandidateDetailUseCase {
    operator fun invoke(candidateId: Int): Flow<CandidateDetail>
}