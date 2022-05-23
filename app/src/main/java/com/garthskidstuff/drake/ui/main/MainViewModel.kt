package com.garthskidstuff.drake.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    fun pushAction(uiAction: UIAction) {
        _uiState.update { uiAction.runAction(this, getApplication<Application>()) }
    }

    fun tryPushEvent(uiEvent: UIEvent): Boolean {
        return _uiEvent.tryEmit(uiEvent)
    }

    private val _uiEvent: MutableSharedFlow<UIEvent> = MutableSharedFlow(10)
    val uiEvent: SharedFlow<UIEvent> = _uiEvent.asSharedFlow()

    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Idle)

    val uiState = _uiState.asStateFlow()

}