package com.unionmobile.angkorlife.feature.login.presentation

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
        val id: String = "",
        val isLoggingIn: Boolean = false
    )

    sealed interface Event {
        data object SuccessLogin : Event
        data class ShowSnackBar(val message: String) : Event
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    fun login() {
        if (uiState.value.isLoggingIn) return

        _uiState.update { it.copy(isLoggingIn = true) }

        val id = uiState.value.id

        launch(Dispatchers.IO) {
            loginUseCase.invoke(id)
                .catch {
                    _uiState.update { it.copy(isLoggingIn = false) }
                    _event.send(Event.ShowSnackBar(it.message ?: ""))
                }
                .collect {
                    _event.send(Event.SuccessLogin)
                }
        }
    }

    fun updateId(id: String) {
        _uiState.update {
            it.copy(id = id)
        }
    }
}