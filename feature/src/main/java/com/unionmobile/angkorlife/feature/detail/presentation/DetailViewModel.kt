package com.unionmobile.angkorlife.feature.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.usecase.GetCandidateDetailUseCase
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import com.unionmobile.angkorlife.exception.ExceptionType
import com.unionmobile.angkorlife.feature.common.launch
import com.unionmobile.angkorlife.feature.detail.model.CandidateDetailModel
import com.unionmobile.angkorlife.feature.detail.model.toPresentation
import com.unionmobile.angkorlife.feature.navigation.Routes
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
class DetailViewModel @Inject constructor(
    private val getCandidateDetailUseCase: GetCandidateDetailUseCase,
    private val voteUseCase: VoteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    data class UiState(
        val candidateDetail: CandidateDetailModel = CandidateDetailModel(),
        val isModal: Boolean = false
    )

    sealed interface Event {
        data class ShowSnackBar(val message: String) : Event
        data class ShowSnackBarAndNavigateToLogin(val message: String) : Event
        data class ShowSnackBarAndNavigateToMain(val message: String) : Event
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    init {
        val candidateId = savedStateHandle.get<Int>(Routes.DETAIL.CANDIDATE_ID)
        val voteCnt = savedStateHandle.get<Int>(Routes.DETAIL.VOTE_CNT)
        launch(Dispatchers.IO) {
            getCandidateDetail(candidateId, voteCnt)
        }
    }

    fun vote() {
        val candidateId = _uiState.value.candidateDetail.id
        val voteCnt = uiState.value.candidateDetail.voteCnt
        launch(Dispatchers.IO) {
            voteUseCase.invoke(candidateId, voteCnt + 1)
                .catch {
                    (it as ExceptionType).handleVoteError()
                }.collect {
                    _uiState.update {
                        it.copy(
                            candidateDetail = it.candidateDetail.copy(
                                voted = true,
                                voteCnt = voteCnt + 1
                            ),
                            isModal = true
                        )
                    }
            }
        }
    }

    fun dismissModal() {
        _uiState.update { it.copy(isModal = false) }
    }

    private suspend fun getCandidateDetail(candidateId: Int?, voteCnt: Int?) {
        if (candidateId == null || voteCnt == null) {
            _event.send(
                Event.ShowSnackBarAndNavigateToMain("Invalid access")
            )
            return
        }

        getCandidateDetailUseCase.invoke(candidateId)
            .catch {
                (it as ExceptionType).handleGetCandidateDetailError()
            }.collect { candidateDetail ->
                _uiState.update {
                    it.copy(
                        candidateDetail = candidateDetail.toPresentation(voteCnt)
                    )
                }
        }
    }

    private suspend fun ExceptionType.handleVoteError() {
        when (this) {
            is ExceptionType.Network, is ExceptionType.BadRequest ->
                _event.send(Event.ShowSnackBar(message))
            is ExceptionType.NotFound ->
                _event.send(Event.ShowSnackBarAndNavigateToMain(message))
            is ExceptionType.Conflict ->
                if (uiState.value.candidateDetail.voted) {
                    _event.send(Event.ShowSnackBar(message))
                } else {
                    _uiState.update {
                        it.copy(
                            it.candidateDetail.copy(
                                voted = true
                            )
                        )
                    }
                }
            is ExceptionType.UnAuthorized ->
                _event.send(Event.ShowSnackBarAndNavigateToLogin(message))
            else ->
                _event.send(Event.ShowSnackBar(message))
        }
    }

    private suspend fun ExceptionType.handleGetCandidateDetailError() {
        when (this) {
            is ExceptionType.Network, is ExceptionType.NotFound ->
                _event.send(Event.ShowSnackBarAndNavigateToMain(message))
            is ExceptionType.UnAuthorized ->
                _event.send(Event.ShowSnackBarAndNavigateToLogin(message))
            else ->
                _event.send(Event.ShowSnackBar(message))
        }
    }
}