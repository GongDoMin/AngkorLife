package com.unionmobile.angkorlife.feature.main.presentation

import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.model.Timer
import com.unionmobile.angkorlife.domain.usecase.GetCandidatesUseCase
import com.unionmobile.angkorlife.domain.usecase.GetTimerUseCase
import com.unionmobile.angkorlife.domain.usecase.GetVotedCandidatesIdUseCase
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import com.unionmobile.angkorlife.exception.ExceptionType
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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
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
        val candidates: List<CandidateModel> = emptyList(),
        val isModal: Boolean = false
    )

    sealed interface Event {
        data class ShowSnackBar(val message: String) : Event
        data class ShowSnackBarAndNavigateToLogin(val message: String) : Event
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    init {
        getTimer()
        getCandidates()
    }

    fun vote(candidateId: Int) {
        launch(Dispatchers.IO) {
            voteUseCase.invoke(candidateId)
                .catch {
                    (it as ExceptionType).handleVoteError(candidateId)
                }.collect {
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
                    _uiState.update {
                        it.copy(
                            candidates = candidates,
                            isModal = true
                        )
                    }
            }
        }
    }

    fun dismissModal() {
        _uiState.update { it.copy(isModal = false) }
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
            getCandidatesUseCase.invoke()
                .catch {
                    _event.send(
                        Event.ShowSnackBarAndNavigateToLogin((it as ExceptionType).message)
                    )
                }.collect {
                    val candidates = it.map { candidate ->
                        candidate.toPresentation(
                            isVoted = false
                        )
                    }

                    _uiState.update {
                        it.copy(
                            candidates = candidates
                        )
                    }
                }

            getVotedCandidatesIdUseCase.invoke()
                .collect { votedCandidatesId ->
                    _uiState.update { uiState ->
                        uiState.copy(
                            candidates = uiState.candidates.map { candidate ->
                                if (votedCandidatesId.contains(candidate.id)) {
                                    candidate.copy(isVoted = true)
                                } else {
                                    candidate
                                }
                            }
                        )
                    }
                }
        }
    }

    private suspend fun ExceptionType.handleVoteError(candidateId: Int) {
        when (this) {
            is ExceptionType.Network, is ExceptionType.BadRequest -> {
                _event.send(Event.ShowSnackBar(this.message))
            }
            is ExceptionType.NotFound -> {
                val candidates = uiState.value.candidates.filterNot { it.id == candidateId }
                _uiState.update { it.copy(candidates = candidates) }
                _event.send(Event.ShowSnackBar(errorMessage))
            }
            is ExceptionType.Conflict -> {
                if (uiState.value.candidates.find { it.id == candidateId }?.isVoted == true) {
                    _event.send(Event.ShowSnackBar(errorMessage))
                } else {
                    val candidates = uiState.value.candidates.map {
                        if (it.id == candidateId) it.copy(isVoted = true)
                        else it
                    }
                    _uiState.update { it.copy(candidates = candidates) }
                }
            }
            else ->
                _event.send(Event.ShowSnackBar(this.message))
        }
    }
}