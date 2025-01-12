package com.unionmobile.angkorlife.feature.main

import androidx.lifecycle.ViewModel
import com.unionmobile.angkorlife.domain.model.Timer
import com.unionmobile.angkorlife.domain.usecase.GetTimerUseCase
import com.unionmobile.angkorlife.feature.common.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTimerUseCase: GetTimerUseCase
) : ViewModel() {
    data class UiState(
        val timer: Timer = Timer()
    )

    private val _uiState = MutableStateFlow<UiState>(UiState())
    val uiState = _uiState.asStateFlow()

    fun updateUiState(timer: Timer) {
        _uiState.update { it.copy(timer = timer) }
    }

    private fun getTimer() {
        launch(Dispatchers.IO) {
            getTimerUseCase.invoke().collect {
                updateUiState(it)
            }
        }
    }

    init {
        getTimer()
    }
}