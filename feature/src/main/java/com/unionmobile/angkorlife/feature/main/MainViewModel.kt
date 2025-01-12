package com.unionmobile.angkorlife.feature.main

import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.model.Timer
import com.unionmobile.angkorlife.domain.usecase.GetCandidatesUseCase
import com.unionmobile.angkorlife.domain.usecase.GetTimerUseCase
import com.unionmobile.angkorlife.feature.common.launch
import com.unionmobile.angkorlife.feature.main.model.CandidateModel
import com.unionmobile.angkorlife.feature.main.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTimerUseCase: GetTimerUseCase,
    private val getCandidatesUseCase: GetCandidatesUseCase
) : ViewModel() {
    data class UiState(
        val timer: Timer = Timer(),
        val candidates: List<CandidateModel> = emptyList()
    )

    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState = _uiState.asStateFlow()

    private fun getTimer() {
        launch(Dispatchers.IO) {
            getTimerUseCase.invoke().collect { timer ->
                _uiState.update {
                    it.copy(
                        timer = timer
                    )
                }
            }
        }
    }

    private fun getCandidates() {
        launch(Dispatchers.IO) {
            getCandidatesUseCase.invoke().collect { candidates ->
                _uiState.update {
                    it.copy(
                        candidates = candidates.map { it.toPresentation() }
                    )
                }
            }
        }
    }

    init {
        getTimer()
        getCandidates()
    }
}