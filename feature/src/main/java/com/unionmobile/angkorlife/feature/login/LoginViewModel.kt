package com.unionmobile.angkorlife.feature.login

import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.usecase.LoginUseCase
import com.unionmobile.angkorlife.feature.common.launch
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    data class UiState(
        val id: String = ""
    )

    sealed interface Event {
        data object SuccessLogin : Event
        data class ShowSnackBar(val message: String) : Event
    }

    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    fun login() {
        val id = uiState.value.id

        launch(Dispatchers.IO) {

            loginUseCase.invoke(id)
                .catch {
                    updateEvent(Event.ShowSnackBar("아이디의 길이는 최소 1 최대 16 입니다."))
                }
                .collect {
                    updateEvent(Event.SuccessLogin)
                }
        }
    }

    fun updateId(id: String) {
        _uiState.update {
            it.copy(id = id)
        }
    }

    private suspend fun updateEvent(
        event: Event
    ) {
        _event.send(event)
    }
}