package com.unionmobile.angkorlife.feature.main

import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.model.Timer
import com.unionmobile.angkorlife.domain.usecase.GetCandidatesUseCase
import com.unionmobile.angkorlife.domain.usecase.GetTimerUseCase
import com.unionmobile.angkorlife.domain.usecase.GetVotedCandidatesIdUseCase
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import com.unionmobile.angkorlife.feature.common.launch
import com.unionmobile.angkorlife.feature.main.model.CandidateModel
import com.unionmobile.angkorlife.feature.main.model.toFormattedString
import com.unionmobile.angkorlife.feature.main.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTimerUseCase: GetTimerUseCase,
    private val getCandidatesUseCase: GetCandidatesUseCase,
    private val getVotedCandidatesIdUseCase: GetVotedCandidatesIdUseCase,
    private val voteUseCase: VoteUseCase
) : ViewModel() {
    data class UiState(
        val timer: Timer = Timer(),
        val candidates: List<CandidateModel> = emptyList()
    )

    sealed interface Event {
        data class ShowSnackBar(val message: String) : Event
        data class ShowSnackBarAndNavigateToLogin(val message: String) : Event
    }

    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    fun vote(candidateId: Int) {
        launch(Dispatchers.IO) {
            voteUseCase.invoke(candidateId).collect {
                val candidates = uiState.value.candidates.map {
                    if (it.id == candidateId) {
                        it.copy(
                            voteCntInt = it.voteCntInt + 1,
                            voteCntString = (it.voteCntInt + 1).toFormattedString(),
                            isVoted = true
                        )
                    }
                    else {
                        it
                    }
                }
                _uiState.update { it.copy(candidates = candidates) }
            }
        }
    }

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
            combine(
                getCandidatesUseCase.invoke(),
                getVotedCandidatesIdUseCase.invoke()
            ) { candidates, votedCandidatesId ->
                candidates.map { candidate ->
                    candidate.toPresentation(
                        isVoted = votedCandidatesId.contains(candidate.id)
                    )
                }
            }.catch {
                when (it) {
                    is IOException -> {
                        _event.send(
                            Event.ShowSnackBarAndNavigateToLogin("연결 실패")
                        )
                    }
                    else -> {
                        _event.send(
                            Event.ShowSnackBarAndNavigateToLogin("에러가 발생했습니다.")
                        )
                    }
                }
            }.collect { candidates ->
                _uiState.update {
                    it.copy(
                        candidates = candidates
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