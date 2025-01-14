package com.unionmobile.angkorlife.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.usecase.GetCandidateDetailUseCase
import com.unionmobile.angkorlife.domain.usecase.VoteUseCase
import com.unionmobile.angkorlife.exception.ExceptionType
import com.unionmobile.angkorlife.feature.common.launch
import com.unionmobile.angkorlife.feature.detail.model.CandidateDetailModel
import com.unionmobile.angkorlife.feature.detail.model.toPresentation
import com.unionmobile.angkorlife.feature.main.MainViewModel.Event
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
        val candidateDetail: CandidateDetailModel = CandidateDetailModel()
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
        launch(Dispatchers.IO) {
            getCandidateDetail(candidateId)
        }
    }

    fun vote() {
        val candidateId = _uiState.value.candidateDetail.id
        launch(Dispatchers.IO) {
            voteUseCase.invoke(candidateId)
                .catch {
                    (it as ExceptionType).handleVoteError()
                }.collect {
                    _uiState.update {
                        it.copy(
                            it.candidateDetail.copy(
                                voted = true
                            )
                        )
                    }
            }
        }
    }

    private suspend fun getCandidateDetail(candidateId: Int?) {
        if (candidateId == null) {
            _event.send(
                Event.ShowSnackBarAndNavigateToMain("잘못된 접근입니다.")
            )
            return
        }

        getCandidateDetailUseCase.invoke(candidateId)
            .catch {
                (it as ExceptionType).handleGetCandidateDetailError()
            }.collect { candidateDetail ->
            _uiState.update {
                it.copy(
                    candidateDetail = candidateDetail.toPresentation()
                )
            }
        }
    }

    private suspend fun ExceptionType.handleVoteError() {
        val message = this.message ?: ""
        when (this) {
            is ExceptionType.Network ->
                _event.send(
                    Event.ShowSnackBar(message)
                )
            is ExceptionType.NotFound ->
                _event.send(
                    Event.ShowSnackBarAndNavigateToMain(message)
                )
            is ExceptionType.Conflict ->
                if (uiState.value.candidateDetail.voted) {
                    _event.send(
                        Event.ShowSnackBar(message)
                    )
                } else {
                    _uiState.update {
                        it.copy(
                            it.candidateDetail.copy(
                                voted = true
                            )
                        )
                    }
                }
            else -> {
                _event.send(
                    Event.ShowSnackBarAndNavigateToLogin("에러가 발생했습니다.")
                )
            }
        }
    }

    private suspend fun ExceptionType.handleGetCandidateDetailError() {
        val message = this.message ?: ""
        when (this) {
            is ExceptionType.Network ->
                _event.send(
                    Event.ShowSnackBarAndNavigateToMain(message)
                )
            is ExceptionType.NotFound ->
                _event.send(
                    Event.ShowSnackBarAndNavigateToMain(message)
                )
            else ->
                _event.send(
                    Event.ShowSnackBarAndNavigateToLogin(message)
                )
        }
    }
}