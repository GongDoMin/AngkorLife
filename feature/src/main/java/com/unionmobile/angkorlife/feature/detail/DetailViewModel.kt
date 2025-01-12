package com.unionmobile.angkorlife.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.usecase.GetCandidateDetailUseCase
import com.unionmobile.angkorlife.feature.common.launch
import com.unionmobile.angkorlife.feature.detail.model.CandidateDetailModel
import com.unionmobile.angkorlife.feature.detail.model.toPresentation
import com.unionmobile.angkorlife.feature.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCandidateDetailUseCase: GetCandidateDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    data class UiState(
        val candidateDetail: CandidateDetailModel = CandidateDetailModel()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private fun getCandidateDetail(candidateId: Int?) {
        checkNotNull(candidateId) { "candidateId cannot be null" }

        launch(Dispatchers.IO) {
            getCandidateDetailUseCase.invoke(candidateId).collect { candidateDetail ->
                _uiState.update {
                    it.copy(
                        candidateDetail = candidateDetail.toPresentation()
                    )
                }
            }
        }
    }

    init {
        val candidateId = savedStateHandle.get<Int>(Routes.DETAIL.CANDIDATE_ID)
        getCandidateDetail(candidateId)
    }
}