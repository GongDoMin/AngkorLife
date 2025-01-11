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
        val id: String = "",
        val loginButtonEnable: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: String = ""
    )

    sealed interface Event {
        data object SuccessLogin : Event
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
                    updateUiState(
                        isError = true,
                        errorMessage = it.message ?: "Unknown Error"
                    )
                }
                .collect {
                    updateEvent(Event.SuccessLogin)
                }
        }
    }

    fun updateUiState(
        id: String = "",
        loginButtonEnable: Boolean = false,
        isError: Boolean = false,
        errorMessage: String = ""
    ) {
        _uiState
            .update { uiState ->
                uiState.copy(
                    id = id,
                    loginButtonEnable = loginButtonEnable,
                    isError = isError,
                    errorMessage = errorMessage
                )
            }
    }

    private suspend fun updateEvent(
        event: Event
    ) {
        _event.send(event)
    }
}